package com.cmbb.smartkids.activities.module;

import android.os.Bundle;

import com.cmbb.smartkids.ListViewFragment;
import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseNoCompatActivity;
import com.cmbb.smartkids.base.ui.header2.FadingActionBarHelper;

/**
 * Created by N.Sun
 */
public class ChatModuleActivity extends BaseNoCompatActivity {
    private static final String TAG = ChatModuleActivity.class.getSimpleName();

    @Override
    public int getLayoutId() {
        return R.layout.activity_module_chat;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new ListViewFragment())
                    .commit();
        }
    }

    @Override
    protected void init() {

    }
}
