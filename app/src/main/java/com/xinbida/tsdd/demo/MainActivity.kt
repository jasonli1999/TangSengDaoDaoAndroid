package com.xinbida.tsdd.demo

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.view.View
import androidx.core.content.ContextCompat
import com.chat.base.SharePreferencesUtil
import com.chat.base.WKBaseApplication
import com.chat.base.base.WKBaseActivity
import com.chat.base.config.WKApiConfig
import com.chat.base.config.WKConfig
import com.chat.base.config.WKSharedPreferencesUtil
import com.chat.base.endpoint.EndpointManager
import com.chat.base.endpoint.entity.UpdateBaseAPIMenu
import com.chat.base.ui.components.NormalClickableContent
import com.chat.base.ui.components.NormalClickableSpan
import com.chat.base.utils.WKDialogUtils
import com.chat.login.ui.PerfectUserInfoActivity
import com.chat.login.ui.WKLoginActivity
import com.chat.uikit.TabActivity
import com.fm.openinstall.OpenInstall
import com.fm.openinstall.listener.AppWakeUpAdapter
import com.fm.openinstall.model.AppData
import com.google.gson.Gson
import com.xinbida.tsdd.demo.databinding.ActivityMainBinding
import com.xinbida.tsdd.demo.push.InviteDataModel
import com.xinbida.wukongim.WKIM
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException
import kotlin.random.Random


class MainActivity : WKBaseActivity<ActivityMainBinding>() {

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT !== 0) {
            finish()
            return
        }
    }
    override fun initView() {
        super.initView()
//        val isShowDialog: Boolean =
//            WKSharedPreferencesUtil.getInstance().getBoolean("show_agreement_dialog")
//        if (isShowDialog) {
//            showDialog()
//        } else gotoApp()
        // 获取拉起参数
        OpenInstall.getWakeUp(intent, wakeUpAdapter)


        // 获取参数，处理业务
        OpenInstall.getInstall({ appData, error ->
            if (error != null && error.shouldRetry()) {
                // 未获取到数据，可以重试
                LogUtil.e("OpenInstall2:" + "error")
            } else {
                try {
                    // 获取渠道数据
                    val channelCode = appData!!.getChannel()

                    // 获取H5落地页传递的数据
                    val bindData = appData!!.getData()
                    // 根据获取到的数据处理业务
                    LogUtil.e("OpenInstall2:channelCode: $channelCode")
                    LogUtil.e("OpenInstall2:_bindData: $bindData")

                    SharePreferencesUtil.addString(this@MainActivity, "inviteCode",channelCode)
                    LogUtil.e("OpenInstall2:channelCode: ${SharePreferencesUtil.getString(this@MainActivity,"inviteCode","")}")
                    if (!bindData.isEmpty()) {
                        val inviteDataModel: InviteDataModel = Gson().fromJson(bindData, InviteDataModel::class.java)

                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }, 8)
    }


    override fun initData() {
        super.initData()
        getIpAddress()
    }

    var wakeUpAdapter: AppWakeUpAdapter = object : AppWakeUpAdapter() {
        override fun onWakeUp(appData: AppData) {
            // 打印数据便于调试
            LogUtil.e("OpenInstall1getWakeUp : wakeupData = $appData")
            //  获取渠道编号参数
            val channelCode = appData.getChannel()
            // 获取自定义参数
            val bindData = appData.getData()
            LogUtil.e("channelCode:$channelCode")
            LogUtil.e("bindData:$bindData")
        }
    }



    /**
     * 测试okhttp的get方法
     */
    fun getIpAddress() {
        val client = OkHttpClient()
        val request = okhttp3.Request.Builder()
            .url("https://for9sy8wy156-1321341241.cos.accelerate.myqcloud.com/forsiyan.json")
            .build()
        val call = client.newCall(request)
        //异步请求，enqueue方法不会阻塞后续代码的执行
        call.enqueue(object : Callback {
            //请求失败调用
            override fun onFailure(call: Call, e: IOException) {
                LogUtil.e("=======请求接口失败==============")
            }

            //请求结束调用（意味着与服务器的通信成功）
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    response.body?.let {
                        val lineModel: LinesModel = Gson().fromJson("{" + "lines:" + it.string() + "}", LinesModel::class.java
                        )
                        // 生成一个0到指定上限之间的随机整数（不包括上限）：
                        val random = Random.nextInt(lineModel.lines.size)
                        LogUtil.e(random.toString())

                        if(lineModel.lines.get(random).address.startsWith("http")){
                            val apiMenu = UpdateBaseAPIMenu( lineModel.lines.get(random).address, "")
                            LogUtil.e(lineModel.lines.get(random).address)
                            EndpointManager.getInstance().invoke("update_base_url", apiMenu)
                        }else{
                            val apiMenu = UpdateBaseAPIMenu("http://" + lineModel.lines.get(random).address, "8090")
                            LogUtil.e(lineModel.lines.get(random).address)
                            EndpointManager.getInstance().invoke("update_base_url", apiMenu)
                        }
                    }

                    WKSharedPreferencesUtil.getInstance().putBoolean("show_agreement_dialog", false)
                    WKBaseApplication.getInstance().init(
                        WKBaseApplication.getInstance().packageName,
                        WKBaseApplication.getInstance().application
                    )
                    gotoApp()
                }
            }
        })

    }


    private fun gotoApp() {
        if (!TextUtils.isEmpty(WKConfig.getInstance().token)) {
            if (TextUtils.isEmpty(WKConfig.getInstance().userInfo.name)) {
                startActivity(Intent(this@MainActivity, PerfectUserInfoActivity::class.java))
            } else {
                val publicRSAKey: String =
                    WKIM.getInstance().cmdManager.rsaPublicKey
                if (TextUtils.isEmpty(publicRSAKey)) {
//                    val intent = Intent(this@MainActivity, ThirdLoginActivity::class.java)
//                    intent.putExtra("from", getIntent().getIntExtra("from", 0))
//                    startActivity(intent)
                    val intent = Intent(this@MainActivity, WKLoginActivity::class.java)
                    intent.putExtra("from", getIntent().getIntExtra("from", 0))
                    startActivity(intent)
                } else {
                    startActivity(Intent(this@MainActivity, TabActivity::class.java))
                }
            }
        } else {
            val intent = Intent(this@MainActivity, WKLoginActivity::class.java)
            intent.putExtra("from", getIntent().getIntExtra("from", 0))
            startActivity(intent)
//            val intent = Intent(this@MainActivity, ThirdLoginActivity::class.java)
//            intent.putExtra("from", getIntent().getIntExtra("from", 0))
//            startActivity(intent)
        }
        finish()
    }

    private fun showDialog() {
        val content = getString(R.string.dialog_content)
        val linkSpan = SpannableStringBuilder()
        linkSpan.append(content)
        val userAgreementIndex = content.indexOf(getString(R.string.main_user_agreement))
        linkSpan.setSpan(
            NormalClickableSpan(
                true,
                ContextCompat.getColor(this, R.color.blue),
                NormalClickableContent(NormalClickableContent.NormalClickableTypes.Other, ""),
                object : NormalClickableSpan.IClick {
                    override fun onClick(view: View) {
                        showWebView(WKApiConfig.baseWebUrl + "user_agreement.html")
                    }
                }), userAgreementIndex, userAgreementIndex + 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        val privacyPolicyIndex = content.indexOf(getString(R.string.main_privacy_policy))
        linkSpan.setSpan(
            NormalClickableSpan(true,
                ContextCompat.getColor(this, R.color.blue),
                NormalClickableContent(NormalClickableContent.NormalClickableTypes.Other, ""),
                object : NormalClickableSpan.IClick {
                    override fun onClick(view: View) {
                        WKApiConfig.baseWebUrl + "privacy_policy.html"
                    }
                }), privacyPolicyIndex, privacyPolicyIndex + 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        WKDialogUtils.getInstance().showDialog(
            this,
            getString(R.string.dialog_title),
            linkSpan,
            false,
            getString(R.string.disagree),
            getString(R.string.agree),
            0,
            0
        ) { index ->
            if (index == 1) {
                WKSharedPreferencesUtil.getInstance().putBoolean("show_agreement_dialog", false)
                WKBaseApplication.getInstance().init(
                    WKBaseApplication.getInstance().packageName,
                    WKBaseApplication.getInstance().application
                )
                gotoApp()
            } else {
                finish()
            }
        }
    }
}

