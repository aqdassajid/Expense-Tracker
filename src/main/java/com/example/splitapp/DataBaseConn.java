package com.example.splitapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
public class DataBaseConn extends SQLiteOpenHelper {

    public DataBaseConn(Context context) {
        super(context, "BBdb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table BBTable(amount Text,date TEXT,radio TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Cursor getAllExpenses()
    {
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor c=database.rawQuery("Select * from BBTable",null);
        return c;
    }

    public long insertExpense(String amount, String date, String category) {
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("amount",amount);
        contentValues.put("date",date);
        contentValues.put("radio",category);
        long res=database.insert("BBTable",null,contentValues);
        if(res==-1)
            return res;
        else
            return 1;
    }
}

