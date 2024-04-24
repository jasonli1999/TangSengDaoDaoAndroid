package com.idss.cashloans.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.idss.cashloans.R;

import java.util.Objects;


/**
 * app版本升级
 */
public class UpdateDialog extends DialogFragment {
    private final Context context;
    private final int force;
    private final String downloadurl;

    public UpdateDialog(@NonNull Context context, int force, String downloadurl) {
        this.context = context;
        this.force = force;
        this.downloadurl = downloadurl;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //去除标题栏
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

            // 0~1 , 1表示完全昏暗
            Objects.requireNonNull(dialog.getWindow()).setDimAmount(0.5f);

//            点击外部不消失
            dialog.setCanceledOnTouchOutside(false);

//            点击返回键不消失，需要监听OnKeyListener:
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        return true;
                    }
                    return false;
                }
            });
        }

        View view = inflater.inflate(R.layout.dialog_location, container, false);

        //0不强制  1强制
        if (force == 0) {
            view.findViewById(R.id.ll_select_update).setVisibility(View.VISIBLE);
            view.findViewById(R.id.btn_ok).setVisibility(View.GONE);

            view.findViewById(R.id.btn_cancle).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });

            view.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(downloadurl));
                    context.startActivity(intent);
                    dismiss();

                }
            });

        } else {
            view.findViewById(R.id.ll_select_update).setVisibility(View.GONE);
            view.findViewById(R.id.btn_ok).setVisibility(View.VISIBLE);
            view.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(downloadurl));
                    context.startActivity(intent);
                }
            });
        }
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        //设置动画、位置、宽度等属性（注意一：必须放在onStart方法中）
        Window window = Objects.requireNonNull(getDialog()).getWindow();
        if (window != null) {
            // 注意二：一定要设置Background，如果不设置，window属性设置无效
            window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));
            WindowManager.LayoutParams layoutParams = window.getAttributes();
//            layoutParams.windowAnimations = R.style.MusicDialog;//动画
            layoutParams.gravity = Gravity.CENTER; // 位置
            layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;//宽度满屏
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;//宽度满屏
            window.setAttributes(layoutParams);
        }

    }

}
