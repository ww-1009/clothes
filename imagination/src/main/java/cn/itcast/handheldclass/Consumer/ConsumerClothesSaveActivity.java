package cn.itcast.handheldclass.Consumer;


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
import cn.itcast.handheldclass.Company.CompanyMainActivity;
import cn.itcast.handheldclass.Company.CompanyOwnPageActivity;
import cn.itcast.handheldclass.Company.CompanyProductDisplayActivity;
import cn.itcast.handheldclass.R;

public class ConsumerClothesSaveActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_clothes_save);
        initNavBar(true,"已保存的服装",false);

        Button B1 = (Button) findViewById(R.id.buy_1);
        Button B2 = (Button) findViewById(R.id.buy_2);
        Button B3 = (Button) findViewById(R.id.buy_3);
        Button B4 = (Button) findViewById(R.id.buy_4);
        Button B5 = (Button) findViewById(R.id.buy_5);



        B1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("https://item.taobao.com/item.htm?id=640270020667");//此处填链接
                intent.setData(content_url);
                startActivity(intent);


            }
        });
        B2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("https://item.taobao.com/item.htm?id=618589382303");//此处填链接
                intent.setData(content_url);
                startActivity(intent);


            }
        });
        B3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("https://item.taobao.com/item.htm?id=610322866455");//此处填链接
                intent.setData(content_url);
                startActivity(intent);
            }
        });

        B4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("https://item.taobao.com/item.htm?id=620867210036");//此处填链接
                intent.setData(content_url);
                startActivity(intent);
            }
        });

        B5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("https://item.taobao.com/item.htm?id=619037775150");//此处填链接
                intent.setData(content_url);
                startActivity(intent);
            }
        });







    }


}