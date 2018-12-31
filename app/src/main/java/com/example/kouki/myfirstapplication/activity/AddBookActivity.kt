package com.example.kouki.myfirstapplication.activity

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.kouki.myfirstapplication.database.BookContract
import com.example.kouki.myfirstapplication.database.BookOpenHelper
import com.example.kouki.myfirstapplication.R
import kotlinx.android.synthetic.main.activity_add_book.*

class AddBookActivity : AppCompatActivity() {

    private var mBookOpenHelper: BookOpenHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)

        mBookOpenHelper = BookOpenHelper(this)

        registerButton.setOnClickListener {
            mBookOpenHelper!!.insert(titleEditText.text.toString(), authorsEditText.text.toString())
            finish()
        }

    }
}
