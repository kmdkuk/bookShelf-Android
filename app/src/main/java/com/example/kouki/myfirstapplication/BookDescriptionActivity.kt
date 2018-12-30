package com.example.kouki.myfirstapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class BookDescriptionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_description)

        val intent = intent
        // MainActivityからintentで受け取ったものを取り出す
        val selectedText = intent.getStringArrayExtra("Text")

        val titleText = findViewById<TextView>(R.id.descTitle)
        val authorText = findViewById<TextView>(R.id.descAuthor)
        val priceText = findViewById<TextView>(R.id.deskPrice)
        titleText.text = selectedText[0]
        authorText.text = selectedText[1]
        priceText.text = selectedText[2]
    }
}
