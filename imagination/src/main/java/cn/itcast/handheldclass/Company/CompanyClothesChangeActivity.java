package cn.itcast.handheldclass.Company;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.opencv.BuildConfig;
import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

import cn.itcast.handheldclass.BaseActivity;
import cn.itcast.handheldclass.Consumer.ConsumerClothesChangeActivity;
import cn.itcast.handheldclass.LoginActivity;
import cn.itcast.handheldclass.R;
import cn.itcast.handheldclass.RegisterActivity;

public class CompanyClothesChangeActivity extends BaseActivity {
    private Bitmap person_BitMap;
    private ImageView person;
    private ImageView photo1;
    private ImageView photo2;
    private ImageView photo3;
    private Bitmap canny_PersonBitMap;
    private Bitmap canny_ClothBitMap;
    private Bitmap gray_PersonBitMap;
    private Bitmap gray_ClothBitMap;
    private Bitmap person_ChangedBitMap;
    private Bitmap new_ClothBitMap;
    private Bitmap cloth_BitMap;
    private static final String TAG = "gao_chun";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_clothes_change);
        System.loadLibrary("opencv_java4");
        initNavBar(true,"形象打造",false);
        person = findViewById(R.id.clothes_changing_ImageView);

        Bundle bundle = this.getIntent().getExtras();
        assert bundle != null;
        final String imagePath = bundle.getString("chosen_Photo");
        if(imagePath!=null){
            person_BitMap = BitmapFactory.decodeFile(imagePath);
            System.out.println(imagePath+person_BitMap.getHeight()+person_BitMap.getWidth());
            person.setImageBitmap(person_BitMap);
        }else {
            Toast.makeText(this,"得到图片失败",Toast.LENGTH_SHORT).show();
        }
        photo1 = findViewById(R.id.clothes_changing_photoslist_1);
        photo2 = findViewById(R.id.clothes_changing_photoslist_2);
        photo3 = findViewById(R.id.clothes_changing_photoslist_3);
        photo1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                cloth_BitMap = BitmapFactory.decodeStream(getClass().getResourceAsStream
                        ("/res/drawable/cloth_gray.jpg"));
                System.out.println("获得第一个图片"+cloth_BitMap.getHeight()+cloth_BitMap.getWidth());
                change_Imagination(person_BitMap, cloth_BitMap);
                person.setImageBitmap(person_ChangedBitMap);

            }
        });
        photo2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                cloth_BitMap = BitmapFactory.decodeStream(getClass().getResourceAsStream
                        ("/res/drawable/cloth_ex2.png"));
                System.out.println("获得第二个图片"+cloth_BitMap.getHeight()+cloth_BitMap.getWidth());
                change_Imagination(person_BitMap, cloth_BitMap);
                person.setImageBitmap(person_ChangedBitMap);
            }
        });
        photo3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                cloth_BitMap = BitmapFactory.decodeStream(getClass().getResourceAsStream
                        ("/res/drawable/cloth_ex3.jpg"));
                System.out.println("获得第三个图片"+cloth_BitMap.getHeight()+cloth_BitMap.getWidth());
                change_Imagination(person_BitMap, cloth_BitMap);
                person.setImageBitmap(person_ChangedBitMap);
            }
        });

        //购买衣服
        Button buy= (Button) findViewById(R.id.buy);
        buy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(CompanyClothesChangeActivity.this , CompanyProductDisplayActivity.class);
                //启动
                startActivity(i);
            }

        });

    }
    private Bitmap getLocalBitmap(String path) {
        Bitmap bitmap = null;
        try {
            FileInputStream fis = new FileInputStream(path);
            bitmap = BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
            /* try catch  可以解决OOM后出现的崩溃，然后采取相应的解决措施，如缩小图片，较少内存使用
             * 但这不是解决OOM的根本方法，因为这个地方是压缩骆驼的最后一颗稻草，
             * 解决方法是dump内存，找到内存异常原因。*/
        } catch (OutOfMemoryError error) {
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
                bitmap = null;
            }
            System.gc();
        }
        return bitmap;
    }
    @Override
    public void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
        //super.onResume();
        //OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_9, this, mLoaderCallback);
    }


    //OpenCV库加载并初始化成功后的回调函数
    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {

        @Override
        public void onManagerConnected(int status) {
            // TODO Auto-generated method stub
            switch (status) {
                case BaseLoaderCallback.SUCCESS:
                    Log.i(TAG, "成功加载");
                    break;
                default:
                    super.onManagerConnected(status);
                    Log.i(TAG, "加载失败");
                    break;
            }
        }
    };
    public TreeMap<Integer, ArrayList> get_Outside_Edge(Mat mat) {
        TreeMap<Integer, ArrayList> outside_Edge = new TreeMap<>();
        for (int i = 0; i < mat.height(); i++) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            for (int j = 0; j < mat.width(); j++) {
                if (mat.get(i, j)[0] >= 50) {
                    arrayList.add(j);
                }
            }
            for (int j = 0; j < mat.width(); j++) {
                if (arrayList.size() != 0 && j != arrayList.get(0) && j != arrayList.get(arrayList.size() - 1)) {
                    mat.put(i, j, 0);
                }
            }
            if (arrayList.size() != 0) {
                ArrayList new_List = new ArrayList();
                new_List.add(arrayList.get(0));
                new_List.add(arrayList.get(arrayList.size() - 1));
                outside_Edge.put(i, new_List);
            }

        }
        return outside_Edge;
    }

    public TreeMap<Integer, ArrayList> get_Position(TreeMap outside_PersonEdge, double head) {
        TreeMap<Integer, ArrayList> arr_position = new TreeMap<>();
        double height_min_coe = 1.15;
        double height_max_coe = 4.2;
        Iterator iter = outside_PersonEdge.keySet().iterator();
        int top_Margin = (int) outside_PersonEdge.firstKey();
        while (iter.hasNext()) {
            int x = (Integer) iter.next();
            if (x >= height_min_coe * head + top_Margin && x <= height_max_coe * head + top_Margin) {
                arr_position.put(x, (ArrayList) outside_PersonEdge.get(x));
            }

        }
        return arr_position;
    }

    public int findBestWidth(int cloth_should_height, Mat rgb_ClothMat, Mat rgb_PersonMat, TreeMap<Integer, ArrayList> cloth_arr_position) {
        ArrayList<Integer> best_width = new ArrayList<>();
        try {
            for (int k = rgb_PersonMat.width()-1; k >= 100; k=k-20) {
                Mat new_Mat = new Mat();
                Mat new_GrayMat = new Mat();
                Mat new_CannyMat = new Mat();
                Size size = new Size(k, cloth_should_height + 1);
                Imgproc.resize(rgb_ClothMat, new_Mat, size);
                Imgproc.cvtColor(new_Mat, new_GrayMat, Imgproc.COLOR_RGB2GRAY);
                Imgproc.Canny(new_Mat, new_CannyMat, 10,  150);
                TreeMap<Integer, ArrayList> new_OutsideEdge = get_Outside_Edge(new_CannyMat);
                Iterator iter_ClothPos = cloth_arr_position.keySet().iterator();
                Iterator iter_Cloth = new_OutsideEdge.keySet().iterator();
                while (iter_Cloth.hasNext()) {
                    int x_ClothPos = (Integer) iter_ClothPos.next();
                    int x_Cloth = (Integer) iter_Cloth.next();
                    ArrayList list_ClothPos = cloth_arr_position.get(x_ClothPos);
                    ArrayList list_Cloth = new_OutsideEdge.get(x_Cloth);
                    int gap_ClothPos = (int) list_ClothPos.get(1) - (int) list_ClothPos.get(0);
                    int gap_Cloth = (int) list_Cloth.get(1) - (int) list_Cloth.get(0);
                    System.out.println("k="+k+" "+gap_Cloth+" "+gap_ClothPos);
                    if (BuildConfig.DEBUG && gap_Cloth < gap_ClothPos) {
                        throw new AssertionError("Assertion failed");
                    }

                }
                best_width.add(k);
                System.out.println("已加入"+k);

            }
        } catch (AssertionError e) {
            System.out.println("捕获" + e.getMessage() + "异常");
            if (best_width.size() == 0)
            {
                return 450;
            }
            return best_width.get(best_width.size() - 1);
        }
        return best_width.get(best_width.size() - 1);
    }
    public Mat changed_Pixel(Mat new_RgbClothMat, TreeMap<Integer, ArrayList> cloth_arr_position, Mat rgb_PersonMat, Mat gray_PersonMat)
    {
        Mat new_CannyClothMat = new Mat();
        Imgproc.Canny(new_RgbClothMat, new_CannyClothMat, 50, 200);
        TreeMap<Integer, ArrayList> new_ClothOutsideEdge = get_Outside_Edge(new_CannyClothMat);
        int top_margin = (int) ((cloth_arr_position.lastKey()-cloth_arr_position.firstKey())*0.025);
        int bottom_margin = (int) ((cloth_arr_position.lastKey()-cloth_arr_position.firstKey())*0.025);
        Iterator iter_person = cloth_arr_position.keySet().iterator();
        Iterator iter_cloth = new_ClothOutsideEdge.keySet().iterator();
        while (iter_person.hasNext())
        {   int x_Cloth = (int)iter_cloth.next();
            int x_Person = (int)iter_person.next();
            ArrayList y_Cloth = new_ClothOutsideEdge.get(x_Cloth);
            ArrayList y_Person = cloth_arr_position.get(x_Person);
            float gap_Cloth = (int)y_Cloth.get(1) - (int)y_Cloth.get(0);
            float gap_Person = (int)y_Person.get(1) - (int)y_Person.get(0);
            float k = gap_Cloth / gap_Person;
            for(int i = (int)y_Person.get(0);i<=(int)y_Person.get(1)+1;i++)
            {
                int person_index = i - (int)y_Person.get(0);
                double transient_index = person_index * k;
                int should_index_1 = Double.valueOf(transient_index).intValue();
                int should_index_2 = should_index_1 + 1;
                double distance  = transient_index - should_index_1;
                int new_col_1 = should_index_1 + (int)y_Cloth.get(0);
                int new_col_2 = should_index_2 + (int)y_Cloth.get(0);
                double[] changed_pixel = mult(changed(new_RgbClothMat.get(x_Cloth, new_col_1), distance),
                        changed(new_RgbClothMat.get(x_Cloth, new_col_2),(1-distance))) ;
                double[] initial_pixel = rgb_PersonMat.get(x_Person, i);
                if(initial_pixel[1]+initial_pixel[2]+initial_pixel[3]<=763)
                    if(changed_pixel[1]+changed_pixel[2]+changed_pixel[3]<=765)
                        if(cloth_arr_position.firstKey()<=x_Person&&x_Person<=cloth_arr_position.firstKey()+
                                top_margin||cloth_arr_position.lastKey()
                                -bottom_margin<=x_Person&&x_Person<=cloth_arr_position.lastKey())
                        {
                            if (gray_PersonMat.get(x_Person, i)[0]<=90)
                            {
                                rgb_PersonMat.put(x_Person, i, changed_pixel);
                            }
                        }
                        else
                        {
                            rgb_PersonMat.put(x_Person, i, changed_pixel);
                        }

            }

        }
        return rgb_PersonMat;
    }
    public double[] changed(double[] pixel, double distance){
        for(int i=0;i<pixel.length;i++)
        {
            pixel[i] = pixel[i] *distance;
        }
        return pixel;
    }
    public double[] mult(double[] pixel1, double[] pixel2)
    {   double[] pixel = new double[pixel1.length];
        for(int i=0;i<pixel1.length;i++)
        {
            pixel[i] = pixel1[i] + pixel2[i];
        }
        return pixel;
    }
    public void change_Imagination(Bitmap person_BitMap, Bitmap cloth_BitMap){
        Mat rgb_PersonMat = new Mat();
        Mat gray_PersonMat = new Mat();
        Mat canny_PersonMat = new Mat();
        Mat new_RgbClothMat = new Mat();
        Mat rgb_ClothMat = new Mat();
        Mat gray_ClothMat = new Mat();
        Mat canny_ClothMat = new Mat();
        Mat person_ChangedMat = new Mat();
        gray_PersonBitMap = Bitmap.createBitmap(person_BitMap.getWidth(), person_BitMap.getHeight(), Bitmap.Config.RGB_565);
        canny_PersonBitMap = Bitmap.createBitmap(person_BitMap.getWidth(), person_BitMap.getHeight(), Bitmap.Config.RGB_565);
        gray_ClothBitMap = Bitmap.createBitmap(cloth_BitMap.getWidth(), cloth_BitMap.getHeight(), Bitmap.Config.RGB_565);
        canny_ClothBitMap = Bitmap.createBitmap(cloth_BitMap.getWidth(), cloth_BitMap.getHeight(), Bitmap.Config.RGB_565);
        person_ChangedBitMap = Bitmap.createBitmap(person_BitMap.getWidth(), person_BitMap.getHeight(), Bitmap.Config.RGB_565);
        Utils.bitmapToMat(person_BitMap, rgb_PersonMat);
        Utils.bitmapToMat(cloth_BitMap, rgb_ClothMat);
        Imgproc.cvtColor(rgb_PersonMat, gray_PersonMat, Imgproc.COLOR_RGB2GRAY);
        Imgproc.cvtColor(rgb_ClothMat, gray_ClothMat, Imgproc.COLOR_RGB2GRAY);
        Imgproc.Canny(gray_PersonMat, canny_PersonMat,50, 200);
        Imgproc.Canny(gray_ClothMat, canny_ClothMat,50, 200);
        TreeMap<Integer, ArrayList> outside_PersonEdge = get_Outside_Edge(canny_PersonMat);
        TreeMap<Integer, ArrayList> outside_ClothEdge = get_Outside_Edge(canny_ClothMat);
        int person_Height = outside_PersonEdge.lastKey()-outside_PersonEdge.firstKey();
        double head  = person_Height / 7.5;
        System.out.println("head="+head
        );
        TreeMap<Integer, ArrayList> cloth_arr_position = get_Position(outside_PersonEdge, head);
        int cloth_should_height = cloth_arr_position.lastKey()-cloth_arr_position.firstKey();
        int best_width_cloth = findBestWidth(cloth_should_height, rgb_ClothMat, rgb_PersonMat, cloth_arr_position);
        System.out.println("已找到最合适的width="+best_width_cloth);
        Size newSize = new Size(best_width_cloth, cloth_should_height+1);
        Imgproc.resize(rgb_ClothMat, new_RgbClothMat, newSize);
        new_ClothBitMap = Bitmap.createBitmap(best_width_cloth, cloth_should_height+1, Bitmap.Config.RGB_565);
        person_ChangedMat = changed_Pixel(new_RgbClothMat, cloth_arr_position, rgb_PersonMat, gray_PersonMat);
        Utils.matToBitmap(gray_PersonMat, gray_PersonBitMap);
        Utils.matToBitmap(canny_PersonMat, canny_PersonBitMap);
        Utils.matToBitmap(gray_ClothMat, gray_ClothBitMap);
        Utils.matToBitmap(canny_ClothMat, canny_ClothBitMap);
        Utils.matToBitmap(new_RgbClothMat, new_ClothBitMap);
        Utils.matToBitmap(person_ChangedMat, person_ChangedBitMap);
        Log.i(TAG, "Canny success...");
    }



}