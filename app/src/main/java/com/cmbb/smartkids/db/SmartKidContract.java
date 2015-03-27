package com.cmbb.smartkids.db;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by N.Sun
 */
public final class SmartKidContract {

    private SmartKidContract() {

    }

    private static final String TEXT_TYPE = " TEXT,";
    private static final String INT_TYPE = " INTEGER,";
    public static final String DATABASE_NAME = " SmartKids.db";

    public final static class UserAccount implements BaseColumns {

        private UserAccount() {

        }

        // scheme
        public static final String SCHEME = "content";

        // Authority
        public static final String AUTHORITY = "com.cmbb.smartkids.useraccount";

        // content uri
        public static final Uri CONTENT_URI = Uri.parse(SCHEME + "://" + AUTHORITY);

        // multiple rows
        public static final String MIME_TYPE_ROWS = "vnd.android.cursor.dir/vnd.com.cmbb.smartkids.useraccount";

        // single row
        public static final String MIME_TYPE_SINGLE_ROWS = "vnd.android.cursor.item/vnd.com.cmbb.smartkids.useraccount";

        // primary key column name
        public static final String ROW_ID = BaseColumns._ID;

        // table name
        public static final String TABLE_NAME = "UserAccount";

        // table content URI
        public static final Uri TABLE_CONTENT_URI = Uri.withAppendedPath(CONTENT_URI, TABLE_NAME);

        //Columns
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_TOKEN = "token";
        public static final String COLUMN_AVATARURL = "avatarUrl";
        public static final String COLUMN_ID = "id";

        // create table

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + ROW_ID +
                " INTEGER PRIMARY KEY," + COLUMN_USERNAME + TEXT_TYPE + COLUMN_PHONE + TEXT_TYPE
                + COLUMN_TOKEN + TEXT_TYPE
                + COLUMN_AVATARURL + TEXT_TYPE + COLUMN_ID + " INTEGER" + ");";

        // delete table
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    }

    // 同城
    public final static class HomeSameCity implements BaseColumns {

        private HomeSameCity() {

        }

        // scheme
        public static final String SCHEME = "content";

        // Authority
        public static final String AUTHORITY = "com.cmbb.smartkids.homesamecity";

        // content uri
        public static final Uri CONTENT_URI = Uri.parse(SCHEME + "://" + AUTHORITY);

        // multiple rows
        public static final String MIME_TYPE_ROWS = "vnd.android.cursor.dir/vnd.com.cmbb" +
                ".smartkids.homesamecity";

        // single row
        public static final String MIME_TYPE_SINGLE_ROWS = "vnd.android.cursor.item/vnd.com.cmbb" +
                ".smartkids.homesamecity";

        // primary key column name
        public static final String ROW_ID = BaseColumns._ID;

        // table name
        public static final String TABLE_NAME = "HomeSameCity";

        // table content URI
        public static final Uri TABLE_CONTENT_URI = Uri.withAppendedPath(CONTENT_URI, TABLE_NAME);

        //Columns
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_TOKEN = "token";
        public static final String COLUMN_AVATARURL = "avatarUrl";
        public static final String COLUMN_ID = "id";

        // create table

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + ROW_ID +
                " INTEGER PRIMARY KEY," + COLUMN_USERNAME + TEXT_TYPE + COLUMN_PHONE + TEXT_TYPE
                + COLUMN_TOKEN + TEXT_TYPE
                + COLUMN_AVATARURL + TEXT_TYPE + COLUMN_ID + " INTEGER" + ");";

        // delete table
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    }

    //同龄
    public final static class HomeSameAge implements BaseColumns {

        private HomeSameAge() {

        }

        // scheme
        public static final String SCHEME = "content";

        // Authority
        public static final String AUTHORITY = "com.cmbb.smartkids.homesameage";

        // content uri
        public static final Uri CONTENT_URI = Uri.parse(SCHEME + "://" + AUTHORITY);

        // multiple rows
        public static final String MIME_TYPE_ROWS = "vnd.android.cursor.dir/vnd.com.cmbb" +
                ".smartkids.homesameage";

        // single row
        public static final String MIME_TYPE_SINGLE_ROWS = "vnd.android.cursor.item/vnd.com.cmbb" +
                ".smartkids.homesameage";

        // primary key column name
        public static final String ROW_ID = BaseColumns._ID;

        // table name
        public static final String TABLE_NAME = "HomeSameAge";

        // table content URI
        public static final Uri TABLE_CONTENT_URI = Uri.withAppendedPath(CONTENT_URI, TABLE_NAME);

        //Columns
        public static final String COLUMN_BIGIMG = "bigImg";
        public static final String COLUMN_BIGIMGHEIGHT = "bigImgHeight";
        public static final String COLUMN_BIGIMGWIDTH = "bigImgWidth";
        public static final String COLUMN_CONNECTOR = "connector";
        public static final String COLUMN_CONTEXT = "context";
        public static final String COLUMN_COUNT = "count";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_SMALLIMG = "smallImg";
        public static final String COLUMN_SMALLIMGHEIGHT = "smallImgHeight";
        public static final String COLUMN_SMALLIMGWIDTH = "smallImgWidth";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_TYPE = "type";

        // create table

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + ROW_ID +
                " INTEGER PRIMARY KEY," + COLUMN_BIGIMG + TEXT_TYPE + COLUMN_BIGIMGHEIGHT + INT_TYPE
                + COLUMN_BIGIMGWIDTH + INT_TYPE
                + COLUMN_CONNECTOR + TEXT_TYPE +
                COLUMN_CONTEXT + TEXT_TYPE + COLUMN_COUNT + INT_TYPE + COLUMN_ID
                + INT_TYPE + COLUMN_SMALLIMG + TEXT_TYPE + COLUMN_SMALLIMGHEIGHT + INT_TYPE
                + COLUMN_SMALLIMGWIDTH + INT_TYPE + COLUMN_TITLE + TEXT_TYPE + COLUMN_TYPE
                + " TEXT"
                + ");";

        // delete table
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    }
}
