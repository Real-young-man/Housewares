package com.ssj.housewares.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ssj.housewares.R;
import com.ssj.housewares.model.Goods;
import com.ssj.housewares.service.HomeListService;
import com.ssj.housewares.use.ScannerActivity;

public class HomeFragment extends Fragment {

    private EditText goodsNameEditText;
    private TextView goodsCodeTextView;
    private EditText goodsSpecsEditText;
    private EditText goodsExecutiveStandardEditText;
    private EditText goodsManufacturerEditText;
    private EditText goodsAddEditText;
    private EditText goodsTelEdiText;
    private String resultCodeValue;

    public static final String TAG = "HomeFragment";

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 1:
                assert data != null;
                resultCodeValue = data.getStringExtra("data_return");
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button goodsSubmit = view.findViewById(R.id.button_home_goods_submit);

        goodsNameEditText = view.findViewById(R.id.editText_home_goods_name);
        goodsCodeTextView = view.findViewById(R.id.textView_home_goods_id_value);
        goodsSpecsEditText = view.findViewById(R.id.editText_home_goods_specs);
        goodsExecutiveStandardEditText = view.findViewById(R.id.editText_home_goods_es);
        goodsManufacturerEditText = view.findViewById(R.id.editText_home_goods_producer);
        goodsAddEditText = view.findViewById(R.id.editText_home_goods_address);
        goodsTelEdiText = view.findViewById(R.id.editText_home_goods_tel);

        goodsCodeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(v.getContext(), ScannerActivity.class), 1);
                goodsCodeTextView.setText(resultCodeValue );
            }
        });


        goodsSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Goods goods = new Goods();

                goods.setGoodsName(goodsNameEditText.getText().toString());
                goods.setGoodsCode(goodsCodeTextView.getText().toString());
                goods.setGoodsSpecs(goodsSpecsEditText.getText().toString());
                goods.setGoodsExecutiveStandard(goodsExecutiveStandardEditText.getText().toString());
                goods.setGoodsManufacturer(goodsManufacturerEditText.getText().toString());
                goods.setGoodsAdd(goodsAddEditText.getText().toString());
                goods.setGoodsTel(goodsTelEdiText.getText().toString());

                HomeListService homeListService = new HomeListService(v.getContext());
                boolean isSuccess = homeListService.insertGoodsInfo(goods);
                if (!isSuccess) {
                    Toast.makeText(v.getContext(), "插入成功", Toast.LENGTH_SHORT).show();
                    goodsNameEditText.setText("");
                    goodsCodeTextView.setText("");
                    goodsSpecsEditText.setText("");
                    goodsExecutiveStandardEditText.setText("");
                    goodsManufacturerEditText.setText("");
                    goodsAddEditText.setText("");
                    goodsTelEdiText.setText("");
                } else {
                    Toast.makeText(v.getContext(), "插入失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }


}
