/*
 * Copyright  2018  WoNium, Joy, Lokiwife.
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

package com.wonium.aclj.ui;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.wonium.cicada.utils.ActivityManagerUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


/**
 * @ClassName: BaseActivity.java
 * @Description: activity 公共基类
 * @Author: Wonium
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2018/11/17 10:08
 * @UpdateUser: update user
 * @UpdateDate: 2018/11/17 10:08
 * @UpdateDescription: 更新说明
 * @Version: 1.0.0
 */
public abstract class BaseActivity extends AppCompatActivity {
    /**
     * 是否允许全屏
     **/
    private boolean mAllowFullScreen = false;
    /**
     * 是否禁止旋转屏幕
     **/
    private boolean isAllowScreenRotate = true;

    /**
     * 是否沉浸状态栏
     **/
    private boolean isSetStatusBar = false;

    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ActivityManagerUtil.getInstance().addActivity(this);
        super.onCreate(savedInstanceState);
        mContext = this;
        initWindowAttributes();
        if (mAllowFullScreen) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }

        bindLayout(getLayoutResId());
        initView();
        if (isSetStatusBar) {
            setStatusBar();
        }
        if (!isAllowScreenRotate) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        if (savedInstanceState != null) {
            initParam(savedInstanceState);
        } else {
            if (getIntent() != null && getIntent().getExtras() != null) {
                initParam(getIntent().getExtras());
            }
        }
        initListener();
    }


    /**
     * 初始化数据
     *
     * @param bundle 界面跳转携带的参数
     */
    protected void initParam(Bundle bundle) {

    }


    public Context getContext() {
        return mContext;
    }

    /**
     * 设置状态栏
     */
    protected void setStatusBar() {
        try {
//            StatusBarUtil.INSTANCE.setColor(this, getStatusColor(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    protected int getStatusColor() {
        return getResources().getColor(android.R.color.background_dark);
    }

    /**
     * 初始化窗口属性
     */
    public  abstract void initWindowAttributes();
    /**
     * 获取资源文件Id
     *
     * @return
     */
    public abstract int getLayoutResId();

    /**
     * 绑定布局文件
     * @param layoutResId 布局文件资源ID
     */
    public abstract void bindLayout(int layoutResId);


    /**
     * 初始化View
     */
    public abstract void initView();

    /**
     * 初始化监听事件
     */
    public abstract void initListener();
    /**
     * 设置是否需要全屏
     *
     * @param allowFullScreen true 全屏，false 不是全屏 该方法的使用必须放在initWindowAttributes方法中，不然会报AndroidRuntimeException异常
     * @link https://blog.wonium.com/archives/112/
     */
    public void setAllowFullScreen(boolean allowFullScreen) {
        this.mAllowFullScreen = allowFullScreen;
    }

    /**
     * 是否设置沉浸状态栏
     *
     * @param isSetStatusBar true 支持沉浸，false 不支持沉浸
     */
    public void setStatusBar(boolean isSetStatusBar) {
        this.isSetStatusBar = isSetStatusBar;
    }


    public void setScreenRoate(boolean isAllowScreenRotate) {
        this.isAllowScreenRotate = isAllowScreenRotate;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
