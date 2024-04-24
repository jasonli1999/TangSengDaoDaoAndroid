package com.idss.cashloans.ui.fragment.mine;

import android.content.Intent;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.azhon.basic.base.BaseFragment;
import com.idss.cashloans.api.Constants;
import com.idss.cashloans.api.moudle.CaiwuIdModel;
import com.idss.cashloans.api.moudle.PaopaoModel;
import com.idss.cashloans.api.utils.LogUtil;
import com.idss.cashloans.api.utils.SharePreferencesUtil;
import com.idss.cashloans.api.utils.SoundUtils;
import com.idss.cashloans.databinding.FragmentNotificationsBinding;
import com.idss.cashloans.ui.activity.Authentication.AuthenticationActivity;
import com.idss.cashloans.ui.activity.aboutbills.HistoryOrderActivity;
import com.idss.cashloans.ui.activity.bankcard.ChangeBankCardActivity;
import com.idss.cashloans.ui.activity.main.MainActivity;
import com.idss.cashloans.ui.adapter.APPlistAdapter;
import com.idss.cashloans.ui.dialog.CaiwuIdDialog;
import com.idss.cashloans.ui.dialog.ExitDialog;

public class MineFragment extends BaseFragment<MineFragmentViewModel, FragmentNotificationsBinding> {
    private String caiwuid;

    @Override
    protected void initView(View view) {
        String username = SharePreferencesUtil.getString(context, Constants.PHONE, "");
        binding.tvName.setText(username);

        binding.ivExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExitDialog exitDialog = new ExitDialog(requireActivity());
                exitDialog.show(requireActivity().getSupportFragmentManager(), "exitDialog");
            }
        });

        //获取聊天软件信息
        viewModel.getPaopao();
        //获取财务id
        viewModel.getCaiwuid();
        binding.llNowbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**播放音頻 */
                SoundUtils soundUtils = new SoundUtils();
                soundUtils.playAudio_point(requireActivity());

                MainActivity mainActivity = (MainActivity) getActivity();
                assert mainActivity != null;
                mainActivity.noticePoit(1);

            }
        });

        binding.llHistorybill.setOnClickListener(v -> startActivity(new Intent(getActivity(), HistoryOrderActivity.class)));


        binding.rlAddcaiwuid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != caiwuid && !caiwuid.isEmpty()) {
                    /**播放音頻 */
                    SoundUtils soundUtils = new SoundUtils();
                    soundUtils.playAudio_point(requireActivity());

                    CaiwuIdDialog caiwuIdDialog = new CaiwuIdDialog(requireActivity(), caiwuid);
                    caiwuIdDialog.show(requireActivity().getSupportFragmentManager(), "caiwuIdDialog");
                }
            }
        });

        LogUtil.e(SharePreferencesUtil.getInteger(requireActivity(), Constants.CERTIFICATION, 999) +"===========");
        //去认证
        if (SharePreferencesUtil.getInteger(requireActivity(), Constants.CERTIFICATION, 999) == 0) {
            binding.rlAuthentication.setVisibility(View.GONE);
            binding.rlChangeBankcard.setVisibility(View.VISIBLE);
        } else {
            binding.rlAuthentication.setVisibility(View.VISIBLE);
            binding.rlChangeBankcard.setVisibility(View.GONE);
        }

        if (SharePreferencesUtil.getInteger(requireActivity(), Constants.IS_OLDUSER, 999) == 0) {
            binding.rlAddcaiwuid.setVisibility(View.GONE);
        } else {
            binding.rlAddcaiwuid.setVisibility(View.VISIBLE);
        }

        binding.rlChangeBankcard.setOnClickListener(v -> startActivity(new Intent(getActivity(), ChangeBankCardActivity.class)));
        binding.rlAuthentication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AuthenticationActivity.class));
            }
        });

    }

    @Override
    protected void initData() {
        viewModel.getPaoPao().observe(this, new Observer<PaopaoModel>() {
            @Override
            public void onChanged(PaopaoModel paopaoModel) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                //竖直方向
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                //设置item显示方法
                binding.recyclerViewAplist.setLayoutManager(layoutManager);
                APPlistAdapter adapter = new APPlistAdapter(getActivity(), paopaoModel);
                binding.recyclerViewAplist.setAdapter(adapter);


            }
        });

        viewModel.getCaiwu().observe(this, new Observer<CaiwuIdModel>() {
                    @Override
                    public void onChanged(CaiwuIdModel caiwuIdModel) {
                        caiwuid = caiwuIdModel.getData();

                    }
                }
        );

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}