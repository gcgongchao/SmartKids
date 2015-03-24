package com.cmbb.smartkids.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cmbb.smartkids.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by N.Sun
 */
public abstract class ListBaseAdapter extends BaseAdapter {

    private static final String TAG = ListBaseAdapter.class.getSimpleName();

    public static final int STATE_EMPTY_ITEM = 0;// 状态：没有数据
    public static final int STATE_LOAD_MORE = 1;// 状态:加在数据
    public static final int STATE_NO_MORE = 2;// 状态：没有可加载数据
    public static final int STATE_NO_DATA = 3;// 状态：没有数据
    public static final int STATE_LESS_ONE_PAGE = 4;// 状态：少于1页数据

    protected int state = STATE_LESS_ONE_PAGE;

    protected int _loadmoreText;
    protected int _loadFinishText;

    // 数据
    protected List _data = new ArrayList();

    public ListBaseAdapter() {
        _loadmoreText = R.string.loading;
        _loadFinishText = R.string.loading_no_more;
    }

    //设置状态
    public void setState(int state) {
        this.state = state;
        notifyDataSetChanged();
    }

    //获取状态
    public int getState() {
        return this.state;
    }

    @Override
    public int getCount() {
        switch (state) {
            case STATE_EMPTY_ITEM:
                return getDataSize() + 1;
            case STATE_LOAD_MORE:
                return getDataSize() + 1;
            case STATE_NO_DATA:
                return 0;
            case STATE_NO_MORE:
                return getDataSize() + 1;
            case STATE_LESS_ONE_PAGE:
                return getDataSize();
            default:
                break;
        }
        return getDataSize();
    }

    public int getDataSize() {
        return _data.size();
    }

    @Override
    public Object getItem(int position) {
        if (_data.size() > position) {
            return _data.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setData(List data) {
        _data = data;
        notifyDataSetChanged();
    }

    public List getData() {
        return _data == null ? (_data = new ArrayList()) : _data;
    }

    public void addData(List data) {
        if (_data == null) {
            _data = new ArrayList();
        }
        _data.addAll(data);
        notifyDataSetChanged();
    }

    public void addItem(Object obj) {
        if (_data == null) {
            _data = new ArrayList();
        }
        _data.add(obj);
        notifyDataSetChanged();
    }

    public void removeItem(Object obj) {
        _data.remove(obj);
        notifyDataSetChanged();
    }

    public void clear() {
        _data.clear();
        notifyDataSetChanged();
    }

    public void setLoadmoreText(int loadmoreText) {
        _loadmoreText = loadmoreText;
    }

    public void setLoadFinishText(int loadFinishText) {
        _loadFinishText = loadFinishText;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (position == getCount() - 1) {// 最后一条
            if (state == STATE_LOAD_MORE || state == STATE_NO_MORE || state == STATE_EMPTY_ITEM) {
                LinearLayout loadmore = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cell_footer, null);
                ProgressBar progress = (ProgressBar) loadmore.findViewById(R.id.progressbar);
                TextView text = (TextView) loadmore.findViewById(R.id.text);
                switch (state) {
                    case STATE_LOAD_MORE:
                        loadmore.setVisibility(View.VISIBLE);
                        progress.setVisibility(View.VISIBLE);
                        text.setVisibility(View.VISIBLE);
                        text.setText(_loadmoreText);
                        break;
                    case STATE_NO_MORE:
                        loadmore.setVisibility(View.VISIBLE);
                        progress.setVisibility(View.GONE);
                        text.setVisibility(View.VISIBLE);
                        text.setText(_loadFinishText);
                        break;
                    case STATE_EMPTY_ITEM:
                        progress.setVisibility(View.GONE);
                        loadmore.setVisibility(View.GONE);
                        text.setVisibility(View.GONE);
                        break;
                    default:
                        progress.setVisibility(View.GONE);
                        loadmore.setVisibility(View.GONE);
                        text.setVisibility(View.GONE);
                        break;
                }
                return loadmore;
            }
        }
        return getRealView(position, convertView, parent);
    }

    protected abstract View getRealView(int position, View convertView, ViewGroup parent);
}
