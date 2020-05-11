package com.ssj.housewares.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ssj.housewares.R;
import com.ssj.housewares.model.Goods;
import com.ssj.housewares.service.HomeListService;

public class HomeFragment extends Fragment {

    private EditText goodsNameEditText;
    private EditText goodsCodeEditText;
    private EditText goodsSpecsEditText;
    private EditText goodsExecutiveStandardEditText;
    private EditText goodsManufacturerEditText;
    private EditText goodsAddEditText;
    private EditText goodsTelEdiText;

    public static final String TAG = "HomeFragment";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button goodsSubmit = view.findViewById(R.id.button_home_goods_submit);

        goodsNameEditText = view.findViewById(R.id.editText_home_goods_name);
        goodsCodeEditText = view.findViewById(R.id.editText_home_goods_id);
        goodsSpecsEditText = view.findViewById(R.id.editText_home_goods_specs);
        goodsExecutiveStandardEditText = view.findViewById(R.id.editText_home_goods_es);
        goodsManufacturerEditText = view.findViewById(R.id.editText_home_goods_producer);
        goodsAddEditText = view.findViewById(R.id.editText_home_goods_address);
        goodsTelEdiText = view.findViewById(R.id.editText_home_goods_tel);


        goodsSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Goods goods = new Goods();

                goods.setGoodsName(goodsNameEditText.getText().toString());
                goods.setGoodsCode(goodsCodeEditText.getText().toString());
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
                    goodsCodeEditText.setText("");
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
