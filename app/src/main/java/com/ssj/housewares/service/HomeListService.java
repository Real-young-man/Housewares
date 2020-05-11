package com.ssj.housewares.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ssj.housewares.model.Goods;
import com.ssj.housewares.utils.DatabaseHelper;

public class HomeListService {
    private DatabaseHelper mDatabaseHelper;

    public HomeListService(Context context) {
        mDatabaseHelper = new DatabaseHelper(context);
    }

    public Goods getGoodsInfo(int id) {

        Goods goods = new Goods();
        SQLiteDatabase sqLiteDatabase = mDatabaseHelper.getReadableDatabase();
        String goodsInfoSQL = "select goods_name,goods_local from goods";
        Cursor cursor = sqLiteDatabase.rawQuery(goodsInfoSQL, null);
        while (cursor.moveToNext()) {
            goods.setGoodsName(cursor.getString(cursor.getColumnIndex("goods_name")));
            goods.setGoodsLocal(cursor.getString(cursor.getColumnIndex("goods_local")));
        }
        cursor.close();
        return goods;
    }

    public Goods getAllGoodsInfo(String id) {

        Goods goods = new Goods();

        SQLiteDatabase sqLiteDatabase = mDatabaseHelper.getReadableDatabase();
        String goodsAllInfo = "select goods_code,goods_name,goods_specs,goods_executive_standard " +
                " ,goods_manufacturer,goods_add,goods_tel from goods";
        Cursor cursor = sqLiteDatabase.rawQuery(goodsAllInfo, null);

        while (cursor.moveToNext()) {
            goods.setGoodsName(cursor.getString(cursor.getColumnIndex("goods_name")));
            goods.setGoodsCode(cursor.getString(cursor.getColumnIndex("goods_code")));
            goods.setGoodsManufacturer(cursor.getString(cursor.getColumnIndex("goods_manufacturer")));
            goods.setGoodsExecutiveStandard(cursor.getString(cursor.getColumnIndex("goods_executive_standard")));
            goods.setGoodsSpecs(cursor.getString(cursor.getColumnIndex("goods_specs")));
            goods.setGoodsAdd(cursor.getString(cursor.getColumnIndex("goods_add")));
            goods.setGoodsTel(cursor.getString(cursor.getColumnIndex("goods_tel")));
        }

        cursor.close();
        return goods;
    }

    public boolean insertGoodsInfo(Goods goods) {

        SQLiteDatabase sqLiteDatabase = mDatabaseHelper.getWritableDatabase();
        String insertGoodsInfoSQL = "INSERT INTO goods(goods_name,goods_code ,goods_specs " +
                " ,goods_executive_standard,goods_manufacturer,goods_add,goods_tel,goods_local) " +
                " VALUES(?,?,?,?,?,?,?,?);";
        Cursor cursor = sqLiteDatabase.rawQuery(insertGoodsInfoSQL, new String[]{goods.getGoodsName(), goods.getGoodsCode(), goods.getGoodsSpecs(),
                goods.getGoodsExecutiveStandard(), goods.getGoodsManufacturer(), goods.getGoodsAdd(), goods.getGoodsTel(), Integer.parseInt(String.valueOf(Math.round(Math.random() * 10))) + ""});
        if (cursor.moveToNext()) {
            cursor.close();
            return true;
        }

        cursor.close();
        return false;

    }

    public int getGoodsCount() {

        SQLiteDatabase sqLiteDatabase = mDatabaseHelper.getReadableDatabase();
        String goodsCountSQL = "select  max(id) from goods";
        Cursor cursor = sqLiteDatabase.rawQuery(goodsCountSQL, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;

    }
}
