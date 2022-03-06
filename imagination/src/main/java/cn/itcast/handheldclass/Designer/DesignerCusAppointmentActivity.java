package cn.itcast.handheldclass.Designer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import cn.itcast.handheldclass.BaseActivity;
import cn.itcast.handheldclass.Consumer.ConsumerChooseDerActivity;
import cn.itcast.handheldclass.Consumer.ConsumerMainActivity;
import cn.itcast.handheldclass.Consumer.ConsumerOwnPageActivity;
import cn.itcast.handheldclass.R;

public class DesignerCusAppointmentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designer_cus_appointment);
        initNavBar(false,"客户页面",false);

        //点击导航栏第一个按钮跳转到形象打造页面
        ImageButton image_building = (ImageButton) findViewById(R.id.image_building);
        image_building.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent是一种运行时绑定（run-time binding）机制，它能在程序运行过程中连接两个不同的组件。
                //在存放资源代码的文件夹下下，
                Intent i = new Intent(DesignerCusAppointmentActivity.this , DesignerMainActivity.class);
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
                Intent i = new Intent(DesignerCusAppointmentActivity.this , DesignerOwnPageActivity.class);
                //启动
                startActivity(i);
            }
        });
    }
}