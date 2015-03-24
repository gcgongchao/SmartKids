package com.cmbb.smartkids.activities.home;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.BaseFragment;
import com.cmbb.smartkids.db.SmartKidContract;
import com.cmbb.smartkids.tools.logger.Log;

import org.w3c.dom.Text;

/**
 * Created by N.Sun
 */
public class HomeFragmentTest extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final String TAG = HomeFragmentTest.class.getSimpleName();

    public static final String ARG_ITEM_ID = "item_id_test";


    TextView item_title;

    public HomeFragmentTest() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            //username = getArguments().getString(SmartKidContract.UserAccount.COLUMN_USERNAME);
        //}
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.pager_item_sample, container, false);
        item_title = (TextView) rootView.findViewById(R.id.item_title);

        getLoaderManager().initLoader(0, null, this);
        return rootView;

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.i(TAG, "onCreateLoader");
        return new CursorLoader(getActivity(), SmartKidContract.UserAccount.TABLE_CONTENT_URI, null, null, null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        item_title.setText("Cursor " + data.getCount());

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
