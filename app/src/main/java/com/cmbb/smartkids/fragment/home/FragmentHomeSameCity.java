package com.cmbb.smartkids.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.BaseFragment;
import com.cmbb.smartkids.db.SmartKidContract;

/**
 * Created by N.Sun
 */
public class FragmentHomeSameCity extends BaseFragment implements LoaderManager.LoaderCallbacks{
    private static final String TAG = FragmentHomeSameCity.class.getSimpleName();

    private ListView lv_same_city;
    private SimpleCursorAdapter simpleCursorAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home_same_city, container, false);
        lv_same_city = (ListView) rootView.findViewById(R.id.lv_same_city);
        simpleCursorAdapter = new SimpleCursorAdapter(getActivity(),
                R.layout.frgment_home_same_item, null, new String[]{SmartKidContract
                .UserAccount.COLUMN_USERNAME},
                new int[]{R.id.tv_sub_title},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        return rootView;
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
}
