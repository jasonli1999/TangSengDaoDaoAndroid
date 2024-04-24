package com.idss.cashloans.ui.activity.main;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.idss.cashloans.R;

public class BaseActivity extends AppCompatActivity {
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        View stateView = getWindow().getDecorView();
        if (stateView != null) {
            int vis = stateView.getSystemUiVisibility();
            //vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //黑色
            vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR; //白色
            stateView.setSystemUiVisibility(vis);//设置状态栏字体颜色
        }

        getWindow().setStatusBarColor(getColor(R.color.yellow_c68b03));
    }
}