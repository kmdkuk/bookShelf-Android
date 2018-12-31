package com.example.kouki.myfirstapplication.database

import android.provider.BaseColumns

class BookContract : BaseColumns {
    companion object {
        const val BOOK_TABLE_NAME = "book"

        const val _ID = "_id"
        const val COLUMN_NAME_BOOK_TITLE = "title"
        const val COLUMN_NAME_BOOK_AUTHORS = "author"
        const val COLUMN_NAME_BOOK_DESCRIPTION = "description"
        const val COLUMN_NAME_BOOK_PUBLISHED_DATE = "published_date"
        const val COLUMN_NAME_BOOK_CATEGORIES = "categories"
        const val COLUMN_NAME_BOOK_BOUGHT_DATE = "bought_date"
        const val COLUMN_NAME_BOOK_READ_DATE = "read_date"
        const val COLUMN_NAME_BOOK_PROGRESS = "progress"
        const val COLUMN_NAME_BOOK_NOTES = "notes"
    }
}
