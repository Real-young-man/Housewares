package com.ssj.housewares.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.VERSION_CODE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据库时的回调
        String sqlCreateUser = "CREATE TABLE user(" +
                "  user_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "  user_name TEXT," +
                "  password TEXT" +
                ");";
        String sqlCreateGoods = "CREATE TABLE goods(" +
                "  goods_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +//id
                "  goods_code TEXT," +     //编号
                "  goods_name TEXT," +          //名字
                "  goods_specs TEXT," +         //规格
                "  goods_executive_standard TEXT," + //执行标准
                "  goods_manufacturer TEXT," +  //生产商
                "  goods_add TEXT," +           //地址
                "  goods_tel TEXT," +           //电话
                "goods_local TEXT" +            //货架号
                ");";
        String sqlCreateEntry = "create table entry(" +
                "  goods_id INTEGER NOT NULL NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "  goods_name TEXT," +
                "  goods_price RAL," +
                "  goods_number INTEGER," +
                "  goods_total_price RAL " +
                ");";
        String sqlInsertUser = "INSERT INTO " +
                " user(user_name,password) " +
                " VALUES('ssj','qwe123456');";
        String sqlInsertGoods = "INSERT INTO goods(goods_name,goods_code,goods_specs " +
                " ,goods_executive_standard,goods_manufacturer,goods_add,goods_tel,goods_local) " +
                " VALUES('阿米洛机械键盘','6924427307310' " +
                " ,'442*137*33','GB/T14081-2010'," +
                " '深圳市志海和科技有限公司', " +
                " '深圳市光明新区田寮社区第十工业区3栋二楼B区','0755-26484009','1');";
        db.execSQL(sqlCreateUser);
        db.execSQL(sqlCreateGoods);
        db.execSQL(sqlCreateEntry);
        db.execSQL(sqlInsertUser);
        db.execSQL(sqlInsertGoods);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
