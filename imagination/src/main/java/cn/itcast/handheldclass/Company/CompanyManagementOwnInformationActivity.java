package cn.itcast.handheldclass.Company;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cn.itcast.handheldclass.BaseActivity;
import cn.itcast.handheldclass.ChangePwdActivity;
import cn.itcast.handheldclass.DatabaseHelper;
import cn.itcast.handheldclass.MyData;
import cn.itcast.handheldclass.R;

public class CompanyManagementOwnInformationActivity extends BaseActivity {


    SQLiteDatabase db;
    //用户信息
    MyData mMyData;

    //控件
    ImageView mImageView;
    TextView mTvName,mTvPwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_management_own_information);


        initNavBar(true,"个人信息",false);

        //取出用户信息
        mMyData = (MyData)getApplication();

        //获取数据库对象
        DatabaseHelper dbHelper = new DatabaseHelper(this, "User_db",null,1);
        db = dbHelper.getWritableDatabase();
        //初始化控件
        initView();
        //显示
        showInfo();

        //跳转到修改密码页面
        Button bt_change = (Button) findViewById(R.id.bt_change);
        bt_change.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent是一种运行时绑定（run-time binding）机制，它能在程序运行过程中连接两个不同的组件。
                //在存放资源代码的文件夹下下，
                Intent i = new Intent(CompanyManagementOwnInformationActivity.this , ChangePwdActivity.class);
                //启动
                startActivity(i);
            }
        });
    }

    public void initView(){
        mImageView = findViewById(R.id.user_image);
        mTvName = findViewById(R.id.tv_username);
        mTvPwd = findViewById(R.id.tv_pwd);

    }
    public void showInfo(){
        mTvName.setText(mMyData.getName());
        mTvPwd.setText(mMyData.getPwd());


    }
}