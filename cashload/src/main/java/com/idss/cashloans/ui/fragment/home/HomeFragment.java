package com.idss.cashloans.ui.fragment.home;

import android.content.Intent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import androidx.lifecycle.Observer;

import com.azhon.basic.base.BaseFragment;
import com.idss.cashloans.R;
import com.idss.cashloans.api.Constants;
import com.idss.cashloans.api.moudle.AwaitingRepaymentModel;
import com.idss.cashloans.api.moudle.BorrowingModel;
import com.idss.cashloans.api.utils.AppUtils;
import com.idss.cashloans.api.utils.SharePreferencesUtil;
import com.idss.cashloans.api.utils.SoundUtils;
import com.idss.cashloans.databinding.FragmentHomeBinding;
import com.idss.cashloans.ui.activity.Authentication.AuthenticationActivity;
import com.idss.cashloans.ui.activity.aboutbills.AwaitingRepaymentActivity;
import com.idss.cashloans.ui.activity.aboutbills.LoanActivity;
import com.idss.cashloans.ui.activity.aboutbills.LoanProgressActivity;
import com.idss.cashloans.ui.activity.login.LoginActivity;
import com.idss.cashloans.ui.activity.webview.ChatServiceActivity;
import com.idss.cashloans.ui.dialog.ExitDialog;

/**
 * 借款首页
 */
public class HomeFragment extends BaseFragment<HomeViewModel, FragmentHomeBinding> {
    private int AuthenticationStatue = 999;  //认证状态
    private int latestOrderStatus = 999;  //订单状态

    @Override
    protected void initView(View view) {
        binding.btService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 *  播放音頻
                 */
                SoundUtils soundUtils = new SoundUtils();
                soundUtils.playAudio_point(requireActivity());
                startActivity(new Intent(getActivity(), ChatServiceActivity.class));
            }
        });
        binding.btOrderOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**播放音頻 */
                SoundUtils soundUtils = new SoundUtils();
                soundUtils.playAudio_point(requireActivity());

                if (!SharePreferencesUtil.getBoolean(requireActivity(), Constants.IF_AUTHENTICATION, false)) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    return;
                }
                /**
                 * /apid/member/latestOrderStatus 查订单状态0 代表没有订单 就查获取认证的接口 认证接口为0为认证完就查额度，1-5为继续认证；
                 * 订单状态为1,就是有订单 查订单接口/apid/member/myBorrowings；
                 * 订单状态2就是待还款，查还款信息 /apid/member/awaitingRepayment
                 */

                switch (latestOrderStatus) {
                    case 0:
                        //认账完成就借款，没完成就继续认证
                        if (AuthenticationStatue == 0) {
                            startActivity(new Intent(getActivity(), LoanActivity.class));
                            SharePreferencesUtil.addString(requireActivity(), Constants.USER_MEIQIA_STATUE, "认证完成等待借款");
                        } else {
                            startActivity(new Intent(getActivity(), AuthenticationActivity.class));
                            SharePreferencesUtil.addString(requireActivity(), Constants.USER_MEIQIA_STATUE, "认证没完成");
                        }
                        break;
                    case 1:
                        //查看订单状态
                        startActivity(new Intent(getActivity(), LoanProgressActivity.class));
                        SharePreferencesUtil.addString(requireActivity(), Constants.USER_MEIQIA_STATUE, "等待审批");
                        break;
                    case 2:
                        //要去还款
                        startActivity(new Intent(getActivity(), AwaitingRepaymentActivity.class));
                        SharePreferencesUtil.addString(requireActivity(), Constants.USER_MEIQIA_STATUE, "待还款");
                        break;
                }

            }
        });
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExitDialog exitDialog = new ExitDialog(requireActivity());
                exitDialog.show(requireActivity().getSupportFragmentManager(), "exitDialog");
            }
        });


        binding.tvName.setText("Hello," + "想借款请直接申请。");
        binding.tvAppversion.setText("版本号: " + AppUtils.getVersionName(getActivity()));

        TranslateAnimation translateAnimation = (TranslateAnimation) AnimationUtils.loadAnimation(getActivity(), R.anim.translate);
        binding.circleview.startAnimation(translateAnimation);
    }


    @Override
    protected void initData() {
        /**
         * /apid/member/latestOrderStatus 查订单状态0 代表没有订单 就查获取认证的接口 认证接口为0为认证完就查额度，1-5为继续认证；
         * 获取最新订单状态（0 可借款 1审核中 2待还款）
         * 订单状态为1,就是有订单 查订单接口/apid/member/myBorrowings；
         * 订单状态2就是待还款，查还款信息 /apid/member/awaitingRepayment
         *
         */
        viewModel.getlatestOrderStatus().observe(this, integer -> {
            String username = SharePreferencesUtil.getString(context, Constants.PHONE, "");
            binding.tvName.setText("Hello," + username.substring(0, 3) + "******" + username.substring(8, 11));

            latestOrderStatus = integer;
            switch (integer) {
                case 0:
                    viewModel.requestCertification(getActivity());
                    break;
                case 1:
                    binding.btOrderOk.setText("查看进度");
                    binding.tvStatu.setText("借款资格认证通过");
                    SharePreferencesUtil.addString(requireActivity(), Constants.USER_MEIQIA_STATUE, "借款资格认证通过");
                    //获取我的借款
                    viewModel.getmyBorrowings();

                    break;
                case 2:
                    binding.btOrderOk.setText("我要还款");
                    binding.tvStatu.setText("借款资格认证通过");
                    SharePreferencesUtil.addString(requireActivity(), Constants.USER_MEIQIA_STATUE, "待还款");
                    viewModel.getawaitingRepayment();
                    break;

            }
        });


        //数据请求成功通知,判断是认证的第几步
        viewModel.getAuthSteps().observe(this, integer -> {
            AuthenticationStatue = integer;
            switch (integer) {
                case 0:
                    binding.tvLoanStatue.setText("想借就借");
                    binding.tvLoans.setText(" ¥ 100000");
                    binding.tvLoanDate.setText("轻轻松松,额度到手");

                    binding.btOrderOk.setText("立即提现");
                    binding.tvStatu.setText("借款资格认证通过");
                    SharePreferencesUtil.addString(requireActivity(), Constants.USER_MEIQIA_STATUE, "借款资格认证通过");
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    binding.tvLoanStatue.setText("想借就借");
                    binding.tvLoans.setText(" ¥ 100000");
                    binding.tvLoanDate.setText("轻轻松松,额度到手");

                    binding.btOrderOk.setText("点我认证");
                    binding.tvStatu.setText("认证尚未完成");
                    SharePreferencesUtil.addString(requireActivity(), Constants.USER_MEIQIA_STATUE, "认证尚未完成");
                    break;

            }
        });


        viewModel.getAwaitingRepaymentModel().observe(this, new Observer<AwaitingRepaymentModel>() {
            @Override
            public void onChanged(AwaitingRepaymentModel awaitingRepaymentModel) {
                binding.tvLoanStatue.setText("待还金额");
                SharePreferencesUtil.addString(requireActivity(), Constants.USER_MEIQIA_STATUE, "待还款");
                binding.tvLoans.setText(" ¥ " + awaitingRepaymentModel.getData().getApplicationAmount());
                binding.tvLoanDate.setText("还款日期: " + awaitingRepaymentModel.getData().getExpirationTime());
            }
        });


        viewModel.getBorrowingModelMutableLiveData().observe(this, new Observer<BorrowingModel>() {
            @Override
            public void onChanged(BorrowingModel borrowingModel) {
                binding.tvLoans.setText(" ¥ " + borrowingModel.getData().getApplicationAmount());
                //0 未提交 1已提交未审核 2初审通过待复审 3复审中  4黑名单 5复审通过待终审 6终审中 7被拒 8终审通过放款中 9放款成功待还款 10已结清 11放款失败 12已逾期")
                switch (borrowingModel.getData().getLoanStatus()) {

                    case 0:
                        binding.tvLoanStatue.setText("想借就借");
                        binding.tvLoanDate.setText("恭喜获得提现资格");
                        SharePreferencesUtil.addString(requireActivity(), Constants.USER_MEIQIA_STATUE, "获得提现资格");
                        break;
                    case 1:
                        binding.tvLoanStatue.setText("已提交金额");
                        binding.tvLoanDate.setText("等待审核中");
                        SharePreferencesUtil.addString(requireActivity(), Constants.USER_MEIQIA_STATUE, "已提交金额");
                        break;
                    case 2:
                        binding.tvLoanStatue.setText("初审通过待复审");
                        binding.tvLoanDate.setText("审核中,请耐心等待");
                        SharePreferencesUtil.addString(requireActivity(), Constants.USER_MEIQIA_STATUE, "初审通过待复审");
                        break;
                    case 3:
                        binding.tvLoanStatue.setText("已提交金额");
                        binding.tvLoanDate.setText("审核中,请耐心等待");
                        SharePreferencesUtil.addString(requireActivity(), Constants.USER_MEIQIA_STATUE, "已提交金额");
                        break;
                    case 4:
                        binding.tvLoanStatue.setText("黑名单");
                        binding.tvLoanDate.setText("审核中,请耐心等待");
                        SharePreferencesUtil.addString(requireActivity(), Constants.USER_MEIQIA_STATUE, "黑名单");
                        break;
                    case 5:
                        binding.tvLoanStatue.setText("复审通过待终审");
                        binding.tvLoanDate.setText("审核中,请耐心等待");
                        SharePreferencesUtil.addString(requireActivity(), Constants.USER_MEIQIA_STATUE, "复审通过待终审");
                        break;
                    case 6:
                        binding.tvLoanStatue.setText("终审中");
                        binding.tvLoanDate.setText("审核中,请耐心等待");
                        SharePreferencesUtil.addString(requireActivity(), Constants.USER_MEIQIA_STATUE, "终审中");
                        break;
                    case 7:
                        binding.tvLoanStatue.setText("借款被拒");
                        binding.tvLoanDate.setText("期待以后合作");
                        SharePreferencesUtil.addString(requireActivity(), Constants.USER_MEIQIA_STATUE, "借款被拒");
                        break;
                    case 8:
                        binding.tvLoanStatue.setText("终审通过放款中");
                        binding.tvLoanDate.setText("放款中,请耐心等待");
                        SharePreferencesUtil.addString(requireActivity(), Constants.USER_MEIQIA_STATUE, "终审通过放款中");
                        break;
                    case 9:
                        binding.tvLoanStatue.setText("放款成功待还款");
                        binding.tvLoanDate.setText("请及时还款");
                        SharePreferencesUtil.addString(requireActivity(), Constants.USER_MEIQIA_STATUE, "放款成功待还款");
                        break;
                    case 10:
                        binding.tvLoanStatue.setText("已结清");
                        binding.tvLoanDate.setText("审核中,请耐心等待");
                        SharePreferencesUtil.addString(requireActivity(), Constants.USER_MEIQIA_STATUE, "已结清");
                        break;
                    case 11:
                        binding.tvLoanStatue.setText("放款失败");
                        binding.tvLoanDate.setText("请联系客服解决");
                        SharePreferencesUtil.addString(requireActivity(), Constants.USER_MEIQIA_STATUE, "放款失败");
                        break;
                    case 12:
                        binding.tvLoanStatue.setText("已逾期");
                        binding.tvLoanDate.setText("请及时还款");
                        SharePreferencesUtil.addString(requireActivity(), Constants.USER_MEIQIA_STATUE, "已逾期");
                        break;
                }
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
//        if (SharePreferencesUtil.getBoolean(requireActivity(), Constants.IF_AUTHENTICATION, false)) {
//            //查看订单状态
//            viewModel.getLatestOrderStatus();
//        }
        //查看订单状态
        viewModel.getLatestOrderStatus();

        binding.radarView.startSearch();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.radarView.stopSearch();
    }
}