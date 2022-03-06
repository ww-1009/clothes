package cn.itcast.handheldclass.Consumer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;

import java.util.Map;

import cn.itcast.handheldclass.AuthResult;
import cn.itcast.handheldclass.BaseActivity;
import cn.itcast.handheldclass.PayResult;
import cn.itcast.handheldclass.R;
import cn.itcast.handheldclass.util.OrderInfoUtil2_0;

public class ConsumerDesignerXxOwn2Activity extends BaseActivity {


    /**
     * 用于支付宝支付业务的入参 app_id。
     */
    public static final String APPID = "2021000117682553";

    /**
     * 用于支付宝账户登录授权业务的入参 pid。
     */
    public static final String PID = "2088621956075302";

    /**
     * 用于支付宝账户登录授权业务的入参 target_id。
     */
    public static final String TARGET_ID = "tkmtet5147@sandbox.com\n";

    /**
     * pkcs8 格式的商户私钥。
     * <p>
     * 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个，如果两个都设置了，本 Demo 将优先
     * 使用 RSA2_PRIVATE。RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议商户使用
     * RSA2_PRIVATE。
     * <p>
     * 建议使用支付宝提供的公私钥生成工具生成和获取 RSA2_PRIVATE。
     */
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCsvVWlN7nr49pDG3wWRxy/kaO7S7oj16i00+pvOJ+BYoa1BzSlEDOKpP+YZcq6Wd4CeyneCZJEaIBTVAEqhmyd2OzDsWsnKvLzIIqGEptr7fLo2pG2w2QnEfny8ZC0P/pAuTp9WZXpebuMS6TgGzkA2oQr+lsXxUDGgytT+7mDUaUbf5+2m3EmRHXsh79XfzPwJjMt4uQQNz0loc7kFXBHDPsAYu7Pq2YRLMn4mIWTxxZaAeoqHyLao3EhqA6vow+Qc/whOAfW9Cm2Y7vxDOR1aLChTGkx392IcF5vQkaSuLLyLQNxfnWUTljLQG3T1I+mMut8d694CH2NB9GIgnKHAgMBAAECggEAYrAg+C1oOQ6KKgDP2eJy2DeatWzEQan6JqVTsItHBaKGl3Iwb4VcDP/LTwQI2IJzRjObbdFGEXMqrXC24da+w0elrP/12gC9oI2c1pBW30iomMQWr7XdyDyK8nN0WqaakNPCSM8fyK3VqoelAKxM2uH81StJU8FMCp3x6R9Nk2nnMEJ0zoiFVtPrbq2c0zClcz/mMDGqvLFBt11HD/WnSFXtdDuSZGAzsIeoGkl/Ub41mwHHoA2Jd/M4QAvxiOggdiGqvKO8M+LH/JK39+2bZdPzPDluP0AwTPSZmf2diyhy5z0Y9RcCEwoEjK9lCjtWRblVwek8eXBGCJrL0kA6oQKBgQDdhmrr7PhkYLyXgGgIAR2gt9hkRaB9kBHIHNwx7z8SN5mYCq3NJycLDHsdme45k39a4ia7CiCs6xlNBNAqRrpCozRHqst+Ov5ZQ0k2BJfMD8IMBEQscmlBflrStRAhLIOsyHeayHD05Hn4xQVUxn2rSmxZeucEti9qmFbjtPg22QKBgQDHn0xSoylZDWUsxedFc2prCAYW45xtRFkMVpKsOOrwZB1nISF02qvD1Afw+Z0QCy6W4XDqVG18scRrm54+U8/c0JKUfVOMakKqbOB8JFkRNUnsObk5EJdO69D957y9e0eQ9ghiXmrrNfLDOlg9dMVIXc1bPrXp5sARKJEChbfYXwKBgDkyEH8zovp/w5G2VKXka9roPNW+6G4i+YRadzvpUraIZn7MGw3CEyGWKl99M/XnsFUt2OQn/EHJPbIWEQ0pT8QO0VvDrGC+KJexeMCatPq6AtcJbvSu4aBIWHjYyP5XuPOumrr7D7cbGqbxb6vwx9enEoyT3/vmch2qkZyfSAsZAoGBAKMOP62QkQmoo2f7D3nvWJGssCyqJvAp0AMABDahF2bcoBfJPCbTJ1KCMypm+iBMwfWEmFMQO46LNkIfjxaiJzvbMqAP7trXQTUsUgGVPxuLR6KpU96E6tMQVFnRnJ25wS9riFWxgew22OEXG1S3uR3vYPqTMsyoK2LYm/pPd2hJAoGANnSF8l/HTW9rHqzQg8jy3sLjEM++APOGfyPuUpAW7wzxnlrQ076G/lFlSFaZFy/8tuT0PWKKsZmNHsAjSF30wyQnfAdWwNCngGDJs/6orUx9Z/7KNWQjBjLTKEuxlyHm2m3YoDPmP0hn5tDyYr7z0C8W5TqXMThdQvK9p6b4zcs=";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(ConsumerDesignerXxOwn2Activity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(ConsumerDesignerXxOwn2Activity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(ConsumerDesignerXxOwn2Activity.this, "授权成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(ConsumerDesignerXxOwn2Activity.this, "授权失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_designer_xx_own_2);
        initNavBar(true, "预约界面", false);

        //点击预约按钮
        Button yuyue = (Button) findViewById(R.id.image_designer_appointButton);
        yuyue.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * 支付宝支付业务示例
             */
            public void onClick(View v) {
                boolean rsa2 = (RSA2_PRIVATE.length() > 0);
                Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
                String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

                String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
                String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
                final String orderInfo = orderParam + "&" + sign;

                final Runnable payRunnable = new Runnable() {

                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(ConsumerDesignerXxOwn2Activity.this);
                        Map<String, String> result = alipay.payV2(orderInfo, true);
                        Log.i("msp", result.toString());
                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };

                // 必须异步调用
                Thread payThread = new Thread(payRunnable);
                payThread.start();
            }

        });
    }

}