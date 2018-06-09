package com.dict.android.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class WordsSQLiteOpenHelper extends SQLiteOpenHelper {

    /**
     * 建表语句
     */
    private final String CREATE_WORDS = "create table Words(" +
            "id Integer primary key autoincrement," +
            "isChinese text," +
            "text text," +
            "fy text," +
            "psE text," +
            "pronE text," +
            "psA text," +
            "pronA text," +
            "posAcceptation text," +
            "affix text,"+
            "root text,"+
            "sent text)";
    private Context mContext;

    public WordsSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_WORDS);
        Toast.makeText(mContext,"Create succeeded",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
