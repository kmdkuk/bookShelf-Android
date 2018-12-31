package com.example.kouki.myfirstapplication.model

import java.io.Serializable

data class BookModel(
        val id: Int,
        val title: String,
        val authors: String,
        val description: String,
        val publishDate: String,
        val categories: String,
        val boughtDate: String,
        val readDate: String,
        val progress: String,
        val notes: String
) : Serializable