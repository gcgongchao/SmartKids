package com.cmbb.smartkids.activities.user;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.TestFragment;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.ui.card.CardAdapter;
import com.cmbb.smartkids.base.ui.card.CardView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by N.Sun
 */
public class GrowthDiaryActivity extends BaseActivity implements LoaderManager.LoaderCallbacks, CardView.OnCardClickListener {

    List<String> list;
    private TestFragment frag;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_growth_diary;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    protected void init() {
        initView();
    }

    private void initView() {
        CardView cardView = (CardView) findViewById(R.id.cardView1);
        cardView.setOnCardClickListener(this);
        cardView.setItemSpace(convertDpToPixelInt(this, 20));

        MyCardAdapter adapter = new MyCardAdapter(this);
        adapter.addAll(initData());
        cardView.setAdapter(adapter);

        FragmentManager manager = getSupportFragmentManager();
        frag = new TestFragment();
        manager.beginTransaction().add(R.id.contentView, frag).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_growth_diary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {

    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

    private List<String> initData() {
        list = new ArrayList<String>();
        list.add("2015-03-12");
        list.add("2015-03-13");
        list.add("2015-03-14");
        list.add("2015-03-15");
        list.add("2015-03-16");
        list.add("2015-03-17");
        list.add("2015-03-18");
        list.add("2015-03-19");
        list.add("2015-03-20");
        list.add("2015-03-21");
        list.add("2015-03-22");
        list.add("2015-03-23");
        list.add("2015-03-24");
        return list;
    }

    public int convertDpToPixelInt(Context context, float dp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int px = (int) (dp * (metrics.densityDpi / 160f));
        return px;
    }

    @Override
    public void onCardClick(View view, int position) {
        Toast.makeText(this, position + "", Toast.LENGTH_SHORT).show();
        Bundle bundle = new Bundle();
        bundle.putString("text", list.get(position % list.size()));
        frag.show(view, bundle);
    }

    public class MyCardAdapter extends CardAdapter<String> {

        public MyCardAdapter(Context context) {
            super(context);
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        protected View getCardView(int position,
                                   View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(GrowthDiaryActivity.this);
                convertView = inflater.inflate(R.layout.item_layout_test, parent, false);
            }
            TextView tv = (TextView) convertView.findViewById(R.id.textView1);
            String text = getItem(position % list.size());
            tv.setText(text);
            return convertView;
        }
    }
}
