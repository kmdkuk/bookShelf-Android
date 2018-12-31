package com.example.kouki.myfirstapplication.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.kouki.myfirstapplication.R
import com.example.kouki.myfirstapplication.model.BookModel
import kotlinx.android.synthetic.main.activity_edit_book.*

class EditBookActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_book)

        val targetText = intent.getStringExtra("Text")

        targetEditText!!.setText(targetText, TextView.BufferType.NORMAL)
    }
}
