package cn.itcast.handheldclass.Detail_text;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import cn.itcast.handheldclass.BaseActivity;
import cn.itcast.handheldclass.R;

public class FzgsSettlementAgreementActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fzgs_settlement_agreement);
        initNavBar(true,"入驻协议",false);
    }
}