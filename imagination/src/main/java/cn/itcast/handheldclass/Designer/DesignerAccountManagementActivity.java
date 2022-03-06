package cn.itcast.handheldclass.Designer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import cn.itcast.handheldclass.BaseActivity;
import cn.itcast.handheldclass.DatabaseHelper;
import cn.itcast.handheldclass.LoginActivity;
import cn.itcast.handheldclass.MyData;
import cn.itcast.handheldclass.R;

public class DesignerAccountManagementActivity extends BaseActivity {
    SQLiteDatabase db;
    //用户信息
    MyData mMyData;
    String sql2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designer_account_management);
        initNavBar(true,"账号管理",false);
        //取出用户信息
        mMyData = (MyData)getApplication();

        //获取数据库对象
        DatabaseHelper dbHelper = new DatabaseHelper(this, "User_db",null,1);
        db = dbHelper.getWritableDatabase();

    }

    //根据用户名删除该用户所有相关信息
    public void delDesigner_User(String name){
        sql2 = "delete from designer where username = '"+name + "'";
        db.execSQL(sql2);

    }



    //注销账号弹出框
    public void delete_account_check2(View view) {

        AlertDialog.Builder builder2 = new AlertDialog.Builder(this,R.style.Theme_AppCompat_Light_Dialog_Alert);

        builder2.setMessage("您真的要注销该账号吗");
        builder2.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String name = mMyData.getName();

                //数据库删除
                delDesigner_User(name);
                Intent i = new Intent(DesignerAccountManagementActivity.this , LoginActivity.class);
                startActivity(i);
                Toast.makeText(DesignerAccountManagementActivity.this,"已永久删除该账号",Toast.LENGTH_SHORT).show();
                System.out.println("删除账号"+":"+name);
                System.out.println("注销成功");
            }
        });
        builder2.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("取消注销账号");
            }
        });
        builder2.show();

    }



    //退出账号退出框
    public void log_out_check(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.Theme_AppCompat_Light_Dialog_Alert);

        builder.setMessage("您真的要退出吗");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(DesignerAccountManagementActivity.this , LoginActivity.class);
                startActivity(i);
                Toast.makeText(DesignerAccountManagementActivity.this,"退出成功",Toast.LENGTH_SHORT).show();
                System.out.println("退出成功");
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("取消退出");
            }
        });
        builder.show();

    }
}