package com.chat.uikit.user;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.chat.base.SharePreferencesUtil;
import com.chat.base.utils.WKLogUtils;
import com.chat.uikit.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class ShareActivity extends AppCompatActivity {

    private TextView tv_share_num, tv_vip_time, tv_share;
    private ClipboardManager mClipboard = null;
    private String shareUrl;
    private String sharTitle = "欢迎下载悦言";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        ImageView imageView = findViewById(R.id.iv_img);
        tv_share = findViewById(R.id.tv_share);
        Bitmap bitmap;
        try {
            shareUrl = "https://ql3332.yueyan.me/?channelCode" + SharePreferencesUtil.getString(getApplicationContext(), "inviteCode", "");
            WKLogUtils.e(shareUrl);
            bitmap = encodeAsBitmap(shareUrl, 600, 600);
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        tv_share.setText("复制分享链接");


        findViewById(R.id.iv_back).setOnClickListener(v -> finish());

        findViewById(R.id.tv_share).setOnClickListener(v -> {

            // Gets a handle to the clipboard service.
            if (null == mClipboard) {
                mClipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            }

            // Creates a new text clip to put on the clipboard
            //newPlainText(label, text)：返回ClipData对象，数据是文字text，描述是label，MIME类型是MIMETYPE_TEXT_PLAIN。
            ClipData clip = ClipData.newPlainText("simple text", sharTitle + "     " + shareUrl);

            // Set the clipboard's primary clip.
            mClipboard.setPrimaryClip(clip);


            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, sharTitle + "     " + shareUrl);

            // Android 10 开始，可以通过 Intent.EXTRA_TITLE 添加描述信息，ClipData 添加缩略图
            sendIntent.putExtra(Intent.EXTRA_TITLE, sharTitle);
            sendIntent.setClipData(ClipData.newUri(getApplication().getContentResolver(), "我是缩略图", Uri.parse(shareUrl)));

            // 设置分享的类型
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);

        });


    }


    /**
     * 生成二维码
     *
     * @param contents
     * @param width
     * @param height
     * @return
     * @throws WriterException
     */
    public static Bitmap encodeAsBitmap(String contents, int width, int height) throws WriterException {
        MultiFormatWriter barcodeWriter = new MultiFormatWriter();
        BitMatrix matrix = null;
        try {
            matrix = barcodeWriter.encode(contents, BarcodeFormat.QR_CODE, width, height);
        } catch (IllegalArgumentException iae) {
            return null;
        }
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = matrix.get(x, y) ? BLACK : WHITE;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

}