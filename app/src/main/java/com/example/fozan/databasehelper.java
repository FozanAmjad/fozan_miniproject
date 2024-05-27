package com.example.fozan;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class databasehelper extends SQLiteOpenHelper{
    public static final String dname="inven.db";
    public static final String tblname = "details";
    public static final String p_id = "Product_ID";
    public static final String p_name = "Product_Name";
    public static final String p_quantity = "Product_Quantity";
    public static final String c_pr = "Cost_Price";
    public static final String s_pr = "Selling_Price";
    public static final String exp = "Expected_Profits";


    public databasehelper(Context context) {super(context, dname,null,1);}


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+tblname+" ("+p_id+" TEXT PRIMARY KEY, "+p_name+" TEXT, "+p_quantity+" TEXT, "+c_pr+" TEXT, "+s_pr+" TEXT, "+exp+" TEXT )";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldv, int newv) {
        db.execSQL("DROP TABLE IF EXISTS "+tblname);
        onCreate(db);
    }

    public boolean insertproduct( String Product_ID, String Product_Name, String Product_Quantity,
                           String Cost_Price, String Selling_Price, String Expected_Profits){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(p_id,Product_ID);
        contentValues.put(p_name,Product_Name);
        contentValues.put(p_quantity,Product_Quantity);
        contentValues.put(c_pr,Cost_Price);
        contentValues.put(s_pr,Selling_Price);
        contentValues.put(exp,Expected_Profits);


        long results=db.insert(tblname,null,contentValues);
        db.close();
        if(results==-1)
            return false;
            else
                return true;
    }

    //update data codes here
    public boolean updateCostPrice(String Product_ID,String Selling_Price)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(s_pr,Selling_Price);
        db.update(tblname,contentValues,"Product_ID=?",new String[]{Product_ID});
        return true;
    }

    public Integer deleteproduct(String pid){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(tblname,"Product_ID=?", new String []{pid});
    }

    public Cursor viewproduct(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+tblname, null);
        return cursor;
    }



}
