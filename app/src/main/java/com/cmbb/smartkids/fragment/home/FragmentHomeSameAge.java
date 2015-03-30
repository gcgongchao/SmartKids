package com.cmbb.smartkids.fragment.home;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cmbb.smartkids.Application;
import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activities.module.ChatModuleActivity;
import com.cmbb.smartkids.base.BaseActivity;
import com.cmbb.smartkids.base.BaseFragment;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.broadcast.ToastBroadcast;
import com.cmbb.smartkids.db.SmartKidContract;
import com.cmbb.smartkids.network.OkHttp;
import com.cmbb.smartkids.network.ResponseActivityCallback;
import com.cmbb.smartkids.network.model.ResponseListModel;
import com.cmbb.smartkids.network.model.ResponseModel;
import com.cmbb.smartkids.tools.logger.Log;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by N.Sun
 */
public class FragmentHomeSameAge extends BaseFragment implements AdapterView.OnItemClickListener,
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = FragmentHomeSameAge.class.getSimpleName();
    private ListView lv_same_city;
    private SimpleCursorAdapter simpleCursorAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        initAdapter();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_home_cityandage, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                attemptRefresh();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void attemptRefresh() {
        showWaitDialog("数据加载中...");
        Map<String, String> body = new HashMap<>();
        body.put("areaType", "AGEBREAKET");
        body.put("token", getToken());
        OkHttp.asyncPost(Constants.Home.AREATYPEPLATE_URL, body,
                new ResponseActivityCallback<ResponseListModel>(((BaseActivity) getActivity()),
                        ResponseListModel.class) {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        super.onFailure(request, e);
                    }

                    @Override
                    public void onSuccess(ResponseListModel data) {
                        super.onSuccess(data);
                        if ("1".equals(data.getCode().trim())) {
                            ArrayList<ContentProviderOperation> batch = new ArrayList<ContentProviderOperation>();
                            if (data.getContext().size() > 0) {
                                batch.add(ContentProviderOperation.newDelete(SmartKidContract
                                        .HomeSameAge.TABLE_CONTENT_URI).withSelection(" 1 =" +
                                        " 1", null).build());
                            }

                            for (int i = 0; i < data.getContext().size(); i++) {
                                batch.add(ContentProviderOperation.newInsert(SmartKidContract
                                        .HomeSameAge.TABLE_CONTENT_URI)
                                        .withValue(SmartKidContract.HomeSameAge.COLUMN_TITLE,
                                                data.getContext().get(i).getTitle())
                                        .withValue(SmartKidContract.HomeSameAge.COLUMN_BIGIMG, data.getContext().get(i).getBigImg())
                                        .withValue(SmartKidContract.HomeSameAge.COLUMN_BIGIMGHEIGHT, data.getContext().get(i).getBigImgHeight())
                                        .withValue(SmartKidContract.HomeSameAge.COLUMN_BIGIMGWIDTH,
                                                data.getContext().get(i).getBigImgWidth())
                                        .withValue(SmartKidContract.HomeSameAge.COLUMN_CONNECTOR,
                                                data.getContext().get(i).getConnector())
                                        .withValue(SmartKidContract.HomeSameAge.COLUMN_CONTEXT,
                                                data.getContext().get(i).getContext())
                                        .withValue(SmartKidContract.HomeSameAge.COLUMN_SMALLIMG,
                                                data.getContext().get(i).getSmallImg())
                                        .withValue(SmartKidContract.HomeSameAge.COLUMN_SMALLIMGHEIGHT,
                                                data.getContext().get(i).getSmallImgHeight())
                                        .withValue(SmartKidContract.HomeSameAge.COLUMN_SMALLIMGWIDTH,
                                                data.getContext().get(i).getSmallImgWidth())
                                        .withValue(SmartKidContract.HomeSameAge.COLUMN_COUNT,
                                                data.getContext().get(i).getCount())
                                        .withValue(SmartKidContract.HomeSameAge.COLUMN_TYPE,
                                                data.getContext().get(i).getType())
                                        .withValue(SmartKidContract.HomeSameAge.COLUMN_ID,
                                                data.getContext().get(i).getId())
                                        .build());
                            }

                            try {
                                ContentProviderResult[] results = getActivity().getContentResolver()
                                        .applyBatch(SmartKidContract
                                                .HomeSameAge.AUTHORITY, batch);
                                Log.i(TAG, "ContentProviderResult[] = " + results.length);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            } catch (OperationApplicationException e) {
                                e.printStackTrace();
                            }

                        } else {
                            Intent intent = new Intent(Constants.INTENT_ACTION_TOAST);
                            intent.putExtra(ToastBroadcast.ToastFLAG, ToastBroadcast.SHOW_TOAST_PARAM);
                            intent.putExtra(ToastBroadcast.SHOW_TOAST_Message, "加载失败");
                            getActivity().sendBroadcast(intent);
                        }
                    }
                });
    }

    private void initAdapter() {
        simpleCursorAdapter = new SimpleCursorAdapter(getActivity(),
                R.layout.frgment_home_same_item, null, new String[]{SmartKidContract
                .HomeSameAge.COLUMN_TITLE},
                new int[]{R.id.tv_constellation},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home_same_age, container, false);
        lv_same_city = (ListView) rootView.findViewById(R.id.lv_same_age);
        lv_same_city.setAdapter(simpleCursorAdapter);
        lv_same_city.setOnItemClickListener(this);
        getLoaderManager().initLoader(0, null, this);
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(getActivity(), ChatModuleActivity.class);
        startActivity(intent);

    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), SmartKidContract.HomeSameAge.TABLE_CONTENT_URI,
                null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        simpleCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader loader) {
        simpleCursorAdapter.swapCursor(null);
    }
}
