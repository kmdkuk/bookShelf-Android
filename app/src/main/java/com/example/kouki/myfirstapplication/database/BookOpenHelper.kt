package com.example.kouki.myfirstapplication.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BookOpenHelper(context: Context)//DB-name,ver 指定
    : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        // create table
        db.execSQL(BOOK_TABLE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // if update
        db.execSQL(BOOK_TABLE_DELETE)
        onCreate(db)
    }

    fun resetDb(db: SQLiteDatabase) {
        db.execSQL(BOOK_TABLE_DELETE)
        onCreate(db)
    }

    companion object {

        // DB version
        private const val DATABASE_VERSION = 3

        private const val DATABASE_NAME = "main.db"

        private const val BOOK_TABLE_CREATE = "CREATE TABLE " + BookContract.BOOK_TABLE_NAME + " (" +
                BookContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                BookContract.COLUMN_NAME_BOOK_TITLE + " TEXT NOT NULL, " +
                BookContract.COLUMN_NAME_BOOK_PUBLISHER + " TEXT, " +
                BookContract.COLUMN_NAME_BOOK_PRICE + " TEXT);"
        private const val BOOK_TABLE_DELETE = "DROP TABLE IF EXISTS " + BookContract.BOOK_TABLE_NAME
    }
}
