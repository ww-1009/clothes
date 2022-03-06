package cn.itcast.handheldclass.Company;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import cn.itcast.handheldclass.BaseActivity;
import cn.itcast.handheldclass.R;

public class CompanyProductDisplayActivity extends BaseActivity {
    private LinearLayout C1;
    private LinearLayout C2;
    private LinearLayout C3;
    private LinearLayout C4;
    private LinearLayout C5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_product_display);
        initNavBar(false,"商品展示",false);

        C1 = findViewById(R.id.C1);
        C2 = findViewById(R.id.C2);
        C3 = findViewById(R.id.C3);
        C4 = findViewById(R.id.C4);
        C5 = findViewById(R.id.C5);

        C1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("https://item.taobao.com/item.htm?id=640270020667");//此处填链接
                intent.setData(content_url);
                startActivity(intent);


            }
        });
        C2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("https://item.taobao.com/item.htm?id=618589382303");//此处填链接
                intent.setData(content_url);
                startActivity(intent);


            }
        });
        C3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("https://item.taobao.com/item.htm?id=610322866455");//此处填链接
                intent.setData(content_url);
                startActivity(intent);
            }
        });

        C4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("https://item.taobao.com/item.htm?id=620867210036");//此处填链接
                intent.setData(content_url);
                startActivity(intent);
            }
        });

        C5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("https://item.taobao.com/item.htm?id=619037775150");//此处填链接
                intent.setData(content_url);
                startActivity(intent);
            }
        });




        //点击导航栏第一个按钮跳转到形象打造页面
        ImageButton image_building = (ImageButton) findViewById(R.id.image_building);
        image_building.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent是一种运行时绑定（run-time binding）机制，它能在程序运行过程中连接两个不同的组件。
                //在存放资源代码的文件夹下下，
                Intent i = new Intent(CompanyProductDisplayActivity.this , CompanyMainActivity.class);
                //启动
                startActivity(i);
            }
        });

        //点击导航栏第三个按钮跳转到个人信息管理界面
        ImageButton personal = (ImageButton) findViewById(R.id.personal);
        personal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent是一种运行时绑定（run-time binding）机制，它能在程序运行过程中连接两个不同的组件。
                //在存放资源代码的文件夹下下，
                Intent i = new Intent(CompanyProductDisplayActivity.this , CompanyOwnPageActivity.class);
                //启动
                startActivity(i);
            }
        });


    }


}