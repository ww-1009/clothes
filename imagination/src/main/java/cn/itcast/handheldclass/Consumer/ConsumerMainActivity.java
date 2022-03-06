package cn.itcast.handheldclass.Consumer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import cn.itcast.handheldclass.BaseActivity;
import cn.itcast.handheldclass.R;
import cn.itcast.handheldclass.tools.Albums;
import cn.itcast.handheldclass.tools.Camera;

public class ConsumerMainActivity extends BaseActivity {
    private Intent intent1,intent2;
    private ImageView picture;
    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_main);
        Button chooseFromAlbum = findViewById(R.id.choose_image);
        chooseFromAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(ConsumerMainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ConsumerMainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CHOOSE_PHOTO);
                } else {
                    openAlbum();//打开album的界面
                }
            }
        });
        Button takePhoto = findViewById(R.id.take_photo);

        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 动态申请权限
                if (ContextCompat.checkSelfPermission(ConsumerMainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ConsumerMainActivity.this, new String[]{Manifest.permission.CAMERA}, TAKE_PHOTO);
                } else {
                    // 启动相机程序
                    startCamera();
                }
            }
        });
        intent1=new Intent(this, Albums.class);//创建跳转到Albums显示的窗口的Intent
        intent2=new Intent(this, Camera.class);//创建跳转到Camera显示的窗口的Intent











        initNavBar(false,"请选择图片",false);

        //点击导航栏第二个按钮跳转到形象设计师选择界面
        ImageButton designer_search = (ImageButton) findViewById(R.id.designer_search);
        designer_search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent是一种运行时绑定（run-time binding）机制，它能在程序运行过程中连接两个不同的组件。
                //在存放资源代码的文件夹下下，
                Intent i = new Intent(ConsumerMainActivity.this , ConsumerChooseDerActivity.class);
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
                Intent i = new Intent(ConsumerMainActivity.this , ConsumerOwnPageActivity.class);
                //启动
                startActivity(i);
            }
        });


    }
    private void openAlbum() {
        startActivity(intent1);//进入album的窗口界面
    }
    private void startCamera() {
        startActivity(intent2);//进入camera的窗口界面
    }
}