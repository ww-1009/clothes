package cn.itcast.handheldclass.Designer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.List;

import cn.itcast.handheldclass.BaseActivity;
import cn.itcast.handheldclass.R;
import cn.itcast.handheldclass.spinners.InformationFilling_spinner_sex;

public class DesignerPersonalDetailActivity extends BaseActivity {
    private Spinner Spinner_sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designer_personal_detail);
        initNavBar(true,"请完善信息",false);
        Spinner_sex = (Spinner) this.findViewById(R.id.Spinner_sex);
        this.loadDataForSpinner_sex();

        Bundle bundle = this.getIntent().getExtras();
        assert bundle != null;
        final String imagePath = bundle.getString("chosen_Photo");

        Button determine = (Button) findViewById(R.id.determine);
        determine.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(DesignerPersonalDetailActivity.this , DesignerSceneSelActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("chosen_Photo", imagePath);
                System.out.println("DesignerPersonalDetailActivity已经发送"+imagePath);
                i.putExtras(bundle);
                startActivity(i);
            }

        });



    }
    //性别下拉列表
    private void loadDataForSpinner_sex() {
        List<String> spinnerList = InformationFilling_spinner_sex.GetSexList();
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerList);
        this.Spinner_sex.setAdapter(myAdapter);
    }
}