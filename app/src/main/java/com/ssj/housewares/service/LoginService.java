package com.ssj.housewares.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ssj.housewares.utils.DatabaseHelper;

public class LoginService {
    private DatabaseHelper mDatabaseHelper;

    public LoginService(Context context) {
        mDatabaseHelper = new DatabaseHelper(context);
    }

    public boolean isLogin(String name, String password) {
        SQLiteDatabase sqLiteDatabase = mDatabaseHelper.getReadableDatabase();
        String sqlLogin = "select user_name,password from user where user_name = ? and password = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(sqlLogin, new String[]{name, password});
        if (cursor.moveToNext()) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }
}
