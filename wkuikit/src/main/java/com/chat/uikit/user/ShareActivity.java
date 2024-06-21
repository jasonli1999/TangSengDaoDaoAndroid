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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.chat.base.SharePreferencesUtil;
import com.chat.base.utils.WKLogUtils;
import com.chat.uikit.R;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShareActivity extends AppCompatActivity {

    private TextView tv_share;
    private ClipboardManager mClipboard = null;
    private String shareUrl;
    private String sharTitle = "";
    private ImageView imageView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_share);
        imageView = findViewById(R.id.iv_img);
        tv_share = findViewById(R.id.tv_share);


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

        getShareUrl();

    }

    private void getShareUrl() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("https://forcyshare620-1323147468.cos.accelerate.myqcloud.com/forcyshare.json").build();
        //enqueue就是将此次的call请求加入异步请求队列，会开启新的线程执行，并将执行的结果通过Callback接口回调的形式返回。
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //请求失败的回调方法
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ShareUrl shareUrl1json = new Gson().fromJson(response.body().string(), ShareUrl.class);
                //请求成功的回调方法
                shareUrl = shareUrl1json.url + SharePreferencesUtil.getString(getApplicationContext(), "shortNo", "");
                WKLogUtils.e(shareUrl);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bitmap;
                        try {
                            WKLogUtils.e(shareUrl);
                            bitmap = encodeAsBitmap(shareUrl, 600, 600);
                            imageView.setImageBitmap(bitmap);
                        } catch (WriterException e) {
                            e.printStackTrace();
                        }

                        tv_share.setText("复制分享链接");
                    }
                });

                //关闭body
                response.body().close();
            }
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