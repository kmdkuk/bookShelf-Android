package com.example.kouki.myfirstapplication.activity

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.kouki.myfirstapplication.database.BookContract
import com.example.kouki.myfirstapplication.database.BookOpenHelper
import com.example.kouki.myfirstapplication.R

class AddBookActivity : AppCompatActivity() {

    private var mBookOpenHelper: BookOpenHelper? = null
    private var mTitle: EditText? = null
    private var mAuthor: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)

        mBookOpenHelper = BookOpenHelper(this)

        mTitle = findViewById(R.id.title_text)
        mAuthor = findViewById(R.id.author_text)

    }

    fun registBook() {
        insert(mBookOpenHelper!!, mTitle?.text.toString(), mAuthor?.text.toString())
        finish()
    }

    private fun insert(bookOpenHelper: BookOpenHelper, title: String, author: String) {

        val db = bookOpenHelper.writableDatabase

        val values = ContentValues()
        values.put(BookContract.COLUMN_NAME_BOOK_TITLE, title)
        values.put(BookContract.COLUMN_NAME_BOOK_PUBLISHER, author)
        values.put(BookContract.COLUMN_NAME_BOOK_PRICE, "PRICE1")

        db.insert(BookContract.BOOK_TABLE_NAME, null, values)
    }
}
