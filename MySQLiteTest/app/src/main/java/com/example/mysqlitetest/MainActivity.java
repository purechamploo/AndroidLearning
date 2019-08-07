package com.example.mysqlitetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button button;
    Button button1;
    Button button2;
    Button button3;
    Button button4;

    SQLiteDatabase db;


    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_main);
        dbHelper = new MyDatabaseHelper(this,"Student.db",null,1);
        db = dbHelper.getWritableDatabase();
        bind();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.create_database:
                create_database();
                break;
            case R.id.insert_data:
                insert();
                break;
            case R.id.delete_data:
                delete();
                break;
            case R.id.update_data:
                update();
                break;
            case R.id.select_data:
                select();
                break;
            default:
                break;
        }
    }

    private void create_database() {
        dbHelper.getWritableDatabase();
    }

    private void insert() {
        db.delete("Student", "old < ?", new String[]{"25"});
        System.out.println("this is insert");
        ContentValues values = new ContentValues();
        values.put("name","Li Hua");
        values.put("gender","男");
        values.put("old","18");
        db.insert("Student",null,values);
        values.clear();
        values.put("name","Han Mei Mei");
        values.put("gender","女");
        values.put("old","16");
        db.insert("Student",null,values);
        values.clear();
        values.put("name","Zhao Lei");
        values.put("gender","男");
        values.put("old","20");
        db.insert("Student",null,values);
        select();
    }

    private void delete() {
        System.out.println("this is delete");
        //第二个参数是WHERE语句（即执行条件，删除哪条数据）
        //第三个参数是WHERE语句中占位符（即"?"号）的填充值
        db.delete("Student", "name=?", new String[]{"Han Mei Mei"});
        select();
    }

    private void update() {
        System.out.println("this is update");
        ContentValues values = new ContentValues();
        values.put("gender","女");
        db.update("Student",values,"name = ?",new String[]{"Li Hua"});
        select();
    }

    private void select() {
        System.out.println("this is select");
        Cursor mCursor = db.query("Student",null,null,
                null,null,null,null);
        mCursor.moveToFirst();
        do{
            String name = mCursor.getString(mCursor.getColumnIndex("name"));
            System.out.println(name);
        }while(mCursor.moveToNext());
    }

    private void deleteAll(){
    }


    private void bind(){
        button = (Button) findViewById(R.id.create_database);
        button1 = (Button)findViewById(R.id.insert_data);
        button2 = (Button)findViewById(R.id.delete_data);
        button3 = (Button)findViewById(R.id.update_data);
        button4 = (Button)findViewById(R.id.select_data);

        button.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
