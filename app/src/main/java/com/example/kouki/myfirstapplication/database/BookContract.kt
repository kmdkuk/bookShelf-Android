package com.example.kouki.myfirstapplication.database

import android.provider.BaseColumns

class BookContract : BaseColumns {
    companion object {
        const val BOOK_TABLE_NAME = "book"

        const val _ID = "_id"
        const val COLUMN_NAME_BOOK_TITLE = "title"
        const val COLUMN_NAME_BOOK_PUBLISHER = "publisher"
        const val COLUMN_NAME_BOOK_PRICE = "price"
    }
}
