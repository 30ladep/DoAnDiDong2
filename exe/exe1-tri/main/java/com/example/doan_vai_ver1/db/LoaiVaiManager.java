package com.example.doan_vai_ver1.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.doan_vai_ver1.Object.Vai;

import java.util.ArrayList;

public class LoaiVaiManager {
    DBHelper dbHelper;
    SQLiteDatabase db;

    public LoaiVaiManager(Context context){
        dbHelper = new DBHelper(context);
    }

    public ArrayList<Vai> LayDL(){
        ArrayList<Vai> data = new ArrayList<>();
        String sql = "select * from LOAIVAI order by ID desc";
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if((cursor.getCount() > 0) && cursor != null){
            int i = cursor.getCount();
            do
            {
                Vai vai = new Vai();
                vai.setStt(i--);
                vai.setVai_ms(cursor.getString(1));
                vai.setVai_ten(cursor.getString(2));
                vai.setVai_xuatxu(cursor.getString(3));
                data.add(vai);
            }
            while (cursor.moveToNext());
        }

        return data;
    }
    public String getMA(String ten){

        String sql = "select * from LOAIVAI where TEN = '" + ten +"'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        String ketqua = cursor.getString(1);
        return ketqua;
    }

    public int Themsp(Vai vai){
        db = dbHelper.getWritableDatabase();
        //kiem tra san pham có tồn tại chưa
        String sql = "select * from LOAIVAI where MA = '" + vai.getVai_ms()+"'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor.getCount() == 0){
            ContentValues values = new ContentValues();
            values.put("MA", vai.getVai_ms());
            values.put("TEN", vai.getVai_ten());
            values.put("XUATXU", vai.getVai_xuatxu());
            db.insert("LOAIVAI", null, values);
        }
        return cursor.getCount();
    }

    public void Sua(Vai vai)
    {
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MA", vai.getVai_ms());
        values.put("TEN", vai.getVai_ten());
        values.put("XUATXU", vai.getVai_xuatxu());
        db.update("LoaiVai",values,"ID = '"+ vai.getStt() +"'",null);
    }


    public int DelProduct(String val){
        db = dbHelper.getWritableDatabase();
        String sql = "select * from PHIEUNHAP where MA = '"+ val +"'";
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.getCount() > 0){
            return -1;
        }
        else
        {
            int resuilt = db.delete("LOAIVAI", "MA =?",new String[]{val});
            return resuilt;
        }
    }
}
