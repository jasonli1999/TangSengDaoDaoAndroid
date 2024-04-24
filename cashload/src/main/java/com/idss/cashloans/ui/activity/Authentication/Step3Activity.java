package com.idss.cashloans.ui.activity.Authentication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.azhon.basic.base.BaseActivity;
import com.idss.cashloans.R;
import com.idss.cashloans.api.moudle.Certification;
import com.idss.cashloans.api.utils.LogUtil;
import com.idss.cashloans.databinding.ActivityStep3Binding;
import com.idss.cashloans.ui.ViewMoudle.Step3ActivityVM;

import java.util.ArrayList;
import java.util.List;


/**
 * 紧急联系人
 */
public class Step3Activity extends BaseActivity<Step3ActivityVM, ActivityStep3Binding> {
    private final int PICK_CONTACT_REQUEST = 1001;
    private final int PICK_CONTACT_REQUEST2 = 1002;
    private static final String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_CONTACTS",
            "android.permission.WRITE_CONTACTS",
            "android.permission.GET_ACCOUNTS"};
    private List<Certification.MemberEmergencyContactListDTO> memberEmergencyContactListDTOList = new ArrayList<>();
    private Certification.MemberEmergencyContactListDTO memberEmergencyContactListDTO;

    private String relationship1, relationship2;

    @Override
    protected void onCreate(View view, Bundle savedInstanceState) {
        binding.ivBack.setOnClickListener(v -> finish());
        //申请权限
        ActivityCompat.requestPermissions(Step3Activity.this, PERMISSIONS_STORAGE, PICK_CONTACT_REQUEST);
    }

    @Override
    protected void initView() {
        binding.radiogroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_1) {
                    relationship1 = "父母";
                } else if (checkedId == R.id.radio_2) {
                    relationship1 = "朋友";
                } else if (checkedId == R.id.radio_3) {
                    relationship1 = "同事";
                } else if (checkedId == R.id.radio_4) {
                    relationship1 = "亲属";
                }
            }
        });

        binding.radiogroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_1) {
                    relationship2 = "父母";
                } else if (checkedId == R.id.radio_2) {
                    relationship2 = "朋友";
                } else if (checkedId == R.id.radio_3) {
                    relationship2 = "同事";
                } else if (checkedId == R.id.radio_4) {
                    relationship2 = "亲属";
                }
            }
        });


        binding.ivContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 打开联系人选择器
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(intent, PICK_CONTACT_REQUEST);
            }
        });

        binding.ivContact2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 打开联系人选择器
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(intent, PICK_CONTACT_REQUEST2);
            }
        });

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.certification3(Step3Activity.this, memberEmergencyContactListDTOList);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_CONTACT_REQUEST && resultCode == RESULT_OK) {
            Uri contactUri = data.getData();
            String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
            Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String name = cursor.getString(nameIndex);
                String number = cursor.getString(numberIndex);
                // 在这里可以对选择的联系人信息进行处理
                LogUtil.e("Selected contact: " + name + " - " + number);
                binding.contact1.setTextColor(getResources().getColor(R.color.black));
                binding.contact1.setText(name);
                binding.tvPhone1.setText(number);

                memberEmergencyContactListDTO = new Certification.MemberEmergencyContactListDTO();
                memberEmergencyContactListDTO.setContactPerson(name);
                memberEmergencyContactListDTO.setPhone(number);
                memberEmergencyContactListDTO.setRelationship(relationship1);
                memberEmergencyContactListDTOList.add(memberEmergencyContactListDTO);

                cursor.close();
            }


        }
        if (requestCode == PICK_CONTACT_REQUEST2 && resultCode == RESULT_OK) {
            Uri contactUri = data.getData();
            String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
            Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String name = cursor.getString(nameIndex);
                String number = cursor.getString(numberIndex);
                // 在这里可以对选择的联系人信息进行处理
                LogUtil.e("Selected contact: " + name + " - " + number);
                binding.contact2.setText(name);
                binding.contact2.setTextColor(getResources().getColor(R.color.black));
                binding.tvPhone2.setText(number);
                memberEmergencyContactListDTO = new Certification.MemberEmergencyContactListDTO();
                memberEmergencyContactListDTO.setContactPerson(name);
                memberEmergencyContactListDTO.setPhone(number);
                memberEmergencyContactListDTO.setRelationship(relationship2);
                memberEmergencyContactListDTOList.add(memberEmergencyContactListDTO);
                cursor.close();
            }


        }
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            //权限的申请结果返回
            case PICK_CONTACT_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    //未授权
                    Toast.makeText(this, "读取通讯录权限被拒绝,这将导致审核失败！", Toast.LENGTH_SHORT).show();
                }
            }
            break;
        }
    }


}