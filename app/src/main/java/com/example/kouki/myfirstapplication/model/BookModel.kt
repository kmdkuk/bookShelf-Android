package com.example.kouki.myfirstapplication.model

import java.io.Serializable

data class BookModel(
        val title: String,
        val author: String,
        val price: String
) : Serializable