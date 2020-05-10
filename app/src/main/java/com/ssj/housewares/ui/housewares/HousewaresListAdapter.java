package com.ssj.housewares.ui.housewares;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ssj.housewares.R;
import com.ssj.housewares.service.HomeListService;


public class HousewaresListAdapter extends RecyclerView.Adapter {

    private static final String TAG = "HousewaresListAdapter";

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_housewares, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mGoodsLocalValuesTextView;
        private TextView mGoodsNameValuesTextView;
        private Button mShowGoodsButton;

        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            mGoodsNameValuesTextView = itemView.findViewById(R.id.textView_item_goods_name_values);
            mGoodsLocalValuesTextView = itemView.findViewById(R.id.textView_item_goods_local_values);
            mShowGoodsButton = itemView.findViewById(R.id.button_item_goods_show);

        }

        public void bindView(int position) {
            HomeListService homeListService = new HomeListService(itemView.getContext());
            mGoodsNameValuesTextView.setText(homeListService.getGoodsInfo(position).getGoodsName());
            Log.d(TAG, "bindView: " + homeListService.getGoodsInfo(position).getGoodsName());
            mGoodsLocalValuesTextView.setText(homeListService.getGoodsInfo(position).getGoodsLocal());
            mShowGoodsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(),"111",Toast.LENGTH_SHORT).show();
                }
            });
        }


        @Override
        public void onClick(View v) {

        }
    }
}
