package com.example.kouki.myfirstapplication;

import android.provider.BaseColumns;

public class Book implements BaseColumns {
    public static final String BOOK_TABLE_NAME = "book";

    public static final String COLUMN_NAME_BOOK_TITLE = "title";
    public static final String COLUMN_NAME_BOOK_PUBLISHER = "publisher";
    public static final String COLUMN_NAME_BOOK_PRICE = "price";

    private String mTitle;
    private String mPublisher;
    private int mPrice;
}
