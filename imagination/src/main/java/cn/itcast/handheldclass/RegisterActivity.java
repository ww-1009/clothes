package cn.itcast.handheldclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import cn.itcast.handheldclass.Detail_text.PrivacyPolicyActivity;
import cn.itcast.handheldclass.Detail_text.UserAgreementActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText addname,addpwd1,addpwd2,addnumber;

    RadioGroup rdg;
    int index;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        initNavBar(true,"添加账户",false);
        //控件初始化
        addname = findViewById(R.id.Add_Username);
        addpwd1 = findViewById(R.id.Add_Password1);
        addpwd2 = findViewById(R.id.Add_Password2);
        addnumber = findViewById(R.id.Add_Number);
        rdg = findViewById(R.id.radioGroup);
        //依靠DatabaseHelper带全部参数的构造函数创建数据库
        DatabaseHelper dbHelper = new DatabaseHelper(this, "User_db",null,1);
        db = dbHelper.getWritableDatabase();

        //跳转到隐私政策
        TextView Privacy_policy = (TextView) findViewById(R.id.Privacy_policy);
        Privacy_policy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent是一种运行时绑定（run-time binding）机制，它能在程序运行过程中连接两个不同的组件。
                //在存放资源代码的文件夹下下，
                Intent i = new Intent(RegisterActivity.this , PrivacyPolicyActivity.class);
                //启动
                startActivity(i);
            }
        });

        //跳转到用户协议
        TextView User_agreement = (TextView) findViewById(R.id.User_agreement);
        User_agreement.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent是一种运行时绑定（run-time binding）机制，它能在程序运行过程中连接两个不同的组件。
                //在存放资源代码的文件夹下下，
                Intent i = new Intent(RegisterActivity.this , UserAgreementActivity.class);
                //启动
                startActivity(i);
            }
        });


    }

    public void AddUserOnclick(View view) {
        //获取用户输入账号
        String name = addname.getText().toString();
        String number = addnumber.getText().toString();
        String pwd1 = addpwd1.getText().toString();
        String pwd2 = addpwd2.getText().toString();
        //获取选择的角色
        for (int i = 0; i < rdg.getChildCount(); i++) {
            RadioButton rd = (RadioButton) rdg.getChildAt(i);
            if (rd.isChecked()) {
                index = i;
                break;
            }
        }
        // 判空
        if(name.equals("") || number.equals("")|| pwd1.equals("")|| pwd2.equals("")){
            Toast.makeText(this,"注册信息不能为空",Toast.LENGTH_SHORT).show();
        }else {
            if(!(number.length()==11)){
                Toast.makeText(this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
            }else if (pwd1.equals(pwd2)) {
                //录入数据库
                if(index == 0){
                    //个人消费者
                    String sql = "select * from consumer where username = '"+name+"'";
                    Cursor cu = db.rawQuery(sql,null);
                    //检查用户名是否存在，保证用户唯一性
                    if(cu.getCount() > 0){
                        Toast.makeText(this,"个人消费者账号已被占用",Toast.LENGTH_SHORT).show();

                    }else {
                        add_consumer(name,pwd1,number);
                        Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(RegisterActivity.this , LoginActivity.class);
                        startActivity(i);
                        finish();

                    }

                }else if(index == 1){
                    //形象设计师
                    String sql2 = "select * from designer where username = '"+name+"'";
                    Cursor cur = db.rawQuery(sql2,null);
                    //检查用户名是否存在，保证用户唯一性
                    if(cur.getCount() > 0){
                        Toast.makeText(this,"形象设计师账号已被占用",Toast.LENGTH_SHORT).show();
                    }else {
                        add_designer(name,pwd1,number);
                        Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(RegisterActivity.this , LoginActivity.class);
                        startActivity(i);
                        finish();
                    }
                }else if(index == 2){
                    //服装公司
                    String sql_company = "select * from company where username = '"+name+"'";
                    Cursor cua = db.rawQuery(sql_company,null);
                    //检查用户名是否存在，保证用户唯一性
                    if(cua.getCount() > 0){
                        Toast.makeText(this,"服装公司账号已被占用",Toast.LENGTH_SHORT).show();
                    }else {
                        add_company(name,pwd1,number);
                        Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(RegisterActivity.this , LoginActivity.class);
                        startActivity(i);
                        finish();
                    }
                }


            }else{
                Toast.makeText(this, "两次输入密码不一致！", Toast.LENGTH_SHORT).show();
            }

        }

    }

    //根据用户信息添加个人消费者
    public void add_consumer(String name,String pwd,String number){
        String sql_consumer = "insert into consumer values(null,'"+name+"','"+pwd+"','"+number+"')";
        db.execSQL(sql_consumer);

    }
    //根据用户信息添加形象设计师

    public void add_designer(String name,String pwd,String number){
        String sql_designer = "insert into designer values(null,'"+name+"','"+pwd+"','"+number+"')";
        db.execSQL(sql_designer);
    }

    //根据用户信息添加服装公司
    public void add_company(String name,String pwd,String number){
        String sql_company = "insert into company values(null,'"+name+"','"+pwd+"','"+number+"')";
        db.execSQL(sql_company);
    }


}