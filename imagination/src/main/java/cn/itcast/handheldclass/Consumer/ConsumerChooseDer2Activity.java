package cn.itcast.handheldclass.Consumer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import cn.itcast.handheldclass.BaseActivity;
import cn.itcast.handheldclass.R;

public class ConsumerChooseDer2Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_choose_der2);
        initNavBar(false,"定制你的style",false);


        //形象设计师和服装设计师转换
        Spinner type = (Spinner) findViewById(R.id.change_type);
        final String arr_ID[] = new String[] { "形象设计师", "服装设计师"};
        ArrayAdapter<String> arrayAdapter_ID = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arr_ID);
        type.setAdapter(arrayAdapter_ID);
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Spinner spinner = (Spinner) parent;
                switch (position){
                    case 0:
                        break;

                    case 1:
                        Intent i = new Intent(ConsumerChooseDer2Activity.this , ConsumerChooseDerActivity.class);
                        //启动
                        startActivity(i);
                        break;


                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });




        //点击导航栏第一个按钮跳转到形象设计师选择界面
        ImageButton image_building = (ImageButton) findViewById(R.id.image_building);
        image_building.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent是一种运行时绑定（run-time binding）机制，它能在程序运行过程中连接两个不同的组件。
                //在存放资源代码的文件夹下下，
                Intent i = new Intent(ConsumerChooseDer2Activity.this , ConsumerMainActivity.class);
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
                Intent i = new Intent(ConsumerChooseDer2Activity.this , ConsumerOwnPageActivity.class);
                //启动
                startActivity(i);
            }
        });

        //点击第二张图片跳转到形象设计师信息界面
        LinearLayout image_d = (LinearLayout) findViewById(R.id.ImageDesignerSearch_xx_image2);
        image_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsumerChooseDer2Activity.this , ConsumerDesignerXxOwn2Activity.class);
                startActivity(intent);
            }
        });
    }
}