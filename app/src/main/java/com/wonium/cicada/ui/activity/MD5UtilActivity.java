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

import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wonium.cicada.R;
import com.wonium.cicada.databinding.ActivityMd5UtilBinding;
import com.wonium.cicada.router.PageRouter;
import com.wonium.hydrogen.ui.BaseActivity;
import com.wonium.hydrogen.utils.Md5Util;

@Route(path = PageRouter.ACTIVITY_MD5_UTIL)
public class MD5UtilActivity extends BaseActivity {
    private ActivityMd5UtilBinding mBinding;
    @Override
    public void initWindowAttributes() {
        setAllowFullScreen(false);
        setScreenRotate(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_md5_util;
    }

    @Override
    public void bindLayout(int layoutResId) {
        mBinding = DataBindingUtil.setContentView(this,layoutResId);
    }

    @Override
    public void initView() {
        setSupportActionBar(mBinding.includeMd5Toolbar.toolbar);
        mBinding.setTitle(getString(R.string.tools_md5));
        mBinding.includeMd5Toolbar.toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        mBinding.includeMd5Toolbar.toolbar.setNavigationOnClickListener(v -> finish());
        StringBuilder builder =new StringBuilder();
        builder.append("原字符串 ： wonium\n")
        .append("加密后字符串： ").append(Md5Util.getInstance().getMD5String("wonium"));
        mBinding.setTestCase(builder.toString());
    }

    @Override
    public void initListener() {

    }
}
