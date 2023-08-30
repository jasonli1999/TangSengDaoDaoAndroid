package com.chat.uikit;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.Manifest;
import android.app.Application;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.chat.base.WKBaseApplication;
import com.chat.base.config.WKConfig;
import com.chat.base.config.WKSharedPreferencesUtil;
import com.chat.base.endpoint.EndpointCategory;
import com.chat.base.endpoint.EndpointHandler;
import com.chat.base.endpoint.EndpointManager;
import com.chat.base.endpoint.EndpointSID;
import com.chat.base.endpoint.entity.ChatChooseContacts;
import com.chat.base.endpoint.entity.ChatFunctionMenu;
import com.chat.base.endpoint.entity.ChatItemPopupMenu;
import com.chat.base.endpoint.entity.ChatToolBarMenu;
import com.chat.base.endpoint.entity.ChatViewMenu;
import com.chat.base.endpoint.entity.ChooseChatMenu;
import com.chat.base.endpoint.entity.ChooseContactsMenu;
import com.chat.base.endpoint.entity.ContactsMenu;
import com.chat.base.endpoint.entity.DBMenu;
import com.chat.base.endpoint.entity.LoginMenu;
import com.chat.base.endpoint.entity.MsgConfig;
import com.chat.base.endpoint.entity.PersonalInfoMenu;
import com.chat.base.endpoint.entity.ScanResultMenu;
import com.chat.base.endpoint.entity.UserDetailMenu;
import com.chat.base.endpoint.entity.WithdrawMsgMenu;
import com.chat.base.entity.PopupMenuItem;
import com.chat.base.entity.UserInfoEntity;
import com.chat.base.glide.ChooseMimeType;
import com.chat.base.glide.ChooseResult;
import com.chat.base.glide.ChooseResultModel;
import com.chat.base.glide.GlideUtils;
import com.chat.base.msg.IConversationContext;
import com.chat.base.msg.model.WKGifContent;
import com.chat.base.msgitem.WKContentType;
import com.chat.base.msgitem.WKMsgItemViewManager;
import com.chat.base.net.HttpResponseCode;
import com.chat.base.utils.ActManagerUtils;
import com.chat.base.utils.WKFileUtils;
import com.chat.base.utils.WKMediaFileUtils;
import com.chat.base.utils.WKPermissions;
import com.chat.base.utils.WKToastUtils;
import com.chat.uikit.chat.ChooseChatActivity;
import com.chat.uikit.chat.face.WKVoiceViewManager;
import com.chat.uikit.chat.manager.FaceManger;
import com.chat.uikit.chat.manager.WKIMUtils;
import com.chat.uikit.chat.msgmodel.WKCardContent;
import com.chat.uikit.chat.msgmodel.WKMultiForwardContent;
import com.chat.uikit.chat.provider.LoadingProvider;
import com.chat.uikit.chat.provider.WKCardProvider;
import com.chat.uikit.chat.provider.WKEmptyProvider;
import com.chat.uikit.chat.provider.WKImageProvider;
import com.chat.uikit.chat.provider.WKMultiForwardProvider;
import com.chat.uikit.chat.provider.WKNoRelationProvider;
import com.chat.uikit.chat.provider.WKPromptNewMsgProvider;
import com.chat.uikit.chat.provider.WKSensitiveWordsProvider;
import com.chat.uikit.chat.provider.WKTextProvider;
import com.chat.uikit.chat.provider.WKVoiceProvider;
import com.chat.uikit.contacts.ChooseContactsActivity;
import com.chat.uikit.contacts.NewFriendsActivity;
import com.chat.uikit.enity.SensitiveWords;
import com.chat.uikit.group.SavedGroupsActivity;
import com.chat.uikit.message.MsgModel;
import com.chat.uikit.message.ProhibitWordModel;
import com.chat.uikit.search.AddFriendsActivity;
import com.chat.uikit.setting.MsgNoticesSettingActivity;
import com.chat.uikit.setting.SettingActivity;
import com.chat.uikit.user.UserDetailActivity;
import com.xinbida.wukongim.WKIM;
import com.xinbida.wukongim.entity.WKChannel;
import com.xinbida.wukongim.entity.WKChannelType;
import com.xinbida.wukongim.entity.WKMsg;
import com.xinbida.wukongim.msgmodel.WKImageContent;
import com.xinbida.wukongim.msgmodel.WKMessageContent;
import com.xinbida.wukongim.msgmodel.WKTextContent;
import com.xinbida.wukongim.msgmodel.WKVideoContent;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * 2020-03-01 17:32
 * ui kit
 */
public class WKUIKitApplication {
    int totalMsgCount = 0;
    public String chattingChannelID;
    public SensitiveWords sensitiveWords;

    private WKUIKitApplication() {
    }

    private static class KitApplicationBinder {
        private static final WKUIKitApplication uikit = new WKUIKitApplication();
    }

    public static WKUIKitApplication getInstance() {
        return KitApplicationBinder.uikit;
    }

    private WeakReference<Application> mContext;

    public void init(Application mContext) {
        this.mContext = new WeakReference<>(mContext);
        initIM();
        //初始化im事件及监听
        WKIMUtils.getInstance().initIMListener();
        initKitModuleListener();
        String json = WKSharedPreferencesUtil.getInstance().getSP("wk_sensitive_words");
        if (!TextUtils.isEmpty(json)) {
            sensitiveWords = JSON.parseObject(json, SensitiveWords.class);
        }
        MsgModel.getInstance().syncSensitiveWords();
        ProhibitWordModel.Companion.getInstance().sync();
        MsgModel.getInstance().deleteFlameMsg();
    }

    public Context getContext() {
        return mContext.get();
    }


    public void initIM() {
        if (!TextUtils.isEmpty(WKConfig.getInstance().getToken())) {
            //设置开发模式
            WKIM.getInstance().setDebug(true);
            WKIM.getInstance().setFileCacheDir("wkIM");

            String imToken = WKConfig.getInstance().getImToken();
            String uid = WKConfig.getInstance().getUid();
            WKIM.getInstance().init(mContext.get(), uid, imToken);
        }
    }

    public void startChat() {
        if (!TextUtils.isEmpty(WKConfig.getInstance().getToken())) {
            WKIM.getInstance().getConnectionManager().connection();
        }
    }

    public void stopConn() {
        EndpointManager.getInstance().invoke("push_update_device_badge", totalMsgCount);
        WKIM.getInstance().getConnectionManager().disconnect(false);
    }

    private void initKitModuleListener() {
        // 注册消息model到sdk
        WKIM.getInstance().getMsgManager().registerContentMsg(WKCardContent.class);


        WKIM.getInstance().getMsgManager().registerContentMsg(WKMultiForwardContent.class);
        //添加消息item
        WKMsgItemViewManager.getInstance().addChatItemViewProvider(WKContentType.sensitiveWordsTips, new WKSensitiveWordsProvider());
        WKMsgItemViewManager.getInstance().addChatItemViewProvider(WKContentType.noRelation, new WKNoRelationProvider());
        WKMsgItemViewManager.getInstance().addChatItemViewProvider(WKContentType.msgPromptNewMsg, new WKPromptNewMsgProvider());
        WKMsgItemViewManager.getInstance().addChatItemViewProvider(WKContentType.WK_TEXT, new WKTextProvider());
        WKMsgItemViewManager.getInstance().addChatItemViewProvider(WKContentType.WK_IMAGE, new WKImageProvider());
        WKMsgItemViewManager.getInstance().addChatItemViewProvider(WKContentType.emptyView, new WKEmptyProvider());
        WKMsgItemViewManager.getInstance().addChatItemViewProvider(WKContentType.loading, new LoadingProvider());


        WKMsgItemViewManager.getInstance().addChatItemViewProvider(WKContentType.WK_VOICE, new WKVoiceProvider());
        WKMsgItemViewManager.getInstance().addChatItemViewProvider(WKContentType.WK_CARD, new WKCardProvider());
        WKMsgItemViewManager.getInstance().addChatItemViewProvider(WKContentType.WK_MULTIPLE_FORWARD, new WKMultiForwardProvider());

        // 设置消息长按选项
        EndpointManager.getInstance().setMethod(EndpointCategory.msgConfig + WKContentType.WK_TEXT, object -> new MsgConfig());
        EndpointManager.getInstance().setMethod(EndpointCategory.msgConfig + WKContentType.WK_IMAGE, object -> new MsgConfig());
        EndpointManager.getInstance().setMethod(EndpointCategory.msgConfig + WKContentType.WK_CARD, object -> new MsgConfig());

        EndpointManager.getInstance().setMethod(EndpointCategory.msgConfig + WKContentType.WK_VOICE, object -> new MsgConfig());
        EndpointManager.getInstance().setMethod(EndpointCategory.msgConfig + WKContentType.WK_MULTIPLE_FORWARD, object -> new MsgConfig());
        EndpointManager.getInstance().setMethod("uikit_sql", EndpointCategory.wkDBMenus, object -> new DBMenu("uikit_sql"));
        //注册消息长按菜单配置
        EndpointManager.getInstance().setMethod(EndpointCategory.msgConfig + WKContentType.WK_VOICE, object -> new MsgConfig(false, true, true, false, false));
        EndpointManager.getInstance().setMethod(EndpointCategory.msgConfig + WKContentType.typing, object -> new MsgConfig(false, false, false, false, false));
        EndpointManager.getInstance().setMethod("", EndpointCategory.wkChatPopupItem, 0, object -> {
            WKMsg wkMsg = (WKMsg) object;
            if (wkMsg.type == WKContentType.WK_TEXT) {
                return new ChatItemPopupMenu(R.mipmap.msg_copy, getContext().getString(R.string.copy), (msg, iConversationContext) -> {
                    WKTextContent textContent = (WKTextContent) msg.baseContentMsgModel;
                    String content = textContent.content;
                    if (msg.remoteExtra.contentEditMsgModel != null) {
                        content = msg.remoteExtra.contentEditMsgModel.getDisplayContent();
                    }
                    ClipboardManager cm = (ClipboardManager) iConversationContext.getChatActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData mClipData = ClipData.newPlainText("Label", content);
                    assert cm != null;
                    cm.setPrimaryClip(mClipData);
                    WKToastUtils.getInstance().showToastNormal(iConversationContext.getChatActivity().getString(R.string.copyed));
                });
            }
            return null;
        });
        EndpointManager.getInstance().setMethod("", EndpointCategory.chatShowBubble, new EndpointHandler() {
            @Override
            public Object invoke(Object object) {
                int type = (int) object;
                return type == WKContentType.WK_TEXT;
            }
        });
        //添加个人中心
        EndpointManager.getInstance().setMethod("personal_center_currency", EndpointCategory.personalCenter, 2, object -> new PersonalInfoMenu(R.mipmap.icon_setting, mContext.get().getString(R.string.currency), () -> {
            Intent intent = new Intent(mContext.get(), SettingActivity.class);
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
            mContext.get().startActivity(intent);
        }));
        EndpointManager.getInstance().setMethod("personal_center_new_msg_notice", EndpointCategory.personalCenter, 3, object -> new PersonalInfoMenu(R.mipmap.icon_notice, mContext.get().getString(R.string.new_msg_notice), () -> {
            Intent intent = new Intent(mContext.get(), MsgNoticesSettingActivity.class);
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
            mContext.get().startActivity(intent);
        }));
        EndpointManager.getInstance().setMethod("personal_center_web_login", EndpointCategory.personalCenter, 1000, object -> new PersonalInfoMenu(R.mipmap.icon_web_login, mContext.get().getString(R.string.web_login), () -> EndpointManager.getInstance().invoke("show_web_login_desc", mContext.get())));

        //添加通讯录
        EndpointManager.getInstance().setMethod(EndpointCategory.mailList + "_friends", EndpointCategory.mailList, 100, object -> new ContactsMenu("friend", R.mipmap.icon_new_friend, mContext.get().getString(R.string.new_friends), () -> {
            Intent intent = new Intent(mContext.get(), NewFriendsActivity.class);
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
            mContext.get().startActivity(intent);
        }));
        EndpointManager.getInstance().setMethod(EndpointCategory.mailList + "_groups", EndpointCategory.mailList, 90, object -> new ContactsMenu("group", R.mipmap.icon_groups, mContext.get().getString(R.string.saved_groups), () -> {
            Intent intent = new Intent(mContext.get(), SavedGroupsActivity.class);
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
            mContext.get().startActivity(intent);
        }));

        // 添加聊天工具栏菜单语音
        EndpointManager.getInstance().setMethod(EndpointCategory.wkChatToolBar + "_voice", EndpointCategory.wkChatToolBar, 97, object -> {
            IConversationContext iConversationContext = (IConversationContext) object;
            View voiceView = WKVoiceViewManager.getInstance().getVoiceView(iConversationContext);
            return new ChatToolBarMenu("wk_chat_toolbar_voice", R.mipmap.icon_chat_toolbar_voice, R.mipmap.icon_chat_toolbar_voice, voiceView, (isSelected, iConversationContext14) -> {
                // TODO: 1/1/21
            });
        });
        //聊天工具栏相册
        EndpointManager.getInstance().setMethod(EndpointCategory.wkChatToolBar + "_album", EndpointCategory.wkChatToolBar, 99, object -> new ChatToolBarMenu("wk_chat_toolbar_album", R.mipmap.icon_chat_toolbar_album, -1, null, (isSelected, iConversationContext1) -> {
            if (isSelected) {
                chooseIMG(iConversationContext1);
            }
        }));
        //聊天工具栏@
        EndpointManager.getInstance().setMethod(EndpointCategory.wkChatToolBar + "_remind", EndpointCategory.wkChatToolBar, 96, object
                -> {
            IConversationContext iConversationContext = (IConversationContext) object;
            if (iConversationContext.getChatChannelInfo().channelType == WKChannelType.PERSONAL)
                return null;
            return new ChatToolBarMenu("wk_chat_toolbar_remind", R.mipmap.icon_chat_toolbar_aite, -1, null, (isSelected, iConversationContext12) -> {

            });
        });

        // 添加聊天工具栏菜单
        EndpointManager.getInstance().setMethod(EndpointCategory.wkChatToolBar + "_more", EndpointCategory.wkChatToolBar, 40, object -> {
            IConversationContext iConversationContext = (IConversationContext) object;
            View moreView = FaceManger.getInstance().getFunctionView(iConversationContext, chatFunctionMenu -> chatFunctionMenu.iChatFunctionCLick.onClick(iConversationContext));

            return new ChatToolBarMenu("wk_chat_toolbar_more", R.mipmap.icon_chat_toolbar_more, R.mipmap.icon_chat_toolbar_more, moreView, (isSelected, iConversationContext13) -> {

            });
        });
        //添加聊天功能面板
        EndpointManager.getInstance().setMethod(EndpointCategory.chatFunction + "_chooseImg", EndpointCategory.chatFunction, 100, object -> new ChatFunctionMenu("chooseImg", R.mipmap.icon_func_album, mContext.get().getString(R.string.image), this::chooseIMG));
        EndpointManager.getInstance().setMethod(EndpointCategory.chatFunction + "_chooseCard", EndpointCategory.chatFunction, 95, object -> new ChatFunctionMenu("chooseCard", R.mipmap.icon_func_card, mContext.get().getString(R.string.card), IConversationContext::sendCardMsg));

        //添加tab页
        EndpointManager.getInstance().setMethod(EndpointCategory.tabMenus + "_start_chat", EndpointCategory.tabMenus, 200, object -> new PopupMenuItem(mContext.get().getString(R.string.start_group_chat), R.mipmap.menu_chats, () -> {
            Intent intent = new Intent(mContext.get(), ChooseContactsActivity.class);
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
            mContext.get().startActivity(intent);
        }));
        EndpointManager.getInstance().setMethod(EndpointCategory.tabMenus + "_add_friends", EndpointCategory.tabMenus, 99, object -> new PopupMenuItem(mContext.get().getString(R.string.add_friends), R.mipmap.menu_invite, () -> {
            Intent intent = new Intent(mContext.get(), AddFriendsActivity.class);
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
            mContext.get().startActivity(intent);
        }));

        //显示聊天页面
        EndpointManager.getInstance().setMethod(EndpointSID.chatView, object -> {
            if (object instanceof ChatViewMenu chatViewMenu) {
                if (!TextUtils.isEmpty(chatViewMenu.channelID)) {
                    WKIMUtils.getInstance().startChatActivity(chatViewMenu);
                }
            }
            return null;
        });

        //撤回消息
        EndpointManager.getInstance().setMethod("chat_withdraw_msg", object -> {
            final WithdrawMsgMenu withdrawMsgMenu = (WithdrawMsgMenu) object;
            if (withdrawMsgMenu != null) {
                MsgModel.getInstance().revokeMsg(withdrawMsgMenu.message_id, withdrawMsgMenu.channel_id, withdrawMsgMenu.channel_type, withdrawMsgMenu.client_msg_no, (code, msg) -> {
                    if (code != HttpResponseCode.success) {
                        WKToastUtils.getInstance().showToastNormal(msg);
                        //  WKIM.getInstance().getMsgManager().updateMsgRevokeWithMessageID(withdrawMsgMenu.message_id, 1);
//                        WKIM.getInstance().getMessageManager().deleteMsgByClientMsgNo(client_msg_no);
                    }
                });
            }
            return null;
        });
        EndpointManager.getInstance().setMethod("str_delete_msg", object -> {
            WKMsg msg = (WKMsg) object;
            if (msg != null) {
                List<WKMsg> list = new ArrayList<>();
                list.add(msg);
                MsgModel.getInstance().deleteMsg(list, null);
            }
            return null;
        });
        //选择会话
        EndpointManager.getInstance().setMethod("chat_show_choose_chat", object -> {
            ChooseChatMenu messageContent = (ChooseChatMenu) object;
            Intent intent = new Intent(mContext.get(), ChooseChatActivity.class);
            intent.putExtra("isChoose", true);
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
            mContext.get().startActivity(intent);
            WKUIKitApplication.this.messageContentList = messageContent.list;
            WKUIKitApplication.this.chooseChatCallBack = messageContent.mChatChooseContacts;
            return null;
        });

        //处理扫一扫结果
        EndpointManager.getInstance().setMethod("", EndpointCategory.wkScan, object -> new ScanResultMenu(hashMap -> {
            String type = Objects.requireNonNull(hashMap.get("type")).toString();
            if (type.equals("userInfo")) {
                JSONObject dataJson = (JSONObject) hashMap.get("data");
                if (dataJson != null && dataJson.has("uid")) {
                    String uid = dataJson.optString("uid");
                    String verCode = dataJson.optString("vercode");
                    if (!TextUtils.isEmpty(uid)) {
                        Intent intent = new Intent(mContext.get(), UserDetailActivity.class);
                        intent.putExtra("uid", uid);
                        intent.putExtra("vercode", verCode);
                        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                        mContext.get().startActivity(intent);
                    }
                }
                return true;
            } else return false;

        }));
        //选择联系人
        EndpointManager.getInstance().setMethod("choose_contacts", object -> {
            Intent intent = new Intent(mContext.get(), ChooseContactsActivity.class);
            intent.putExtra("type", 2);
            this.contactsMenu = (ChooseContactsMenu) object;
            if (contactsMenu != null) {
                intent.putParcelableArrayListExtra("defaultSelected", (ArrayList<? extends Parcelable>) contactsMenu.defaultSelected);
                intent.putExtra("isShowSaveLabelDialog", contactsMenu.isShowSaveLabelDialog);
                if (contactsMenu.defaultSelected != null && contactsMenu.defaultSelected.size() > 0 && !contactsMenu.isCanDeselect) {
                    String unSelectUids = "";
                    for (int i = 0, size = contactsMenu.defaultSelected.size(); i < size; i++) {
                        if (TextUtils.isEmpty(unSelectUids)) {
                            unSelectUids = contactsMenu.defaultSelected.get(i).channelID;
                        } else
                            unSelectUids = unSelectUids + "," + contactsMenu.defaultSelected.get(i).channelID;
                    }
                    intent.putExtra("unSelectUids", unSelectUids);
                }
            }
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
            mContext.get().startActivity(intent);
            return null;
        });
        EndpointManager.getInstance().setMethod("exit_login", object -> {
            exitLogin(0);
            return null;
        });
        //查看用户详情
        EndpointManager.getInstance().setMethod(EndpointSID.userDetailView, object -> {
            UserDetailMenu wkUserDetailMenu = (UserDetailMenu) object;
            if (wkUserDetailMenu != null) {
                if (!TextUtils.isEmpty(wkUserDetailMenu.uid)) {
                    Intent intent = new Intent(mContext.get(), UserDetailActivity.class);
                    intent.putExtra("uid", wkUserDetailMenu.uid);
                    if (!TextUtils.isEmpty(wkUserDetailMenu.groupID)) {
                        intent.putExtra("groupID", wkUserDetailMenu.groupID);
                    }
                    wkUserDetailMenu.context.startActivity(intent);
                }

            }
            return null;
        });

        EndpointManager.getInstance().setMethod("show_tab_main", object -> {
            Intent intent = new Intent(mContext.get(), TabActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.get().startActivity(intent);
            return null;
        });
        //监听登录状态
        EndpointManager.getInstance().setMethod("", EndpointCategory.loginMenus, object -> new LoginMenu(() -> {
            WKSharedPreferencesUtil.getInstance().putInt("wk_lock_screen_pwd_count", 5);
            WKSharedPreferencesUtil.getInstance().putBoolean("sync_friend", true);
            //初始化im
            WKUIKitApplication.getInstance().initIM();
            //初始化密钥
//            WKIM.getInstance().getSignalProtocolManager().init();
            UserInfoEntity userInfo = WKConfig.getInstance().getUserInfo();
            if (userInfo != null) {
                WKIM.getInstance().getCMDManager().setRSAPublicKey(userInfo.rsa_public_key);
                WKIM.getInstance().getChannelManager().updateAvatarCacheKey(userInfo.uid, WKChannelType.PERSONAL, UUID.randomUUID().toString().replaceAll("-", ""));
            }
            Intent intent = new Intent(mContext.get(), TabActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.get().startActivity(intent);
            startChat();
            ProhibitWordModel.Companion.getInstance().sync();
            MsgModel.getInstance().deleteFlameMsg();
            //更新文件传输助手时间
            // WKIM.getInstance().getConversationManager().updateLastMsgTime(WKSystemAccount.system_file_helper, WKChannelType.PERSONAL, TimeUtils.getInstance().getCurrentSeconds());
        }));

        EndpointManager.getInstance().setMethod("syncExtraMsg", object -> {
            if (object != null) {
                WKChannel channel = (WKChannel) object;
                MsgModel.getInstance().syncExtraMsg(channel.channelID, channel.channelType);
            }
            return null;
        });

        EndpointManager.getInstance().setMethod("deleteRemoteMsg", object -> {
            if (object instanceof String clientMsgNo) {
                WKMsg msg = WKIM.getInstance().getMsgManager().getWithClientMsgNO(clientMsgNo);
                if (msg != null) {
                    List<WKMsg> list = new ArrayList<>();
                    list.add(msg);
                    MsgModel.getInstance().deleteMsg(list, null);
                }
            }
            return null;
        });
    }

    public void sendChooseChatBack(List<WKChannel> list) {
        if (chooseChatCallBack != null) {
            chooseChatCallBack.iChoose.onResult(list);
            chooseChatCallBack = null;
        }
    }

    public List<WKMessageContent> getMessageContentList() {
        return messageContentList;
    }

    public void setChooseContactsBack(List<WKChannel> list) {
        if (contactsMenu != null) {
            contactsMenu.iChooseBack.onBack(list);
            contactsMenu = null;
        }
    }

    private ChatChooseContacts chooseChatCallBack;
    private ChooseContactsMenu contactsMenu;
    private List<WKMessageContent> messageContentList;

    public void exitLogin(int from) {
        MsgModel.getInstance().stopTimer();
        EndpointManager.getInstance().invoke("push_unregister_push", null);
        WKConfig.getInstance().clearInfo();
        WKIM.getInstance().getConnectionManager().disconnect(true);
        ActManagerUtils.getInstance().clearAllActivity();
        EndpointManager.getInstance().invoke("exit_rtc", null);
        EndpointManager.getInstance().invoke("main_show_home_view", from);
        //关闭UI层数据库
        WKBaseApplication.getInstance().closeDbHelper();

    }

    private void chooseIMG(IConversationContext iConversationContext) {
        String permissionStr = Manifest.permission.READ_EXTERNAL_STORAGE;
        if (Build.VERSION.SDK_INT >= 33) {
            permissionStr = Manifest.permission.READ_MEDIA_IMAGES;
        }
        String desc = String.format(iConversationContext.getChatActivity().getString(R.string.album_permissions_desc), iConversationContext.getChatActivity().getString(R.string.app_name));
        WKPermissions.getInstance().checkPermissions(new WKPermissions.IPermissionResult() {
            @Override
            public void onResult(boolean result) {
                if (result) {
                    GlideUtils.getInstance().chooseIMG(iConversationContext.getChatActivity(), 9, true, ChooseMimeType.all, true, new GlideUtils.ISelectBack() {
                        @Override
                        public void onBack(List<ChooseResult> paths) {
                            if (paths.size() == 1 && paths.get(0).model == ChooseResultModel.video) {
                                WKVideoContent videoContent = new WKVideoContent();
                                videoContent.coverLocalPath = WKMediaFileUtils.getInstance().getVideoCover(paths.get(0).path);
                                videoContent.localPath = paths.get(0).path;
                                videoContent.second = WKMediaFileUtils.getInstance().getVideoTime(paths.get(0).path) / 1000;
                                videoContent.size = WKFileUtils.getInstance().getFileSize(paths.get(0).path);
                                iConversationContext.sendMessage(videoContent);
                                return;
                            }

                            for (int i = 0, size = paths.size(); i < size; i++) {
                                String path = paths.get(i).path;
                                if (paths.get(i).model == ChooseResultModel.video) {
                                    WKVideoContent videoContent = new WKVideoContent();
                                    videoContent.coverLocalPath = WKMediaFileUtils.getInstance().getVideoCover(path);
                                    videoContent.localPath = path;
                                    videoContent.second = WKMediaFileUtils.getInstance().getVideoTime(path) / 1000;
                                    videoContent.size = WKFileUtils.getInstance().getFileSize(path);
                                    iConversationContext.sendMessage(videoContent);
                                } else {
                                    if (WKFileUtils.getInstance().isGif(path)) {
                                        WKGifContent mGifContent = new WKGifContent();
                                        mGifContent.format = "gif";
                                        mGifContent.localPath = path;
                                        Bitmap bitmap = BitmapFactory.decodeFile(path);
                                        if (bitmap != null) {
                                            mGifContent.height = bitmap.getHeight();
                                            mGifContent.width = bitmap.getWidth();
                                        }
                                        iConversationContext.sendMessage(mGifContent);
                                    } else {
                                        WKImageContent imageContent = new WKImageContent(path);
                                        iConversationContext.sendMessage(imageContent);
                                    }
                                }

                            }
                        }

                        @Override
                        public void onCancel() {

                        }
                    });
                }
            }

            @Override
            public void clickResult(boolean isCancel) {
            }
        }, iConversationContext.getChatActivity(), desc, permissionStr);
    }
}