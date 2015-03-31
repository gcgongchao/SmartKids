package com.cmbb.smartkids.fragment.module;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.BaseNoCompatActivity;
import com.cmbb.smartkids.base.ui.header2.HeaderFragment;
import com.cmbb.smartkids.db.SmartKidContract;

/**
 * Created by N.Sun
 */
public class Fragment_ChatModule extends HeaderFragment implements LoaderManager
        .LoaderCallbacks<Cursor>, AdapterView.OnItemClickListener {

    private static final String TAG = Fragment_ChatModule.class.getSimpleName();

    // content
    private ListView mListView;
    private SimpleCursorAdapter simpleCursorAdapter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        setHeaderBackgroundScrollMode(HEADER_BACKGROUND_SCROLL_PARALLAX);
        setOnHeaderScrollChangedListener(new HeaderFragment.OnHeaderScrollChangedListener() {
            @Override
            public void onHeaderScrollChanged(float progress, int height, int scroll) {
                height -= getActivity().getActionBar().getHeight();

                progress = (float) scroll / height;
                if (progress > 1f) progress = 1f;
                progress = (1 - (float) Math.cos(progress * Math.PI)) * 0.5f;

                ((BaseNoCompatActivity) getActivity())
                        .getFadingActionBarHelper()
                        .setActionBarAlpha((int) (255 * progress));
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        initAdapter();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    // listView Item
    private void initAdapter() {
        simpleCursorAdapter = new SimpleCursorAdapter(getActivity(),
                R.layout.frgment_home_same_item, null, new String[]{SmartKidContract
                .HomeSameAge.COLUMN_TITLE},
                new int[]{R.id.tv_constellation},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_module_sameage, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateHeaderView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_chat_module, container, false);
    }

    @Override
    public View onCreateContentView(LayoutInflater inflater, ViewGroup container) {
        mListView = (ListView) inflater.inflate(R.layout.fragment_listview, container, false);
        mListView.setAdapter(simpleCursorAdapter);
        mListView.setOnItemClickListener(this);
        getLoaderManager().initLoader(0, null, this);
        return mListView;
    }

    @Override
    public View onCreateContentOverlayView(LayoutInflater inflater, ViewGroup container) {
        return null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        simpleCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        simpleCursorAdapter.swapCursor(null);
    }
}
