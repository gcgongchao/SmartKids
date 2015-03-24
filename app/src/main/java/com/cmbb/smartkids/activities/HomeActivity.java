package com.cmbb.smartkids.activities;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.account.ApiKeyProvider;
import com.cmbb.smartkids.activities.user.UserCenterActivity;
import com.cmbb.smartkids.activities.user.XXModelActivity;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.fragment.FragmentActive;
import com.cmbb.smartkids.fragment.FragmentDoc;
import com.cmbb.smartkids.fragment.FragmentHome;
import com.cmbb.smartkids.fragment.FragmentHome2;
import com.cmbb.smartkids.fragment.FragmentHome_Only;
import com.cmbb.smartkids.fragment.FragmentTools;
import com.cmbb.smartkids.tools.logger.Log;
import com.umeng.update.UmengUpdateAgent;

import java.io.IOException;

/**
 * Created by N.Sun
 */
public class HomeActivity extends BaseActivity {

    private static final String TAG = HomeActivity.class.getSimpleName();

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    String title_cache;

    TextView btn_home, btn_active, btn_doc, btn_tools, test;
    FragmentHome fragmentHome = new FragmentHome();
    FragmentActive fragmentActive = new FragmentActive();
    FragmentDoc fragmentDoc = new FragmentDoc();
    FragmentTools fragmentTools = new FragmentTools();
    // test
    FragmentHome2 fragmentHome2 = new FragmentHome2();
    // test two
    FragmentHome_Only fragmentHome_only = new FragmentHome_Only();

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container,
                    fragmentHome).commit();
        }
    }

    @Override
    protected void init() {
        initDrawer();
        initTabBottom();
    }

    private void initDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_left);
        getToolbar().setTitle(getString(R.string.app_name));//设置Toolbar标题
        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, getToolbar(), R.string.open,
                R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                title_cache = (String) getToolbar().getTitle();
                getToolbar().setTitle(getString(R.string.app_name));
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getToolbar().setTitle(title_cache);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        test = (TextView) findViewById(R.id.test);
        test.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initTabBottom() {
        btn_home = (TextView) findViewById(R.id.btn_home);
        btn_home.setOnClickListener(this);
        btn_active = (TextView) findViewById(R.id.btn_active);
        btn_active.setOnClickListener(this);
        btn_doc = (TextView) findViewById(R.id.btn_doc);
        btn_doc.setOnClickListener(this);
        btn_tools = (TextView) findViewById(R.id.btn_tools);
        btn_tools.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_home:
                //设置焦点
                btn_home.setSelected(true);
                btn_active.setSelected(false);
                btn_doc.setSelected(false);
                btn_tools.setSelected(false);
                getSupportFragmentManager().beginTransaction().replace(R.id.container,
                        fragmentHome).commit();
                break;
            case R.id.btn_active:
                //设置焦点
                btn_home.setSelected(false);
                btn_active.setSelected(true);
                btn_doc.setSelected(false);
                btn_tools.setSelected(false);
                getSupportFragmentManager().beginTransaction().replace(R.id.container,
                        fragmentActive).commit();
                break;
            case R.id.btn_doc:
                //设置焦点
                btn_home.setSelected(false);
                btn_active.setSelected(false);
                btn_doc.setSelected(true);
                btn_tools.setSelected(false);
                getSupportFragmentManager().beginTransaction().replace(R.id.container,
                        fragmentHome_only).commit();
                //UmengUpdateAgent.update(this);
                break;
            case R.id.btn_tools:
                //设置焦点
                btn_home.setSelected(false);
                btn_active.setSelected(false);
                btn_doc.setSelected(false);
                btn_tools.setSelected(true);
                getSupportFragmentManager().beginTransaction().replace(R.id.container,
                        fragmentTools).commit();

//                Intent xx = new Intent(this, XXModelActivity.class);
//                startActivity(xx);
                break;
            // Drawer
            case R.id.test:
                // 检测登录
                ApiKeyProvider.getAuthKey(this, new AccountManagerCallback<Bundle>() {
                    @Override
                    public void run(AccountManagerFuture<Bundle> future) {
                        try {
                            if (!TextUtils.isEmpty(future.getResult().getString(AccountManager
                                    .KEY_AUTHTOKEN))) {
                                Intent intent = new Intent(HomeActivity.this,
                                        UserCenterActivity.class);
                                startActivity(intent);
                            }
                        } catch (OperationCanceledException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (AuthenticatorException e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;

        }
        super.onClick(v);
    }

    @Override
    public void onBackPressed() {
        // addToStack
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}
