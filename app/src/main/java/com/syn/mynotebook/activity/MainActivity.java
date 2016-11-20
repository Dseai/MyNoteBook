package com.syn.mynotebook.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.syn.mynotebook.DB.MyDataBase;
import com.syn.mynotebook.R;
import com.syn.mynotebook.adapter.MyAdapter;
import com.syn.mynotebook.enties.Note;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private ListView listView;
    private MyDataBase myDataBase;
    private LayoutInflater layoutInflater;
    private ArrayList<Note> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView1);
        layoutInflater = getLayoutInflater();
        myDataBase = new MyDataBase(this);
        button = (Button) findViewById(R.id.button1);
        arrayList = myDataBase.getArray();
        MyAdapter myAdapter = new MyAdapter(layoutInflater, arrayList);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("id", arrayList.get(i).getId());
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        /**
         * 参数
         　　parent 发生点击事件的 AbsListView。
         　　view    AbsListView 中被点击的视图。
         　　position    视图在一览中的位置（索引）。
         　　id 被点击条目的行 ID。
         　　返回值
         　　如果回调函数处理了长按事件，返回真；否则返回假。
         */
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                new AlertDialog.Builder(MainActivity.this).setTitle("删除").setMessage("数据无价，谨慎操作!")
                        .setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        myDataBase.deleteNote(arrayList.get(position).getId());
                        arrayList = myDataBase.getArray();
                        MyAdapter myAdapter = new MyAdapter(layoutInflater, arrayList);
                        listView.setAdapter(myAdapter);

                    }
                }).create().show();
                return true;
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                MainActivity.this.finish();

            }
        });
      /*  Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.item1:
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
                break;
            case R.id.item2:
                this.finish();
                break;
            default: break;
        }
        return true;
    }*/
    }
}

