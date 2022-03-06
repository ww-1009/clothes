package cn.itcast.handheldclass.tools;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.app.Activity;
import android.net.Uri;
import android.widget.Button;
import android.widget.ImageView;

import cn.itcast.handheldclass.BaseActivity;
import cn.itcast.handheldclass.Company.CompanyPersonalDetailActivity;
import cn.itcast.handheldclass.Consumer.ConsumerMainActivity;
import cn.itcast.handheldclass.Consumer.ConsumerPersonalDetailActivity;
import cn.itcast.handheldclass.Designer.DesignerPersonalDetailActivity;
import cn.itcast.handheldclass.MyData;
import cn.itcast.handheldclass.R;

public class Camera extends BaseActivity {
    private ImageView cameraPicture;
    public static final int TAKE_PHOTO = 1;
    private Button pictureSave=null;
    private Button submit2;
    private Uri imageUri;
    private String uriden;
    private String token;
    private String pathiden;
    private String resultden;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        initNavBar(true,"拍照",false);
        MyData mydata = (MyData)getApplication();
        final int myIndex = mydata.getIndex();
        pictureSave=findViewById(R.id.pictureSave);
        cameraPicture = findViewById(R.id.Saved_picture);
        // 创建一个File对象，用于保存摄像头拍下的图片，这里把图片命名为output_image.jpg
        // 并将它存放在手机SD卡的应用关联缓存目录下
        File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
        // 对照片的更换设置
        try {
            // 如果上一次的照片存在，就删除
            if (outputImage.exists()) {
                outputImage.delete();
            }
            // 创建一个新的文件
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 如果Android版本大于等于7.0
        if (Build.VERSION.SDK_INT >= 24) {
            // 将File对象转换成一个封装过的Uri对象
            imageUri = FileProvider.getUriForFile(this, "cn.itcast.handheldclass.fileProvider", outputImage);
            Log.d("MainActivity", outputImage.toString() + "手机系统版本高于Android7.0");
        } else {
            // 将File对象转换为Uri对象，这个Uri标识着output_image.jpg这张图片的本地真实路径
            Log.d("MainActivity", outputImage.toString() + "手机系统版本低于Android7.0");
            imageUri = Uri.fromFile(outputImage);
        }
        // 动态申请权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions( this, new String[]{Manifest.permission.CAMERA}, TAKE_PHOTO);
        } else {
            // 启动相机程序
            startCamera();
        }


        pictureSave.setOnClickListener(new Camera.pictureSaveFunction());
        //跳转到个人信息填写页面
        submit2=findViewById(R.id.Camera_Submit);
        submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myIndex==0)
                {
                    Intent intent=new Intent(v.getContext(), ConsumerPersonalDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("chosen_Photo", uriden);
                    System.out.println("当前选择图片地址为"+uriden);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
                else if(myIndex==1)
                {
                    Intent intent=new Intent(v.getContext(), DesignerPersonalDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("chosen_Photo", uriden);
                    System.out.println("当前选择图片地址为"+uriden);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
                else if(myIndex==2)
                {
                    Intent intent=new Intent(v.getContext(), CompanyPersonalDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("chosen_Photo", uriden);
                    System.out.println("当前选择图片地址为"+uriden);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            }
        });
//        Button button1=findViewById(R.id.pictureback);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), ConsumerMainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });




    }
    private void startCamera() {
        Intent intent4 = new Intent("android.media.action.IMAGE_CAPTURE");
        // 指定图片的输出地址为imageUri
        intent4.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent4, TAKE_PHOTO);

    }


    private class pictureSaveFunction implements View.OnClickListener {
        public void onClick(View view){
            BitmapDrawable bmpDrawable = (BitmapDrawable) cameraPicture.getDrawable();
            Bitmap bitmap = bmpDrawable.getBitmap();
            saveToSystemGallery(bitmap);//将图片保存到本地
            Toast.makeText(getApplicationContext(),"图片保存成功！",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO:
                if (requestCode == TAKE_PHOTO && resultCode == RESULT_OK) {
                    try {
                        // 将图片解析成Bitmap对象
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        cameraPicture.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
    public void saveToSystemGallery(Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "Pictures");
        if (!appDir.exists()) {
            System.out.println("创建文件夹成功");
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(file.getAbsolutePath())));
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        sendBroadcast(intent);// 发送广播，通知图库更新
        uriden=uri.getPath();
        System.out.println(uriden);
    }

}
