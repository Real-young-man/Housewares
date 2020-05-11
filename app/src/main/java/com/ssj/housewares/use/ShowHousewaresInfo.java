package com.ssj.housewares.use;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.ssj.housewares.R;
import com.ssj.housewares.model.Goods;
import com.ssj.housewares.service.HomeListService;

public class ShowHousewaresInfo extends AppCompatActivity {

    private TextView mNameTextView;
    private TextView mCodeTextView;
    private TextView mSpecsEditText;
    private TextView mExecutiveStandardTextView;
    private TextView mManufacturerTextView;
    private TextView mAddTextView;
    private TextView mTelTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_housewares_info);

        Intent intent = getIntent();

        String goodsId = intent.getStringExtra("id");

        HomeListService homeListService = new HomeListService(this);

        Goods goodsInfo = homeListService.getAllGoodsInfo(goodsId);

        mNameTextView = findViewById(R.id.textView_show_goods_name_value);
        mCodeTextView = findViewById(R.id.textView_show_goods_code_value);
        mSpecsEditText = findViewById(R.id.textView_show_goods_specs_value);
        mExecutiveStandardTextView = findViewById(R.id.textView_show_goods_es_value);
        mManufacturerTextView = findViewById(R.id.textView_show_goods_producer_value);
        mAddTextView = findViewById(R.id.textView_show_goods_address_value);
        mTelTextView = findViewById(R.id.textView_show_goods_tel_value);

        mNameTextView.setText(goodsInfo.getGoodsName());
        mCodeTextView.setText(goodsInfo.getGoodsCode());
        mSpecsEditText.setText(goodsInfo.getGoodsSpecs());
        mExecutiveStandardTextView.setText(goodsInfo.getGoodsExecutiveStandard());
        mManufacturerTextView.setText(goodsInfo.getGoodsManufacturer());
        mAddTextView.setText(goodsInfo.getGoodsAdd());
        mTelTextView.setText(goodsInfo.getGoodsTel());



    }
}
