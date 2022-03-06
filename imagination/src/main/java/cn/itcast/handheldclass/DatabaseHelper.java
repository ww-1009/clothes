package cn.itcast.handheldclass;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //创建表的sql语句
    //个人消费者信息表
    String sql1 = "create table consumer(id INTEGER PRIMARY KEY,username varchar(32),pwd varchar(32),number varchar(40))";
    //形象设计师信息表
    String sql2 = "create table designer(id INTEGER PRIMARY KEY,username varchar(32),pwd varchar(32),number varchar(40))";
    //服装公司信息表
    String sql3 = "create table company(id INTEGER PRIMARY KEY,username varchar(32),pwd varchar(32),number varchar(40))";


    //带全部参数的构造函数，此构造函数必不可少
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("建立数据库");
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}