package com.cmbb.smartkids.adapter;

import android.view.View;
import android.widget.CursorAdapter;

/**
 * Created by N.Sun
 */
public abstract class OnClubClickListener {
    public abstract void onClick(View v, int position, Object item);
}
