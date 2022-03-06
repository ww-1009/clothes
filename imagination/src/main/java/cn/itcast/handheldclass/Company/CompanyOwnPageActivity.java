package cn.itcast.handheldclass.Company;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import cn.itcast.handheldclass.BaseActivity;
import cn.itcast.handheldclass.Detail_text.FzgsSettlementAgreementActivity;
import cn.itcast.handheldclass.R;

public class CompanyOwnPageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_own_page);
        initNavBar(false,"我的主页",true);

        //点击导航栏第一个按钮跳转到形象打造页面
        ImageButton image_building = (ImageButton) findViewById(R.id.image_building);
        image_building.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent是一种运行时绑定（run-time binding）机制，它能在程序运行过程中连接两个不同的组件。
                //在存放资源代码的文件夹下下，
                Intent i = new Intent(CompanyOwnPageActivity.this , CompanyMainActivity.class);
                //启动
                startActivity(i);
            }
        });
        //点击导航栏第二个按钮跳转到商品展示页面
        ImageButton clothes_Changes = (ImageButton) findViewById(R.id.product_display);
        clothes_Changes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent是一种运行时绑定（run-time binding）机制，它能在程序运行过程中连接两个不同的组件。
                //在存放资源代码的文件夹下下，
                Intent i = new Intent(CompanyOwnPageActivity.this , CompanyProductDisplayActivity.class);
                //启动
                startActivity(i);
            }
        });

        //跳转到个人信息页面
        Button company_management_own_information_btn = (Button) findViewById(R.id.company_management_own_information_btn);
        company_management_own_information_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent是一种运行时绑定（run-time binding）机制，它能在程序运行过程中连接两个不同的组件。
                //在存放资源代码的文件夹下下，
                Intent i = new Intent(CompanyOwnPageActivity.this , CompanyManagementOwnInformationActivity.class);
                //启动
                startActivity(i);
            }
        });


        //跳转到账号管理页面
        Button account_control_btn = (Button) findViewById(R.id.account_control_btn3);
        account_control_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent是一种运行时绑定（run-time binding）机制，它能在程序运行过程中连接两个不同的组件。
                //在存放资源代码的文件夹下下，
                Intent i = new Intent(CompanyOwnPageActivity.this , CompanyAccountManagementActivity.class);
                //启动
                startActivity(i);
            }
        });

        //点击导航栏第一个按钮跳转到形象打造页面
        ImageButton fzgs = (ImageButton) findViewById(R.id.fzgs_4);
        fzgs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent是一种运行时绑定（run-time binding）机制，它能在程序运行过程中连接两个不同的组件。
                //在存放资源代码的文件夹下下，
                Intent i = new Intent(CompanyOwnPageActivity.this , FzgsSettlementAgreementActivity.class);
                //启动
                startActivity(i);
            }
        });
    }
}