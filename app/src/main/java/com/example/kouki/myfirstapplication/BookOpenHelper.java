package com.example.kouki.myfirstapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookOpenHelper extends SQLiteOpenHelper {

    // DB version
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "main.db";

    private static final String BOOK_TABLE_CREATE =
            "CREATE TABLE " + Book.BOOK_TABLE_NAME + " (" +
                    Book._ID + " INTEGER PRIMARY KEY," +
                    Book.COLUMN_NAME_BOOK_TITLE + " TEXT NOT NULL, " +
                    Book.COLUMN_NAME_BOOK_PUBLISHER + " TEXT, " +
                    Book.COLUMN_NAME_BOOK_PRICE + " TEXT);";
    private static final String BOOK_TABLE_DELETE =
            "DROP TABLE IF EXISTS " + Book.BOOK_TABLE_NAME;

    public BookOpenHelper(Context context)
    {
        //DB-name,ver 指定
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        // create table
        db.execSQL(BOOK_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // if update
        db.execSQL(BOOK_TABLE_DELETE);
        onCreate(db);
    }

    public void resetDb(SQLiteDatabase db)
    {
        db.execSQL(BOOK_TABLE_DELETE);
        onCreate(db);
    }
}
