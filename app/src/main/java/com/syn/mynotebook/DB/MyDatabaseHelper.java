package com.syn.mynotebook.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 孙亚楠 on 2016/11/19.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    public static  final String CREATE_NOTE="create table if not exists note (id integer primary key autoincrement,title text,content text,date text)";
    public MyDatabaseHelper(Context context) {
        super(context, "note", null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_NOTE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int j) {

    }
}
