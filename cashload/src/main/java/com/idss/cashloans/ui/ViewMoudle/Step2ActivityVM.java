package com.idss.cashloans.ui.ViewMoudle;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.azhon.basic.lifecycle.BaseViewModel;
import com.idss.cashloans.api.BaseApplication;
import com.idss.cashloans.api.HttpCallback;
import com.idss.cashloans.api.HttpRequest;
import com.idss.cashloans.api.moudle.AppBeans;
import com.idss.cashloans.api.moudle.Certification;
import com.idss.cashloans.api.moudle.FaceVerifyModel;
import com.idss.cashloans.api.utils.AppInfoUtils;
import com.idss.cashloans.api.utils.ContactsInfo;

import java.util.ArrayList;
import java.util.List;

public class Step2ActivityVM extends BaseViewModel {

    /**
     * ---------------------------------------------读取APP列表---------------------------------------------
     */
    protected MutableLiveData<List<AppBeans>> appList = new MutableLiveData<>();

    private final AppInfoUtils contactNumUtils = new AppInfoUtils();

    public void getApplist(Context context) throws PackageManager.NameNotFoundException {
        appList.setValue(contactNumUtils.getInstalledApps(context));
    }

    /**
     * 读取APP列表
     */
    public MutableLiveData<List<AppBeans>> getAppList() {
        return appList;
    }


    /**
     * ---------------------------------------------读取通讯录---------------------------------------------
     */

    protected MutableLiveData<ArrayList<ContactsInfo>> ContactsInfo = new MutableLiveData<>();

    /**
     * 读取通讯录
     */
    public void getContactsList(Context context) {
        try {
            ContactsInfo.setValue(AppInfoUtils.loadContacts(context));
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    /**
     * 联系人列表
     */
    public MutableLiveData<ArrayList<ContactsInfo>> getContactsInfo() {
        return ContactsInfo;
    }


    /**
     * ---------------------------------------------读取短信列表---------------------------------------------
     */
    protected MutableLiveData<List<Certification.MemberSmsListDTO>> memberSmsList = new MutableLiveData<>();

    public MutableLiveData<List<Certification.MemberSmsListDTO>> getSmsList() {
        return memberSmsList;
    }

    public void getSmsInPhone(Context context) {
        memberSmsList.setValue(AppInfoUtils.getSmsInPhone(context));
    }


    /**
     * ---------------------------------------------获取通话记录---------------------------------------------
     */

    protected MutableLiveData<List<Certification.MemberCallLogListDTO>> memberCallLogList = new MutableLiveData<>();

    /**
     * 获取通话记录
     */

    public MutableLiveData<List<Certification.MemberCallLogListDTO>> getMemberCallLogList() {
        return memberCallLogList;
    }


    public void getCallLogDataList(Context context) {
        memberCallLogList.setValue(AppInfoUtils.getCallLogDataList(context));
    }


    /**
     * 信息认证
     */
    public void certification2(Activity activity, Certification certification) {
        showDialog.setValue(true);
        HttpRequest.getInstance().certification2("certification2", certification, new HttpCallback<FaceVerifyModel>() {
            @Override
            public void onSuccess(FaceVerifyModel data) {
                showDialog.setValue(false);
                activity.finish();
            }

            @Override
            public void onFailure(String msgCode, String errorMsg) {
                showDialog.setValue(false);
                Toast.makeText(BaseApplication.getAppContext(), errorMsg, Toast.LENGTH_SHORT).show();
            }
        });

    }

}
