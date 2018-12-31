package com.example.kouki.myfirstapplication.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.kouki.myfirstapplication.R
import com.example.kouki.myfirstapplication.model.BookModel
import kotlinx.android.synthetic.main.activity_book_description.*

class BookDescriptionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_description)

        val intent = intent
        // MainActivityからintentで受け取ったものを取り出す
        val selectedBook = intent.getSerializableExtra("Book") as BookModel
        titleTextView.text = selectedBook.title
        authorsTextView.text = selectedBook.authors
    }
}
