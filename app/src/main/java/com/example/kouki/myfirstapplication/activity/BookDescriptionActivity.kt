package com.example.kouki.myfirstapplication.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.kouki.myfirstapplication.R
import com.example.kouki.myfirstapplication.database.BookContract
import com.example.kouki.myfirstapplication.model.BookModel
import kotlinx.android.synthetic.main.activity_book_description.*

class BookDescriptionActivity : AppCompatActivity() {

    private var mSelectedBook: BookModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_description)

        // MainActivityからintentで受け取ったものを取り出す
        mSelectedBook = intent.getSerializableExtra("Book") as BookModel
        titleTextView.text = mSelectedBook!!.title
        authorsTextView.text = mSelectedBook!!.authors
        descriptionTextView.text = mSelectedBook!!.description
        publishedDateTextView.text = mSelectedBook!!.publishDate
        categoriesTextView.text = mSelectedBook!!.categories
        boughtDateTextView.text = mSelectedBook!!.boughtDate
        readDateTextView.text = mSelectedBook!!.readDate
        progressTextView.text = mSelectedBook!!.progress
        notesTextView.text = mSelectedBook!!.notes

        val bookColumns = arrayListOf(
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
        listOf(titleTextView, authorsTextView, descriptionTextView, publishedDateTextView,
                categoriesTextView, boughtDateTextView, readDateTextView, progressTextView, notesTextView)
                .forEachIndexed { index, textView ->
                    textView.setOnClickListener {
                        // リスト項目をタップしたときの処理
                        val intent = Intent(this.applicationContext, EditBookActivity::class.java)
                        // インテントにセット
                        intent.putExtra("SelectedColumns", bookColumns[index])
                        intent.putExtra("Text", textView.text.toString())
                        // Activity をスイッチする
                        startActivity(intent)
                    }
                }

    }
}
