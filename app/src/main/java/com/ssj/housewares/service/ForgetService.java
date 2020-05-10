package com.ssj.housewares.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ssj.housewares.utils.DatabaseHelper;

import java.sql.Statement;

public class ForgetService {
    private DatabaseHelper mDatabaseHelper;
    public static final String TAG = "ForgetService";

    public ForgetService(Context context) {
        mDatabaseHelper = new DatabaseHelper(context);
    }

    public boolean isForget(String userName, String password) {
        SQLiteDatabase database = mDatabaseHelper.getWritableDatabase();
        String sqlForget = "update user set password = ? where user_name = ?";
        if (isUser(userName)) {
            database.execSQL(sqlForget, new String[]{password, userName});
            return true;
        }else{
            return false;
        }

    }

    private boolean isUser(String userName) {
        SQLiteDatabase database = mDatabaseHelper.getReadableDatabase();
        String sqlIsUser = "select user_id from user where user_name = ?";
        Cursor cursor = database.rawQuery(sqlIsUser, new String[]{userName});
        if (cursor.moveToNext()) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

}
