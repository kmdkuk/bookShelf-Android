package com.example.kouki.myfirstapplication

import android.provider.BaseColumns

class Book : BaseColumns {

    private val mTitle: String? = null
    private val mPublisher: String? = null
    private val mPrice: Int = 0

    companion object {
        val BOOK_TABLE_NAME = "book"

        val _ID = "_id"
        val COLUMN_NAME_BOOK_TITLE = "title"
        val COLUMN_NAME_BOOK_PUBLISHER = "publisher"
        val COLUMN_NAME_BOOK_PRICE = "price"
    }
}
