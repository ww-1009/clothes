package cn.itcast.handheldclass.Consumer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import cn.itcast.handheldclass.BaseActivity;
import cn.itcast.handheldclass.R;

public class ConsumerSceneSelActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_scene_sel);
        initNavBar(true,"场景选择",false);

        Bundle bundle = this.getIntent().getExtras();
        assert bundle != null;
        final String imagePath = bundle.getString("chosen_Photo");

        //季节下拉框
        Spinner spinner = (Spinner) findViewById(R.id.scene_selection_season);
        final String arr_ID[] = new String[] { "春季", "夏季", "秋季", "冬季"};
        ArrayAdapter<String> arrayAdapter_ID = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arr_ID);
        spinner.setAdapter(arrayAdapter_ID);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Spinner spinner = (Spinner) parent;
                // 使用Toast弹出提示信息
//                Toast.makeText(getApplicationContext(),
//                        "  " + spinner.getItemAtPosition(position),
//                        Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "没有改变的处理",
                        Toast.LENGTH_LONG).show();
            }
        });

        //点击提交跳转到形象打造页面
        Button scene_selection_check = (Button) findViewById(R.id.scene_selection_check);
        scene_selection_check.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent是一种运行时绑定（run-time binding）机制，它能在程序运行过程中连接两个不同的组件。
                //在存放资源代码的文件夹下下，
                Intent i = new Intent(ConsumerSceneSelActivity.this , ConsumerClothesChangeActivity.class);
                //启动
                Bundle bundle = new Bundle();
                bundle.putString("chosen_Photo", imagePath);
                System.out.println("ConsumerSceneSelActivity已经发送"+imagePath);
                i.putExtras(bundle);
                startActivity(i);
            }

        });


    }
}