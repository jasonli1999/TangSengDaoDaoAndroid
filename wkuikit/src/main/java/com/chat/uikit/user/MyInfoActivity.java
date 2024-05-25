package com.chat.uikit.user;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.chat.base.SharePreferencesUtil;
import com.chat.base.base.WKBaseActivity;
import com.chat.base.common.WKCommonModel;
import com.chat.base.config.WKConfig;
import com.chat.base.entity.BottomSheetItem;
import com.chat.base.entity.UserInfoEntity;
import com.chat.base.entity.WKAPPConfig;
import com.chat.base.net.HttpResponseCode;
import com.chat.base.utils.WKDialogUtils;
import com.chat.base.utils.WKLogUtils;
import com.chat.base.utils.singleclick.SingleClickUtil;
import com.chat.uikit.R;
import com.chat.uikit.databinding.ActMyInfoLayoutBinding;
import com.chat.uikit.user.service.UserModel;
import com.xinbida.wukongim.WKIM;
import com.xinbida.wukongim.entity.WKChannel;
import com.xinbida.wukongim.entity.WKChannelType;

import java.util.ArrayList;
import java.util.List;

/**
 * 2020-06-29 22:28
 * 登录用户个人信息
 */
public class MyInfoActivity extends WKBaseActivity<ActMyInfoLayoutBinding> {

    @Override
    protected ActMyInfoLayoutBinding getViewBinding() {
        return ActMyInfoLayoutBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setTitle(TextView titleTv) {
        titleTv.setText(R.string.personal_info);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        wkVBinding.idLeftTv.setText(getString(R.string.identity));
        wkVBinding.refreshLayout.setEnableOverScrollDrag(true);
        wkVBinding.refreshLayout.setEnableLoadMore(false);
        wkVBinding.refreshLayout.setEnableRefresh(false);
        UserInfoEntity userInfoEntity = WKConfig.getInstance().getUserInfo();
        WKAPPConfig appConfig = WKConfig.getInstance().getAppConfig();
        if (userInfoEntity.short_status == 1 || appConfig.shortno_edit_off == 1) {
            wkVBinding.identityLayout.setEnabled(false);
            wkVBinding.identityIv.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        WKChannel channel = WKIM.getInstance().getChannelManager().getChannel(WKConfig.getInstance().getUid(), WKChannelType.PERSONAL);
        if (channel != null && !TextUtils.isEmpty(channel.channelID)) {
            wkVBinding.avatarView.showAvatar(channel);
        } else
            wkVBinding.avatarView.showAvatar(WKConfig.getInstance().getUid(), WKChannelType.PERSONAL);
    }

    @Override
    protected void initListener() {
        WKCommonModel.getInstance().getChannel(WKConfig.getInstance().getUid(), WKChannelType.PERSONAL, (code, msg, entity) -> {
            if (entity != null && entity.extra != null) {
                Object sexObject = entity.extra.get("sex");
                if (sexObject != null) {
                    int sex = (int) sexObject;
                    wkVBinding.sexTv.setText(sex == 1 ? R.string.male : R.string.female);
                }
                Object shortNoObject = entity.extra.get("short_no");
                if (shortNoObject != null) {
                    String shortNo = (String) shortNoObject;
                    wkVBinding.identityTv.setText(shortNo);
                    WKLogUtils.e("shortNo:   "+shortNo);
                    SharePreferencesUtil.addString(getApplicationContext(),"shortNo",shortNo);
                    wkVBinding.identityTv.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("label", wkVBinding.identityTv.getText().toString());
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(getApplicationContext(), "复制成功", Toast.LENGTH_LONG).show();
                            return true;
                        }
                    });

                    wkVBinding.nameTv.setText(entity.name);
                }
            }
        });
        SingleClickUtil.onSingleClick(wkVBinding.headLayout, view -> startActivity(new Intent(MyInfoActivity.this, MyHeadPortraitActivity.class)));
        SingleClickUtil.onSingleClick(wkVBinding.nameLayout, view1 -> {
            Intent intent = new Intent(this, UpdateUserInfoActivity.class);
            intent.putExtra("oldStr", wkVBinding.nameTv.getText().toString());
            intent.putExtra("updateType", 1);
            chooseResultLac.launch(intent);
        });
//        SingleClickUtil.onSingleClick(wkVBinding.identityLayout, view1 -> {
//            if (WKConfig.getInstance().getAppConfig().shortno_edit_off == 0) {
//                Intent intent = new Intent(this, UpdateUserInfoActivity.class);
//                intent.putExtra("oldStr", wkVBinding.identityTv.getText().toString());
//                intent.putExtra("updateType", 2);
//                chooseResultLac.launch(intent);
//            }
//        });
        SingleClickUtil.onSingleClick(wkVBinding.qrLayout, view1 -> startActivity(new Intent(this, UserQrActivity.class)));
        SingleClickUtil.onSingleClick(wkVBinding.share, view1 -> startActivity(new Intent(this, ShareActivity.class)));
        wkVBinding.sexLayout.setOnClickListener(v -> {
            List<BottomSheetItem> list = new ArrayList<>();
            list.add(new BottomSheetItem(getString(R.string.male), 0, () -> updateSex(1)));
            list.add(new BottomSheetItem(getString(R.string.female), 0, () -> updateSex(0)));
            WKDialogUtils.getInstance().showBottomSheet(this, getString(R.string.sex), false, list);
        });
    }

    private void updateSex(int value) {
        UserModel.getInstance().updateUserInfo("sex", String.valueOf(value), (code, msg) -> {
            if (code == HttpResponseCode.success)
                wkVBinding.sexTv.setText(value == 1 ? R.string.male : R.string.female);
            else showToast(msg);
        });
    }

    ActivityResultLauncher<Intent> chooseResultLac = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
            String resultStr = result.getData().getStringExtra("result");
            int updateType = result.getData().getIntExtra("updateType", 1);
            if (updateType == 1) {
                wkVBinding.nameTv.setText(resultStr);
                WKConfig.getInstance().setUserName(resultStr);
            } else if (updateType == 2) {
                wkVBinding.identityTv.setText(resultStr);
                wkVBinding.identityIv.setVisibility(View.GONE);
            }
        }
    });
}
