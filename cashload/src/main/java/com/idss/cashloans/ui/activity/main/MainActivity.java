package com.idss.cashloans.ui.activity.main;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.azhon.basic.base.BaseActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.idss.cashloans.R;
import com.idss.cashloans.api.moudle.SystemNoticeMoudel;
import com.idss.cashloans.api.utils.AppUtils;
import com.idss.cashloans.api.utils.SoundUtils;
import com.idss.cashloans.databinding.ActivityMainBinding;
import com.idss.cashloans.ui.ViewMoudle.MainActivityVM;
import com.idss.cashloans.ui.activity.interfaces.pointInterface;
import com.idss.cashloans.ui.dialog.SystemNoticeDialog;
import com.idss.cashloans.ui.dialog.UpdateDialog;

import java.util.Objects;

public class MainActivity extends BaseActivity<MainActivityVM, ActivityMainBinding> implements pointInterface {
    @Override
    protected void onCreate(View view, Bundle savedInstanceState) {
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

    }

    @Override
    protected void initView() {
        //检查更新
        viewModel.getAPPLiSt(MainActivity.this);

        //获取系统提示
        viewModel.getSystemNotice();

        viewModel.requestCertification(MainActivity.this);

        /**
         *  播放音頻
         */
        SoundUtils soundUtils = new SoundUtils();
        soundUtils.playAudio_fengling(MainActivity.this);
    }

    @Override
    protected void initData() {
        viewModel.getApPversionModelMutableLiveData().observe(this, appversionModel -> {
            int size = appversionModel.getData().size();
            for (int i = 0; i < size; i++) {
                if (appversionModel.getData().get(i).getType().equals("android")) {
                    if (!Objects.equals(AppUtils.getVersionName(MainActivity.this), appversionModel.getData().get(i).getVersion())) {
                        UpdateDialog updateDialog = new UpdateDialog(MainActivity.this, appversionModel.getData().get(i).getOnOff(), appversionModel.getData().get(i).getUrl());
                        updateDialog.show(getSupportFragmentManager(), "appUpdate");
                    }
                }
            }
        });


        viewModel.getSystemNotices().observe(this, new Observer<SystemNoticeMoudel>() {
            @Override
            public void onChanged(SystemNoticeMoudel systemNoticeMoudel) {
                try {
                    if (null != systemNoticeMoudel.getData()) {
                        SystemNoticeDialog systemNoticeDialog = new SystemNoticeDialog(MainActivity.this, systemNoticeMoudel);
                        systemNoticeDialog.show(MainActivity.this.getSupportFragmentManager(), "systemNoticeDialog");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
    }

    @Override
    public void noticePoit(int position) {
        binding.navView.setSelectedItemId(binding.navView.getMenu().getItem(position).getItemId());
    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}