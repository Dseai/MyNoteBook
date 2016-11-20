package com.syn.mynotebook.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.syn.mynotebook.enties.Note;

import java.util.ArrayList;

import static android.R.attr.id;

/**
 * Created by 孙亚楠 on 2016/11/19.
 */

public class MyDataBase {
    Context context;
    SQLiteDatabase sqLiteDatabase;
    MyDatabaseHelper myDatabaseHelper;
    public MyDataBase(Context context){
        this.context=context;
        myDatabaseHelper=new MyDatabaseHelper(context);
    }
    public ArrayList<Note> getArray(){
        ArrayList<Note> arrayList=new ArrayList<Note>();
        ArrayList<Note> arrayList1=new ArrayList<Note>();
        sqLiteDatabase=myDatabaseHelper.getWritableDatabase();
        //rawQuery()方法的第一个参数为select语句；
        // 第二个参数为select语句中占位符参数的值，
        // 如果select语句没有使用占位符，该参数可以设置为null。
     Cursor cursor=sqLiteDatabase.rawQuery("select id,title,date from note",null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id=cursor.getInt(cursor.getColumnIndex("id"));
            String title=cursor.getString(cursor.getColumnIndex("title"));
            String date=cursor.getString(cursor.getColumnIndex("date"));
            Note note=new Note(id,title,date);
            arrayList.add(note);
            cursor.moveToNext();

        }cursor.close();
        sqLiteDatabase.close();
        for(int i=arrayList.size();i>0;i--){
            arrayList1.add(arrayList.get(i-1));
        }
        return arrayList1;
        //return  arrayList;
    }
    //第二个activity用到的  标题+内容
    public Note getTitleandContent( int id){
        sqLiteDatabase=myDatabaseHelper.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select title,content from note where id='"+id+"'" ,null);
        cursor.moveToFirst();
        String title=cursor.getString(cursor.getColumnIndex("title"));
        String content =cursor.getString(cursor.getColumnIndex("content"));
        Note note=new Note(title,content);
        cursor.close();
        return note;
    }

    //execSQL(String sql, Object[] bindArgs)方法的第一个参数为SQL语句，
    // 第二个参数为SQL语句中占位符参数的值，参数值在数组中的顺序要和占位符的位置对应。
    // 修改数据
    public void toUpdate(Note note){
        sqLiteDatabase =myDatabaseHelper.getWritableDatabase();
        sqLiteDatabase.execSQL("update note set title='"+ note.getTitle()+"',date='"+note.getDate()+"',content='"+note.getContent() +"' where id='"+ note.getId()+"'");
        sqLiteDatabase.close();

    }
    //插入数据,新建一条便笺
    public void toInsert(Note note){
        sqLiteDatabase=myDatabaseHelper.getWritableDatabase();
        sqLiteDatabase.execSQL("insert into note(title,content,date) values('"+ note.getTitle()+"','"+note.getContent()+"','"+note.getDate()+"')");
        sqLiteDatabase.close();
    }
    //删除一条数据
    public void deleteNote(int id){
        sqLiteDatabase=myDatabaseHelper.getWritableDatabase();
        sqLiteDatabase.execSQL("delete  from note where id=" + id+ "" );
        sqLiteDatabase.close();
    }

}
