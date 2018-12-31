package com.example.kouki.myfirstapplication.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.kouki.myfirstapplication.model.BookModel

class BookOpenHelper(context: Context)//DB-name,ver 指定
    : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val TAG = BookOpenHelper::class.java!!.getSimpleName();

    private val projectionAll = arrayOf(
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
        val db = readableDatabase
        var selectedBook:BookModel? = null
        var c = db.query(BookContract.BOOK_TABLE_NAME, projectionAll, "${BookContract._ID} = ?", arrayOf(index.toString()), null, null,null,null)
        try{
            if (c.moveToNext()){
                Log.d(TAG, c.getString(0))
                selectedBook = BookModel(
                        c.getString(0).toInt(),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5),
                        c.getString(6),
                        c.getString(7),
                        c.getString(8),
                        c.getString(9))
            }
        }finally {
            c.close()
        }
        return selectedBook!!
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

    fun update(id: Int, value: String, column: String)
    {
        val db = writableDatabase

        val values = ContentValues()
        values.put(column, value)

        db.update(BookContract.BOOK_TABLE_NAME, values, "${BookContract._ID} = ?", arrayOf(id.toString()))
    }

    fun resetDb() {
        val db = writableDatabase
        db.execSQL(BOOK_TABLE_DELETE)
        onCreate(db)
    }

    fun readDb(): Cursor {
        val db = readableDatabase

        return db.query(BookContract.BOOK_TABLE_NAME, projectionAll, null, null, null, null, null)
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
