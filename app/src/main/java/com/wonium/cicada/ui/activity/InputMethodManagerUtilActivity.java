/*
 * Copyright (C) 2019 Ethan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wonium.cicada.ui.activity;



import android.view.View;



import com.alibaba.android.arouter.facade.annotation.Route;
import com.wonium.cicada.R;
import com.wonium.cicada.databinding.ActivityInputMethodManagerUtilBinding;
import com.wonium.cicada.router.PageRouter;
import com.wonium.cicada.base.BaseActivity;
import com.wonium.hydrogen.utils.InputMethodManagerUtil;

@Route(path = PageRouter.ACTIVITY_INPUT_METHOD_MANAGER_UTIL)
public class InputMethodManagerUtilActivity extends BaseActivity {
    private ActivityInputMethodManagerUtilBinding mBinding;

    @Override
    public void initWindowAttributes() {
        setAllowFullScreen(false);
        setScreenRotate(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_input_method_manager_util;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = ActivityInputMethodManagerUtilBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }

    @Override
    public void initView() {
        mBinding.includeImmuToolbar.tvToolbarTitle.setText(getString(R.string.tools_input_method_manager));
        mBinding.includeImmuToolbar.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);

    }

    @Override
    public void initListener() {
        mBinding.includeImmuToolbar.toolbar.setNavigationOnClickListener(v -> finish());
    }

    public void onOpenSoftKeyBoard(View view) {
        InputMethodManagerUtil.getInstance(getContext()).toggleSoftInput();
    }

    public void onCloseSoftKeyBoard(View view) {
        InputMethodManagerUtil.getInstance(getContext()).hideSoftInput(this);
    }
}
