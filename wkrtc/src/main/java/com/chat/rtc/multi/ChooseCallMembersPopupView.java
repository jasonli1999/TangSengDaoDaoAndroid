package com.chat.rtc.multi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chat.base.config.WKConfig;
import com.chat.base.utils.AndroidUtilities;
import com.chat.base.utils.WKToastUtils;
import com.chat.rtc.R;
import com.github.promeg.pinyinhelper.Pinyin;
import com.lxj.xpopup.core.BottomPopupView;
import com.xinbida.wukongim.WKIM;
import com.xinbida.wukongim.entity.WKChannel;
import com.xinbida.wukongim.entity.WKChannelMember;
import com.xinbida.wukongim.entity.WKChannelType;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * 7/1/21 11:51 AM
 * 选择多人音视频成员
 */
@SuppressLint("ViewConstructor")
public class ChooseCallMembersPopupView extends BottomPopupView {
    Context context;
    String channelID;
    byte channelType;
    List<String> selectedList;
    RecyclerView recyclerView;
    private int maxSelectCount = 9;
    private List<GroupMemberEntity> allList;
    ChooseMemberAdapter adapter;
    private ChooseUserSelectedAdapter selectedAdapter;
    private TextView rightBtn;
    private final IChooseBack iChooseBack;
    private int page = 1;
    private String searchKey = "";

    public ChooseCallMembersPopupView(@NonNull Context context, String channelID, byte channelType, List<String> selectedUid, IChooseBack iChooseBack) {
        super(context);
        this.context = context;
        this.channelID = channelID;
        this.channelType = channelType;
        this.selectedList = selectedUid;
        this.iChooseBack = iChooseBack;
        if (selectedList != null)
            maxSelectCount = 9 - selectedList.size();
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        adapter = new ChooseMemberAdapter();
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayout contentLayout = findViewById(R.id.contentLayout);
        contentLayout.getLayoutParams().height = AndroidUtilities.getScreenHeight() - AndroidUtilities.dp(100);
        RecyclerView selectUserRecyclerView = findViewById(R.id.selectUserRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        rightBtn = findViewById(R.id.sureBtn);
        ((DefaultItemAnimator) Objects.requireNonNull(selectUserRecyclerView.getItemAnimator())).setSupportsChangeAnimations(false);
        selectedAdapter = new ChooseUserSelectedAdapter(new ChooseUserSelectedAdapter.IGetEdit() {
            @Override
            public void onDeleted(String uid) {
                for (int i = 0, size = adapter.getData().size(); i < size; i++) {
                    if (adapter.getData().get(i).member.memberUID.equals(uid) && adapter.getData().get(i).isCanCheck == 1) {
                        adapter.getData().get(i).checked = adapter.getData().get(i).checked == 1 ? 0 : 1;
                        adapter.notifyItemChanged(i, adapter.getData().get(i));
                        break;
                    }
                }
                checkSelect();
            }

            @Override
            public void searchUser(String key) {
                ChooseCallMembersPopupView.this.searchUser(key);
            }
        });
        selectUserRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        selectUserRecyclerView.setAdapter(selectedAdapter);
        findViewById(R.id.closeIv).setOnClickListener(v -> dismiss());
        GroupMemberEntity entity = new GroupMemberEntity();
        entity.itemType = 1;
        selectedAdapter.addData(entity);
        selectedAdapter.setOnItemClickListener((adapter1, view1, position) -> {
            GroupMemberEntity userEntity = selectedAdapter.getItem(position);
            if (userEntity != null && userEntity.itemType == 0) {
                if (!userEntity.isSetDelete) {
                    userEntity.isSetDelete = true;
                    selectedAdapter.notifyItemChanged(position, userEntity);
                    return;
                }
                boolean isRemove = false;
                for (int i = 0, size = adapter.getData().size(); i < size; i++) {
                    if (adapter.getData().get(i).member.memberUID.equalsIgnoreCase(userEntity.member.memberUID) && adapter.getData().get(i).isCanCheck == 1) {
                        adapter.getData().get(i).checked = adapter.getData().get(i).checked == 1 ? 0 : 1;
                        adapter.notifyItemChanged(i, adapter.getData().get(i));
                        isRemove = true;
                        break;
                    }
                }
                if (isRemove) {
                    for (int i = 0, size = selectedAdapter.getData().size(); i < size; i++) {
                        selectedAdapter.getData().get(i).isSetDelete = false;
                    }
                    selectedAdapter.removeAt(position);
                    checkSelect();

                }
            }
        });
        adapter.setOnItemClickListener((adapter, view1, position) -> {
            GroupMemberEntity memberEntity = (GroupMemberEntity) adapter.getItem(position);
            if (memberEntity != null) {
                if (maxSelectCount != -1 && selectedAdapter.getItemCount() >= maxSelectCount) {
                    String content = String.format(context.getString(R.string.call_max_count), maxSelectCount);
                    WKToastUtils.getInstance().showToastNormal(content);
                    return;
                }
                if (memberEntity.isCanCheck == 1) {
                    memberEntity.checked = memberEntity.checked == 1 ? 0 : 1;
                    adapter.notifyItemChanged(position, memberEntity);
                    checkSelect();

                    if (memberEntity.checked == 1) {
                        GroupMemberEntity friendEntity = new GroupMemberEntity();
                        friendEntity.member = new WKChannelMember();
                        friendEntity.member.memberUID = memberEntity.member.memberUID;
                        friendEntity.member.memberName = memberEntity.member.memberName;
                        friendEntity.member.memberRemark = memberEntity.member.memberRemark;
                        selectedAdapter.addData(selectedAdapter.getData().size() - 1, friendEntity);
                        selectUserRecyclerView.scrollToPosition(selectedAdapter.getData().size() - 1);
                    } else {
                        for (int i = 0, size = selectedAdapter.getData().size(); i < size; i++) {
                            if (selectedAdapter.getData().get(i).itemType == 0 && selectedAdapter.getData().get(i).member.memberUID.equals(memberEntity.member.memberUID)) {
                                selectedAdapter.removeAt(i);
                                break;
                            }
                        }
                    }
                }

            }
        });

        rightBtn.setOnClickListener(v -> {
            if (selectedAdapter.getData().size() > 0) {
                List<String> uids = new ArrayList<>();
                for (int i = 0, size = selectedAdapter.getData().size(); i < size; i++) {
                    if (selectedAdapter.getData().get(i).member != null && !TextUtils.isEmpty(selectedAdapter.getData().get(i).member.memberUID)) {
                        uids.add(selectedAdapter.getData().get(i).member.memberUID);
                    }
                }
                iChooseBack.onResult(uids);
                dismiss();
            }
        });
        getData();
    }


    private void searchUser(String content) {
        if (TextUtils.isEmpty(content)) {
            adapter.setList(allList);
            return;
        }
        List<GroupMemberEntity> tempList = new ArrayList<>();
        for (int i = 0, size = allList.size(); i < size; i++) {
            if ((!TextUtils.isEmpty(allList.get(i).member.memberName) && allList.get(i).member.memberName.toLowerCase(Locale.getDefault())
                    .contains(content.toLowerCase(Locale.getDefault())))
                    || (!TextUtils.isEmpty(allList.get(i).member.memberRemark) && allList.get(i).member.memberRemark.toLowerCase(Locale.getDefault())
                    .contains(content.toLowerCase(Locale.getDefault())))
                    || content.contains(allList.get(i).pying.toLowerCase(
                    Locale.getDefault()))) {
                tempList.add(allList.get(i));
            }
        }
        adapter.setList(tempList);
    }

    private void getData() {
        List<WKChannelMember> memberList = WKIM.getInstance().getChannelMembersManager().getMembers(channelID, channelType);
        List<GroupMemberEntity> list = new ArrayList<>();
        //@群成员删除自己
        for (int i = 0, size = memberList.size(); i < size; i++) {
            if (!memberList.get(i).memberUID.equals(WKConfig.getInstance().getUid())) {
                GroupMemberEntity entity = new GroupMemberEntity();
                entity.member = memberList.get(i);
                WKChannel channel = WKIM.getInstance().getChannelManager().getChannel(entity.member.memberUID, WKChannelType.PERSONAL);
                if (channel != null && !TextUtils.isEmpty(channel.channelRemark)) {
                    entity.member.memberRemark = channel.channelRemark;
                }
                String showName = entity.member.memberRemark;
                if (TextUtils.isEmpty(showName))
                    showName = entity.member.memberName;

                if (!TextUtils.isEmpty(showName)) {
                    if (isStartNum(showName)) {
                        entity.pying = "#";
                    } else
                        entity.pying = Pinyin.toPinyin(showName, "").toUpperCase();
                } else entity.pying = "#";

                entity.isCanCheck = 1;
                if (selectedList != null && selectedList.size() > 0) {
                    for (int j = 0, len = selectedList.size(); j < len; j++) {
                        if (selectedList.get(j).equals(entity.member.memberUID)) {
                            entity.checked = 1;
                            entity.isCanCheck = 0;
                            break;
                        }
                    }
                }
                list.add(entity);
            }
        }
        allList = list;
        adapter.setList(allList);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.choose_call_members_layout;
    }

    private void checkSelect() {
        int count = 0;
        List<GroupMemberEntity> list = adapter.getData();
        for (int i = 0, size = list.size(); i < size; i++) {
            if (list.get(i).checked == 1 && list.get(i).isCanCheck == 1) {
                count++;
            }
        }
        if (count > 0) {
            rightBtn.setText(String.format("%s(%s)", context.getString(R.string.sure), count));
            rightBtn.setEnabled(true);
            rightBtn.setAlpha(1);
        } else {
            rightBtn.setText(R.string.sure);
            rightBtn.setEnabled(false);
            rightBtn.setAlpha(0.2f);
        }

    }

    public interface IChooseBack {
        void onResult(List<String> uids);
    }


    private boolean isStartNum(String str) {
        String temp = str.substring(0, 1);
        return Character.isDigit(temp.charAt(0));
    }
}

