package com.cmbb.smartkids.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.SlidingTabsBasicFragment;
import com.cmbb.smartkids.activities.user.UserCenterActivity;
import com.cmbb.smartkids.activities.user.XXModelActivity;
import com.cmbb.smartkids.base.BaseActivity;

import org.w3c.dom.Text;

/**
 * Created by N.Sun
 */
public class HomeActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ArrayAdapter arrayAdapter;
    String title_cache;

    TextView btn_home, btn_active, btn_doc, btn_tools, test;

    //private AnimationDrawable mAnimationDrawable;
    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            SlidingTabsBasicFragment fragment = new SlidingTabsBasicFragment();
            transaction.replace(R.id.container, fragment);
            transaction.commit();
        }

    }

    @Override
    protected void init() {
        initView();
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

    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_left);
        getToolbar().setTitle("萌宝派");//设置Toolbar标题
        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, getToolbar(), R.string.open,
                R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //mAnimationDrawable.stop();
                title_cache = (String) getToolbar().getTitle();
                getToolbar().setTitle("萌宝派");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //mAnimationDrawable.start();
                getToolbar().setTitle(title_cache);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        btn_home = (TextView) findViewById(R.id.btn_home);
        btn_home.setOnClickListener(this);
        btn_active = (TextView) findViewById(R.id.btn_active);
        btn_active.setOnClickListener(this);
        btn_doc = (TextView) findViewById(R.id.btn_doc);
        btn_doc.setOnClickListener(this);
        btn_tools = (TextView) findViewById(R.id.btn_tools);
        btn_tools.setOnClickListener(this);
        test = (TextView) findViewById(R.id.test);
        test.setOnClickListener(this);
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
                break;
            case R.id.btn_active:
                //设置焦点
                btn_home.setSelected(false);
                btn_active.setSelected(true);
                btn_doc.setSelected(false);
                btn_tools.setSelected(false);
                break;
            case R.id.btn_doc:
                //设置焦点
                btn_home.setSelected(false);
                btn_active.setSelected(false);
                btn_doc.setSelected(true);
                btn_tools.setSelected(false);
                break;
            case R.id.btn_tools:
                //设置焦点
                btn_home.setSelected(false);
                btn_active.setSelected(false);
                btn_doc.setSelected(false);
                btn_tools.setSelected(true);

                Intent xx = new Intent(this, XXModelActivity.class);
                startActivity(xx);
                break;
            // Drawer
            case R.id.test:
                Intent intent = new Intent(this, UserCenterActivity.class);
                startActivity(intent);
                break;

        }
        super.onClick(v);
    }
}
