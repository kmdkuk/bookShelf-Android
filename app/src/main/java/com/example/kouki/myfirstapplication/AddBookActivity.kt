package com.example.kouki.myfirstapplication

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class AddBookActivity : AppCompatActivity() {

    private var mBookOpenHelper: BookOpenHelper? = null
    private val mCursor: Cursor? = null
    internal var Title: EditText? = null
    internal var Author: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)

        mBookOpenHelper = BookOpenHelper(this)

        Title = findViewById(R.id.title_text)
        Author = findViewById(R.id.author_text)

    }

    fun registBook(view: View) {
        insert(mBookOpenHelper!!, Title?.text.toString(), Author?.text.toString())
        finish()
    }

    private fun insert(bookOpenHelper: BookOpenHelper, title: String, author: String) {

        val db = bookOpenHelper.writableDatabase

        val values = ContentValues()
        values.put(Book.COLUMN_NAME_BOOK_TITLE, title)
        values.put(Book.COLUMN_NAME_BOOK_PUBLISHER, author)
        values.put(Book.COLUMN_NAME_BOOK_PRICE, "PRICE1")

        db.insert(Book.BOOK_TABLE_NAME, null, values)
    }
}
