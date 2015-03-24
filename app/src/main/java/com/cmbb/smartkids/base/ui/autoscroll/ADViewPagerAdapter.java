package com.cmbb.smartkids.base.ui.autoscroll;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.adapter.OnClubClickListener;
import com.cmbb.smartkids.vo.TestModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class ADViewPagerAdapter extends RecyclingPagerAdapter {
    private OnClubClickListener ADListener;

    public void setOnADListener(OnClubClickListener listener) {
        if (listener != null) {
            ADListener = listener;
        }
    }

    private List<TestModel> data = new ArrayList<TestModel>();
    private DisplayImageOptions options;

    public ADViewPagerAdapter() {
        options = new DisplayImageOptions.Builder().showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher).resetViewBeforeLoading(true).cacheInMemory(true).cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY).bitmapConfig(Bitmap.Config.RGB_565).considerExifParams(true).displayer(new FadeInBitmapDisplayer(300)).build();
    }

    public void setData(List data) {
        if (null != data && data.size() > 0) {
            this.data.clear();
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public View getView(final int position, View view, final ViewGroup container) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.autoscroll_item_img, container, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        ImageLoader.getInstance().displayImage(data.get(position).getUrl(), holder.iv, options);

        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ADListener != null) {
                    ADListener.onClick(v, position, data.get(position));
                }
            }
        });
        return view;
    }

    static final class ViewHolder {
        public ImageView iv;

        public ViewHolder(View view) {
            iv = (ImageView) view.findViewById(R.id.img);
        }
    }
}
