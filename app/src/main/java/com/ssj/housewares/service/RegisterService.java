package com.ssj.housewares.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ssj.housewares.model.User;
import com.ssj.housewares.utils.DatabaseHelper;

public class RegisterService {
    private DatabaseHelper mDatabaseHelper;

    public RegisterService(Context context) {
        mDatabaseHelper = new DatabaseHelper(context);
    }

    public boolean isRegister(String userName,String password){
        SQLiteDatabase sqLiteDatabase = mDatabaseHelper.getReadableDatabase();
        String sqlCheckRepeat ="select user_name from user where user_name = ? ";
        Cursor cursorCheckRepeat = sqLiteDatabase.rawQuery(sqlCheckRepeat, new String[]{userName});
        if(cursorCheckRepeat.moveToNext()){
            return false;
        }
        String sqlRegister = "insert into user(user_name,password) values (?,?);";
        sqLiteDatabase.execSQL(sqlRegister, new String[]{userName, password});
        return true;
    }

}
