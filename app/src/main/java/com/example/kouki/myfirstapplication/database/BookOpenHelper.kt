package com.example.kouki.myfirstapplication.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.kouki.myfirstapplication.model.BookModel

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

    fun getBook(index: Int): BookModel {
        var c = readDb()
        c.moveToPosition(index)
        val selectedBook = BookModel(c.getString(1), c.getString(2), c.getString(3))
        c.close()

        return selectedBook
    }

    fun insert(title: String, author: String) {
        val db = writableDatabase

        val values = ContentValues()
        values.put(BookContract.COLUMN_NAME_BOOK_TITLE, title)
        values.put(BookContract.COLUMN_NAME_BOOK_AUTHORS, author)
        values.put(BookContract.COLUMN_NAME_BOOK_DESCRIPTION, "description")
        values.put(BookContract.COLUMN_NAME_BOOK_PUBLISHED_DATE, "yyyy/mm/dd")
        values.put(BookContract.COLUMN_NAME_BOOK_CATEGORIES, "category")
        values.put(BookContract.COLUMN_NAME_BOOK_BOUGHT_DATE, "yyyy/mm/dd")
        values.put(BookContract.COLUMN_NAME_BOOK_READ_DATE, "not complete")
        values.put(BookContract.COLUMN_NAME_BOOK_PROGRESS, "0page")
        values.put(BookContract.COLUMN_NAME_BOOK_NOTES, "")

        db.insert(BookContract.BOOK_TABLE_NAME, null, values)
    }

    fun resetDb() {
        val db = writableDatabase
        db.execSQL(BOOK_TABLE_DELETE)
        onCreate(db)
    }

    fun readDb(): Cursor {
        val db = readableDatabase

        val projection = arrayOf(
                BookContract._ID,
                BookContract.COLUMN_NAME_BOOK_TITLE,
                BookContract.COLUMN_NAME_BOOK_AUTHORS,
                BookContract.COLUMN_NAME_BOOK_DESCRIPTION,
                BookContract.COLUMN_NAME_BOOK_PUBLISHED_DATE,
                BookContract.COLUMN_NAME_BOOK_CATEGORIES,
                BookContract.COLUMN_NAME_BOOK_BOUGHT_DATE,
                BookContract.COLUMN_NAME_BOOK_READ_DATE,
                BookContract.COLUMN_NAME_BOOK_PROGRESS,
                BookContract.COLUMN_NAME_BOOK_NOTES
        )

        return db.query(BookContract.BOOK_TABLE_NAME, projection, null, null, null, null, null)
    }

    companion object {

        // DB version
        private const val DATABASE_VERSION = 6

        private const val DATABASE_NAME = "main.db"

        private const val BOOK_TABLE_CREATE = "CREATE TABLE " + BookContract.BOOK_TABLE_NAME + " (" +
                BookContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                BookContract.COLUMN_NAME_BOOK_TITLE + " TEXT NOT NULL, " +
                BookContract.COLUMN_NAME_BOOK_AUTHORS + " TEXT, " +
                BookContract.COLUMN_NAME_BOOK_DESCRIPTION + " TEXT, " +
                BookContract.COLUMN_NAME_BOOK_PUBLISHED_DATE + " TEXT, " +
                BookContract.COLUMN_NAME_BOOK_CATEGORIES + " TEXT, " +
                BookContract.COLUMN_NAME_BOOK_BOUGHT_DATE + " TEXT, " +
                BookContract.COLUMN_NAME_BOOK_READ_DATE + " TEXT, " +
                BookContract.COLUMN_NAME_BOOK_PROGRESS + " TEXT, " +
                BookContract.COLUMN_NAME_BOOK_NOTES + " TEXT);"
        private const val BOOK_TABLE_DELETE = "DROP TABLE IF EXISTS " + BookContract.BOOK_TABLE_NAME
    }
}
