package cn.itcast.handheldclass.tools;
import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import cn.itcast.handheldclass.BaseActivity;
import cn.itcast.handheldclass.Company.CompanyPersonalDetailActivity;
import cn.itcast.handheldclass.Consumer.ConsumerMainActivity;
import cn.itcast.handheldclass.Consumer.ConsumerPersonalDetailActivity;
import cn.itcast.handheldclass.Designer.DesignerPersonalDetailActivity;
import cn.itcast.handheldclass.MyData;
import cn.itcast.handheldclass.R;

public class Albums extends BaseActivity {
    private  ImageView albumsPicture;
    private Button submit2;
    String imagePath = null;
    public static final int CHOOSE_PHOTO = 2;
    private String pathiden;
    private String resultden;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
//        resultden =
        }
    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.albums);
        MyData mydata = (MyData)getApplication();
        final int myIndex = mydata.getIndex();
        albumsPicture = findViewById(R.id.picture);
        initNavBar(true,"本地图库选择",false);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CHOOSE_PHOTO);
        } else {
            openAlbum();
        }

        //跳转到个人信息填写页面
        submit2=findViewById(R.id.submit2);
        submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myIndex==0)
                {
                    Intent intent=new Intent(v.getContext(), ConsumerPersonalDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("chosen_Photo", imagePath);
                    System.out.println("当前选择图片地址为"+imagePath);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
                else if(myIndex==1)
                {   System.out.println("到这里");
                    Intent intent=new Intent(v.getContext(), DesignerPersonalDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("chosen_Photo", imagePath);
                    System.out.println("当前选择图片地址为"+imagePath);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Intent intent=new Intent(v.getContext(), CompanyPersonalDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("chosen_Photo", imagePath);
                    System.out.println("当前选择图片地址为"+imagePath);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            }
        });



    }
    private void openAlbum(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);//打开相册
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CHOOSE_PHOTO:
                //相册照片
                if (requestCode == CHOOSE_PHOTO && resultCode == RESULT_OK && null != data) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        handleImageOnKitkat(data);
                    } else {
                        handleImageBeforeKitKat(data);
                    }
                }
            default:
                break;
        }
    }
    @TargetApi(19)
    private void handleImageOnKitkat(Intent data) {
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            //如果是document类型的uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
                System.out.println(imagePath);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content:" +
                        "//downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
                System.out.println(imagePath);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            //如果是content类型的uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
            System.out.println(imagePath);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            //如果是File类型的uri，直接获取图片路径即可
            imagePath = uri.getPath();
            System.out.println(imagePath);
        }
        //根据图片路径显示图片
        displayImage(imagePath);
        pathiden=imagePath;
        System.out.println(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data){
        Uri uri=data.getData();
        String imagePath=getImagePath(uri,null);
        displayImage(imagePath);
    }
    private String getImagePath(Uri uri,String selection){
        String path=null;
        Cursor cursor=getContentResolver().query(uri,null,selection,null,null);
        if(cursor!=null){
            if(cursor.moveToFirst()){
                path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
    private void displayImage(String imagePath){
        if(imagePath!=null){
            Bitmap bitmap=BitmapFactory.decodeFile(imagePath);
            albumsPicture.setImageBitmap(bitmap);//将图片放置在控件上
        }else {
            Toast.makeText(this,"得到图片失败",Toast.LENGTH_SHORT).show();
        }
    }


}
