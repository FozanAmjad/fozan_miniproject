package com.example.fozan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class accountdatabase extends SQLiteOpenHelper {
    public static final String dname="acc.db";
    public static final String tblname = "account_details";
    public static final String fn = "first_name";
    public static final String ln = "last_name";
    public static final String em = "email";
    public static final String phn = "phone_number";
    public static final String pass = "password";

    public accountdatabase(Context context) {super(context, dname,null,1);}


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+tblname+" ("+fn+" TEXT , "+ln+" TEXT, "+em+" TEXT, "+phn+" TEXT PRIMARY KEY, "+pass+" TEXT )";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldv, int newv) {
        db.execSQL("DROP TABLE IF EXISTS "+tblname);
        onCreate(db);
    }

    public boolean createacc( String first_name, String last_name, String email,
                                  String phone_number, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(fn,first_name);
        contentValues.put(ln,last_name);
        contentValues.put(em,email);
        contentValues.put(phn,phone_number);
        contentValues.put(pass,password);


        long results=db.insert(tblname,null,contentValues);
        db.close();
        if(results==-1)
            return false;
        else
            return true;
    }

    public String loginaccount(String phone_number){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+tblname+" WHERE "+phn+" ='"+phone_number+"'",null);
        cursor.moveToFirst();
        String Password=cursor.getString(4);
        db.close();
        cursor.close();
        return Password;
    }
}
