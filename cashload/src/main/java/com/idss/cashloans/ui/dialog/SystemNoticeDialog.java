package com.idss.cashloans.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.idss.cashloans.R;
import com.idss.cashloans.api.moudle.SystemNoticeMoudel;

import java.util.Objects;


/**
 *   显示财务id
 */
public class SystemNoticeDialog extends DialogFragment {
    private Context context;
    private final SystemNoticeMoudel systemNoticeMoudel;

    public SystemNoticeDialog(@NonNull Context context,SystemNoticeMoudel systemNoticeMoudel) {
        this.context = context;
        this.systemNoticeMoudel=systemNoticeMoudel;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //去除标题栏
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

            // 0~1 , 1表示完全昏暗
            Objects.requireNonNull(dialog.getWindow()).setDimAmount(0.5f);

            //  点击外部不消失
            dialog.setCanceledOnTouchOutside(false);

            //点击返回键不消失，需要监听OnKeyListener:
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

        View view = inflater.inflate(R.layout.dialog_syetemnotie, container, false);

        TextView  tv_title= view.findViewById(R.id.tv_title);
        tv_title.setText(systemNoticeMoudel.getData().noticeTitle);
        TextView  tv_systemNotice= view.findViewById(R.id.tv_systemNotice);
        tv_systemNotice.setText(systemNoticeMoudel.getData().noticeContent);


            view.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                         dismiss();
                }
            });


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