package com.azhon.basic.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.azhon.basic.lifecycle.BaseViewModel;
import com.azhon.basic.utils.GenericsUtil;

import java.lang.reflect.Type;


/**
 * createDate: 2023/11/8 on 17:44
 * desc: ViewModel、ViewDataBinding都需要的基类
 *
 * @author azhon
 */
public abstract class BaseFragment<VM extends BaseViewModel, VB extends ViewBinding> extends BaseNoModelFragment<VB> {

    protected VM viewModel;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = createViewModel();
        initObserve();
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * 初始化ViewModel
     */
    protected VM createViewModel() {
        Type type = GenericsUtil.get(getClass(), "");
        return new ViewModelProvider(this).get((Class<VM>) type);
    }

    /**
     * 监听当前ViewModel中 showDialog和error的值
     */
    private void initObserve() {
        if (viewModel == null) return;
        viewModel.getShowDialog(this, bean -> {
            if (bean.isShow()) {
                showDialog(bean.getMsg());
            } else {
                dismissDialog();
            }
        });
        viewModel.getError(this, this::showError);
    }

}
