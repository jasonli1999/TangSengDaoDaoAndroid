package com.chat.uikit;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.chat.base.WKBaseApplication;
import com.chat.base.adapter.WKFragmentStateAdapter;
import com.chat.base.base.WKBaseActivity;
import com.chat.base.common.WKCommonModel;
import com.chat.base.config.WKApiConfig;
import com.chat.base.config.WKConfig;
import com.chat.base.config.WKConstants;
import com.chat.base.config.WKSharedPreferencesUtil;
import com.chat.base.endpoint.EndpointCategory;
import com.chat.base.endpoint.EndpointManager;
import com.chat.base.endpoint.entity.MailListDot;
import com.chat.base.net.HttpResponseCode;
import com.chat.base.ui.Theme;
import com.chat.base.ui.components.CounterView;
import com.chat.base.utils.ActManagerUtils;
import com.chat.base.utils.LayoutHelper;
import com.chat.base.utils.WKDialogUtils;
import com.chat.base.utils.WKReader;
import com.chat.base.utils.WKTimeUtils;
import com.chat.base.utils.language.WKMultiLanguageUtil;
import com.chat.uikit.chat.GenerateTestUserSig;
import com.chat.uikit.contacts.service.FriendModel;
import com.chat.uikit.databinding.ActTabMainBinding;
import com.chat.uikit.fragment.ChatFragment;
import com.chat.uikit.fragment.ContactsFragment;
import com.chat.uikit.fragment.MyFragment;
import com.tbruyelle.rxpermissions3.RxPermissions;
import com.tencent.qcloud.tuicore.TUILogin;
import com.tencent.qcloud.tuicore.interfaces.TUICallback;
import com.tencent.qcloud.tuikit.TUICommonDefine;
import com.tencent.qcloud.tuikit.tuicallkit.TUICallKit;

import org.telegram.ui.Components.RLottieImageView;

import java.util.ArrayList;
import java.util.List;


/**
 * 2019-11-12 13:57
 * tab导航栏
 */
public class TabActivity extends WKBaseActivity<ActTabMainBinding> {
    CounterView msgCounterView;
    CounterView contactsCounterView;
    //    CounterView workplaceCounterView;
    View contactsSpotView;
    RLottieImageView chatIV, contactsIV, workplaceIV, meIV;
    private long lastClickChatTabTime = 0L;

    @Override
    protected ActTabMainBinding getViewBinding() {
        return ActTabMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setTitle(TextView titleTv) {

    }

    @Override
    protected void initPresenter() {
        ActManagerUtils.getInstance().clearAllActivity();
    }

    @Override
    public boolean supportSlideBack() {
        return false;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initView() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            String desc = String.format(getString(R.string.notification_permissions_desc), getString(R.string.app_name));
            RxPermissions rxPermissions = new RxPermissions(this);
            rxPermissions.request(Manifest.permission.POST_NOTIFICATIONS).subscribe(aBoolean -> {
                if (!aBoolean) {
                    WKDialogUtils.getInstance().showDialog(this, getString(com.chat.base.R.string.authorization_request), desc, true, getString(R.string.cancel), getString(R.string.to_set), 0, Theme.colorAccount, index -> {
                        if (index == 1) {
                            EndpointManager.getInstance().invoke("show_open_notification_dialog", this);
                        }
                    });
                }
            });
        } else {
            boolean isEnabled = NotificationManagerCompat.from(this).areNotificationsEnabled();
            if (!isEnabled) {
                EndpointManager.getInstance().invoke("show_open_notification_dialog", this);
            }
        }

        chatIV = new RLottieImageView(this);
        contactsIV = new RLottieImageView(this);
//        workplaceIV = new RLottieImageView(this);
        meIV = new RLottieImageView(this);

        List<Fragment> fragments = new ArrayList<>(3);
        fragments.add(new ChatFragment());
        fragments.add(new ContactsFragment());
//        Fragment workplaceFra = (Fragment) EndpointManager.getInstance().invoke("get_workplace_fragment", null);
//        fragments.add(workplaceFra);
        fragments.add(new MyFragment());

        wkVBinding.vp.setAdapter(new WKFragmentStateAdapter(this, fragments));

        WKCommonModel.getInstance().getAppNewVersion(false, version -> {
            if (version != null && !TextUtils.isEmpty(version.download_url)) {
                WKDialogUtils.getInstance().showNewVersionDialog(TabActivity.this, version);
            }
        });
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
        WKCommonModel.getInstance().getAppConfig(null);
        wkVBinding.bottomNavigation.getOrCreateBadge(R.id.i_chat).setVisible(false);
        wkVBinding.bottomNavigation.getOrCreateBadge(R.id.i_my).setVisible(false);
        wkVBinding.bottomNavigation.getOrCreateBadge(R.id.i_chat).setVisible(false);
        FrameLayout view = wkVBinding.bottomNavigation.findViewById(R.id.i_chat);
        msgCounterView = new CounterView(this);
        msgCounterView.setColors(R.color.white, R.color.reminderColor);
        view.addView(chatIV, LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, Gravity.CENTER));
        view.addView(msgCounterView, LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, Gravity.CENTER, 20, 5, 0, 15));

        FrameLayout contactsView = wkVBinding.bottomNavigation.findViewById(R.id.i_contacts);
        contactsCounterView = new CounterView(this);
        contactsCounterView.setColors(R.color.white, R.color.reminderColor);
        contactsView.addView(contactsIV, LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, Gravity.CENTER));
        contactsView.addView(contactsCounterView, LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, Gravity.CENTER, 20, 5, 0, 15));
        contactsSpotView = new View(this);
        contactsSpotView.setBackgroundResource(R.drawable.msg_bg);
        contactsView.addView(contactsSpotView, LayoutHelper.createFrame(10, 10, Gravity.CENTER_HORIZONTAL, 10, 10, 0, 0));


//        FrameLayout workplaceView = wkVBinding.bottomNavigation.findViewById(R.id.i_workplace);
//        workplaceCounterView = new CounterView(this);
//        workplaceCounterView.setColors(R.color.white, R.color.reminderColor);
//        workplaceView.addView(workplaceIV, LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, Gravity.CENTER));
//        workplaceView.addView(workplaceCounterView, LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, Gravity.CENTER, 20, 5, 0, 15));


        FrameLayout meView = wkVBinding.bottomNavigation.findViewById(R.id.i_my);
        meView.addView(meIV, LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, Gravity.CENTER));

        contactsSpotView.setVisibility(View.GONE);
        contactsCounterView.setVisibility(View.GONE);
//        workplaceCounterView.setVisibility(View.GONE);
        msgCounterView.setVisibility(View.GONE);
        playAnimation(0);



    }

    private void initNotitionPermeision() {
        int currentApiVersion = Build.VERSION.SDK_INT;
        if (currentApiVersion >= Build.VERSION_CODES.M) {
            // 当前系统版本为 Android 6.0 及以上版本
            if (Settings.canDrawOverlays(WKBaseApplication.getInstance().getContext())) {
                // 应用拥有悬浮窗权限
                initTencentCall();
            } else {
                // 应用没有悬浮窗权限
                showDialog2("为了更好的使用悦言,请打开悬浮窗权限", new WKDialogUtils.IClickListener() {
                    @Override
                    public void onClick(int index) {
                        PermissionHelper.requestSystemAlertWindowPermission(TabActivity.this);
                    }
                });
            }
        } else {
            // 当前系统版本低于 Android 6.0
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PermissionHelper.REQUEST_CODE_SYSTEM_ALERT_WINDOW) {

            if (Settings.canDrawOverlays(WKBaseApplication.getInstance().getContext())) {
                // 权限获取成功，可以创建悬浮窗
                initNotitionPermeision();
            } else {
                // 用户拒绝授权
                Toast.makeText(TabActivity.this,"不开启悬浮窗会影响app的使用.",Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 获取应用程序的版本号
     *
     * @return 应用程序的版本号
     */
    public String getAppVersion() {
        try {
            // 获取包管理器
            PackageManager packageManager = getPackageManager();
            // 获取当前应用的包名
            String packageName = getPackageName();
            // 获取 PackageInfo 对象
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            // 获取应用程序的版本号
            int versionCode = packageInfo.versionCode;
            String versionName = packageInfo.versionName;
            // 返回版本号
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            // 异常处理，返回空字符串或其他默认值
            return "";
        }
    }

    @Override
    protected void initListener() {
        wkVBinding.vp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 0) {
                    playAnimation(0);
                    wkVBinding.bottomNavigation.setSelectedItemId(R.id.i_chat);
                } else if (position == 1) {
                    playAnimation(1);
                    wkVBinding.bottomNavigation.setSelectedItemId(R.id.i_contacts);
                }
//                else if (position == 2) {
//                    playAnimation(2);
//                    wkVBinding.bottomNavigation.setSelectedItemId(R.id.i_workplace);
//                }
                else if (position == 2) {
                    playAnimation(2);
                    wkVBinding.bottomNavigation.setSelectedItemId(R.id.i_my);
                }
            }
        });
        wkVBinding.bottomNavigation.setItemIconTintList(null);
        wkVBinding.bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.i_chat) {
                long nowTime = WKTimeUtils.getInstance().getCurrentMills();
                if (wkVBinding.vp.getCurrentItem() == 0) {
                    if (nowTime - lastClickChatTabTime <= 300) {
                        EndpointManager.getInstance().invoke("scroll_to_unread_channel", null);
                    }
                    lastClickChatTabTime = nowTime;
                    return true;
                }
                wkVBinding.vp.setCurrentItem(0);
                playAnimation(0);
            } else if (item.getItemId() == R.id.i_contacts) {
                wkVBinding.vp.setCurrentItem(1);
                playAnimation(1);
            }
//            else if (item.getItemId() == R.id.i_workplace) {
//                wkVBinding.vp.setCurrentItem(2);
//                playAnimation(2);
//            }
            else if (item.getItemId() == R.id.i_my) {
                wkVBinding.vp.setCurrentItem(2);
                playAnimation(2);
            }
            return true;
        });
        EndpointManager.getInstance().setMethod("tab_activity", EndpointCategory.wkRefreshMailList, object -> {
            getAllRedDot();
            return null;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllRedDot();
        boolean sync_friend = WKSharedPreferencesUtil.getInstance().getBoolean("sync_friend");
        if (sync_friend) {
            FriendModel.getInstance().syncFriends((code, msg) -> {
                if (code != HttpResponseCode.success && !TextUtils.isEmpty(msg)) {
                    showToast(msg);
                }
                if (code == HttpResponseCode.success) {
                    WKSharedPreferencesUtil.getInstance().putBoolean("sync_friend", false);
                }
            });
        }


        initNotitionPermeision();
        initTencentCall();
    }


    /**
     * 初始化腾云语音
     */
    private void initTencentCall() {
        //=======================begin==============================
        String userId = WKConfig.getInstance().getUid();     // 请替换为您的UserId
        int sdkAppId = 1600040006;            // 请替换为第一步在控制台得到的SDKAppID
        String secretKey = "66bf0e39b88be03903a73b97caa3784eaa954d1ef2a0566cc3bc674c778532d2";   // 请替换为第一步在控制台得到的SecretKey

//        String userSig1 = SharePreferencesUtil.getString(WKBaseApplication.getInstance().getContext(), "SIG", "");
//        Log.e("====userSig1", userSig1);

        Log.e("====userId", userId);
        Log.e("====sdkAppId", String.valueOf(sdkAppId));

        String userSig = GenerateTestUserSig.genTestUserSig(userId);
        Log.e("====userSig", userSig);
        TUICallKit.createInstance(TabActivity.this).enableIncomingBanner(true);
        TUILogin.login(TabActivity.this, sdkAppId, userId, userSig, new TUICallback() {
            @Override
            public void onSuccess() {
                Log.e("userSig====", "onSuccess");
                //更換頭像
//                String avarterurl = SharePreferencesUtil.getString(WKBaseApplication.getInstance().getContext(), "AvatarURL", "");
//                Log.e("userSig====avarterurl1", avarterurl);
                String avarterurl = WKApiConfig.getAvatarUrl(WKConfig.getInstance().getUid());
                Log.e("userSig====avarterurl2", avarterurl);

//                String nickname = SharePreferencesUtil.getString(WKBaseApplication.getInstance().getContext(), "NICKNAME", "");
                String nickname = WKConfig.getInstance().getUserName();
                Log.e("userSig====nickname", nickname);
                TUICallKit.createInstance(WKBaseApplication.getInstance().getContext()).setSelfInfo(nickname, avarterurl, new TUICommonDefine.Callback() {
                    @Override
                    public void onSuccess() {
                        Log.e("userSig====setSelfInfo", "onSuccess");
                    }

                    @Override
                    public void onError(int i, String s) {
                        Log.e("userSig====setSelfInfo+i", String.valueOf(i));
                        Log.e("userSig====setSelfInfo+s", String.valueOf(s));
                    }
                });
            }

            @Override
            public void onError(int errorCode, String errorMessage) {
                Log.e("userSig====errorCode", String.valueOf(errorCode));
                Log.e("userSig====errorMessage", errorMessage);
            }
        });

        /**
         * 接通前先展示一个弹窗
         */
        TUICallKit.createInstance(WKBaseApplication.getInstance().getContext()).enableFloatWindow(true);

        //==============================接入tencent視頻聊天========end====================================
    }

    public void setMsgCount(int number) {
        WKUIKitApplication.getInstance().totalMsgCount = number;
        if (number > 0) {
            msgCounterView.setCount(number, true);
            msgCounterView.setVisibility(View.VISIBLE);
        } else {
            msgCounterView.setCount(0, true);
            msgCounterView.setVisibility(View.GONE);
        }
    }

    public void setContactCount(int number, boolean showDot) {
        if (number > 0 || showDot) {
            if (number > 0) {
                contactsCounterView.setCount(number, true);
                contactsCounterView.setVisibility(View.VISIBLE);
                contactsSpotView.setVisibility(View.GONE);
            } else {
                contactsCounterView.setVisibility(View.GONE);
                contactsSpotView.setVisibility(View.VISIBLE);
                contactsCounterView.setCount(0, true);
            }
        } else {
            contactsCounterView.setVisibility(View.GONE);
            contactsSpotView.setVisibility(View.GONE);
        }
    }

    private void getAllRedDot() {
        boolean showDot = false;
        int totalCount = 0;
        int newFriendCount = WKSharedPreferencesUtil.getInstance().getInt(WKConfig.getInstance().getUid() + "_new_friend_count");
        totalCount = totalCount + newFriendCount;
        List<MailListDot> list = EndpointManager.getInstance().invokes(EndpointCategory.wkGetMailListRedDot, null);
        if (WKReader.isNotEmpty(list)) {
            for (MailListDot MailListDot : list) {
                if (MailListDot != null) {
                    totalCount += MailListDot.numCount;
                    if (!showDot) showDot = MailListDot.showDot;
                }
            }
        }
        setContactCount(totalCount, showDot);
    }

    @Override
    public Resources getResources() {
        float fontScale = WKConstants.getFontScale();
        Resources res = super.getResources();
        Configuration config = res.getConfiguration();
        config.fontScale = fontScale; //1 设置正常字体大小的倍数
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.e("=====keyCode=======", "onKeyDown");
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        } else
            return super.onKeyDown(keyCode, event);
    }

    private void playAnimation(int index) {
        if (index == 0) {
            lastClickChatTabTime = 0;
            meIV.setImageResource(R.mipmap.ic_mine_n);
            contactsIV.setImageResource(R.mipmap.ic_contacts_n);
            chatIV.setImageResource(R.mipmap.ic_chat_s);
//            workplaceIV.setImageResource(R.mipmap.ic_contacts_n);
        } else if (index == 1) {
            meIV.setImageResource(R.mipmap.ic_mine_n);
            chatIV.setImageResource(R.mipmap.ic_chat_n);
            contactsIV.setImageResource(R.mipmap.ic_contacts_s);
//            workplaceIV.setImageResource(R.mipmap.ic_contacts_n);
        } else if (index == 2) {
            meIV.setImageResource(R.mipmap.ic_mine_s);
            chatIV.setImageResource(R.mipmap.ic_chat_n);
            contactsIV.setImageResource(R.mipmap.ic_contacts_n);
//            workplaceIV.setImageResource(R.mipmap.ic_contacts_s);
        } else {
            chatIV.setImageResource(R.mipmap.ic_chat_n);
            contactsIV.setImageResource(R.mipmap.ic_contacts_n);
            meIV.setImageResource(R.mipmap.ic_mine_s);
//            workplaceIV.setImageResource(R.mipmap.ic_contacts_n);
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        WKMultiLanguageUtil.getInstance().setConfiguration();
        Theme.applyTheme();
    }

    @Override
    public void finish() {
        super.finish();
        EndpointManager.getInstance().remove("tab_activity");
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


}
