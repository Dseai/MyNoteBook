package com.syn.mynotebook.activity;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.syn.mynotebook.DB.MyDataBase;
import com.syn.mynotebook.R;
import com.syn.mynotebook.enties.Note;

import java.util.Date;

/**
 * Created by 孙亚楠 on 2016/11/19.
 */
public class SecondActivity extends Activity{
    Button save;
    TextView textView1;      //标题
    TextView textView2;      //内容
    EditText title;
    EditText content;
    MyDataBase myDataBase;
    Note note2;                        //第二个activity里面的note
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        save=(Button)findViewById(R.id.save);
        textView1=(TextView)findViewById(R.id.t1);
        textView2=(TextView)findViewById(R.id.t2);
        title=(EditText)findViewById(R.id.title2);
        content=(EditText)findViewById(R.id.content2);
        myDataBase=new MyDataBase(this);
        id=getIntent().getIntExtra("id",0);
        if(id!=0){
            note2=myDataBase.getTitleandContent(id);
            title.setText(note2.getTitle());
            content.setText(note2.getContent());

        }
        save.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        isSave();
                                    }
                                }
        );

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBackPressed() {
       /* SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy.MM.dd  HH:mm:ss");
         */Date currentDate=new Date(System.currentTimeMillis());//获取当前时间

        String time=currentDate.toLocaleString();
                //simpleDateFormat.format(currentDate);
        String titles=title.getText().toString();
        String contents=content.getText().toString();
        //修改数据
        if (id != 0) {
            note2 = new Note( id,titles, contents, time);
            myDataBase.toUpdate(note2);
            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(intent);
            SecondActivity.this.finish();
        }
        //新建文本
        else {
            note2 = new Note(titles, contents, time);
            myDataBase.toInsert(note2);
            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(intent);
            SecondActivity.this.finish();

        }
    }
    private void isSave(){
        java.text.SimpleDateFormat formatter   =   new java.text.SimpleDateFormat("yyyy.MM.dd  HH:mm:ss");
        Date   curDate   =   new   Date(System.currentTimeMillis());//获取当前系统时间
        String times   =   formatter.format(curDate);
        String titles=title.getText().toString();
        String contents=content.getText().toString();
        //修改数据
        if (id!=0) {
            note2 = new Note(id,titles, contents, times);
            myDataBase.toUpdate(note2);
            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(intent);
            SecondActivity.this.finish();
        }
        //新建一条便笺
        else {
            if(titles!=null||contents!=null){
                note2 = new Note(titles, contents, times);
                myDataBase.toInsert(note2);
            }

            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(intent);
            SecondActivity.this.finish();

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "标题" + title.getText().toString() + "内容" + content.getText().toString());
                startActivity(intent);
                break;

            default:
                break;
        }
        return false;
    }

}
