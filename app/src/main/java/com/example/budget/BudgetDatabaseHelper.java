package com.example.budget;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class BudgetDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="mybudget.db";
    private static final String TABLE_NAME="budgettable";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_BUDGET="budget";
    private Context context;

    public BudgetDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context= context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query=
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_BUDGET + " TEXT);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    void add(String budgett) {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put(COLUMN_BUDGET, budgett);
        long res= db.insert(TABLE_NAME, null, cv);

        if (res==-1) Toast.makeText(context, "Data not inserted!", Toast.LENGTH_SHORT).show();
        else Toast.makeText(context, "Data Successfully inserted!", Toast.LENGTH_SHORT).show();
    }

    Cursor readAllData() {
        String query= "SELECT * FROM " + TABLE_NAME;
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
        db.delete(TABLE_NAME,null,null);
    }

    public boolean deleteTitle(String data)
    {
        MyDatabaseHelper helper= new MyDatabaseHelper(context);
        SQLiteDatabase db= helper.getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_BUDGET + "=" + data, null) > 0;
    }


}