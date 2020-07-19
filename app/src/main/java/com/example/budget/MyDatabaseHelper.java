package com.example.budget;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME="mydb.db";
    private static final String DATABASE_TABLE="mytable";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_ITEM="items";
    private static final String COLUMN_PRICE= "price";
    private static final String HASH_CODE= "hashcode";
    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context= context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query=
                "CREATE TABLE " + DATABASE_TABLE +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_ITEM + " TEXT, " +
                        COLUMN_PRICE + " TEXT, " +
                        HASH_CODE + " TEXT);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(sqLiteDatabase);
    }

    void add(String items, String price, String Hashcode) {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put(COLUMN_ITEM, items);
        cv.put(COLUMN_PRICE, price);
        cv.put(HASH_CODE, Hashcode);
       long res= db.insert(DATABASE_TABLE, null, cv);

       if (res==-1) Toast.makeText(context, "Data not inserted!", Toast.LENGTH_SHORT).show();
        else Toast.makeText(context, "Data Successfully inserted!", Toast.LENGTH_SHORT).show();
    }

    Cursor readAllData() {
        String query= "SELECT * FROM " + DATABASE_TABLE;
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor= null;
        if (db!=null) {
            cursor= db.rawQuery(query, null);
        }
        return cursor;
    }

    public void deleteAll() {
        MyDatabaseHelper helper= new MyDatabaseHelper(context);
        SQLiteDatabase db= helper.getWritableDatabase();
        db.delete(DATABASE_TABLE,null,null);
    }

    public boolean deleteTitle(String id_)
    {
        MyDatabaseHelper helper= new MyDatabaseHelper(context);
        SQLiteDatabase db= helper.getWritableDatabase();
        return db.delete(DATABASE_TABLE, COLUMN_ID + "=" + id_, null) > 0;
    }

    public boolean deleteTitle_1(String price_11)
    {
        MyDatabaseHelper helper= new MyDatabaseHelper(context);
        SQLiteDatabase db= helper.getWritableDatabase();
        return db.delete(DATABASE_TABLE, COLUMN_PRICE + "=" + price_11, null) > 0;
    }
}