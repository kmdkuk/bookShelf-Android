package com.example.kouki.myfirstapplication

import android.content.Context
import android.database.Cursor
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
        private val DATABASE_VERSION = 3

        private val DATABASE_NAME = "main.db"

        private val BOOK_TABLE_CREATE = "CREATE TABLE " + Book.BOOK_TABLE_NAME + " (" +
                Book._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Book.COLUMN_NAME_BOOK_TITLE + " TEXT NOT NULL, " +
                Book.COLUMN_NAME_BOOK_PUBLISHER + " TEXT, " +
                Book.COLUMN_NAME_BOOK_PRICE + " TEXT);"
        private val BOOK_TABLE_DELETE = "DROP TABLE IF EXISTS " + Book.BOOK_TABLE_NAME
    }
}
