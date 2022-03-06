package cn.itcast.handheldclass.Company;

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

public class CompanyAccountManagementActivity extends BaseActivity {


    SQLiteDatabase db;
    //用户信息
    MyData mMyData;
    String sql3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_account_management);
        initNavBar(true,"账号管理",false);
        //取出用户信息
        mMyData = (MyData)getApplication();

        //获取数据库对象
        DatabaseHelper dbHelper = new DatabaseHelper(this, "User_db",null,1);
        db = dbHelper.getWritableDatabase();

    }

    //根据用户名删除该用户所有相关信息
    public void del_company_User(String name){
        sql3 = "delete from company where username = '"+name + "'";
        db.execSQL(sql3);

    }



    //注销账号弹出框
    public void delete_account_check3(View view) {

        AlertDialog.Builder builder2 = new AlertDialog.Builder(this,R.style.Theme_AppCompat_Light_Dialog_Alert);

        builder2.setMessage("您真的要注销该账号吗");
        builder2.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String name = mMyData.getName();

                //数据库删除
                del_company_User(name);
                Intent i = new Intent(CompanyAccountManagementActivity.this , LoginActivity.class);
                startActivity(i);
                Toast.makeText(CompanyAccountManagementActivity.this,"已永久删除该账号",Toast.LENGTH_SHORT).show();
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
                Intent i = new Intent(CompanyAccountManagementActivity.this , LoginActivity.class);
                startActivity(i);
                Toast.makeText(CompanyAccountManagementActivity.this,"退出成功",Toast.LENGTH_SHORT).show();
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