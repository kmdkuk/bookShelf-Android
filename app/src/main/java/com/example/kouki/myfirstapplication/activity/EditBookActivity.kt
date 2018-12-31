package com.example.kouki.myfirstapplication.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.kouki.myfirstapplication.R
import com.example.kouki.myfirstapplication.database.BookOpenHelper
import com.example.kouki.myfirstapplication.model.BookModel
import kotlinx.android.synthetic.main.activity_edit_book.*

class EditBookActivity : AppCompatActivity() {

    private var mBookOpenHelper: BookOpenHelper? = null
    private var mTargetID: Int? = null
    private var mTargetColumn: String? = null

    companion object {
        const val EXTRA_TARGET_ID = "com.example.kouki.myfirstapplication.activity.edit_book.TARGET_ID"
        const val EXTRA_TARGET_COLUMN = "com.example.kouki.myfirstapplication.activity.edit_book.TARGET_COLUMN"
        const val EXTRA_TARGET_TEXT = "com.example.kouki.myfirstapplication.activity.edit_book.TARGET_TEXT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_book)

        val targetText = intent.getStringExtra(EXTRA_TARGET_TEXT)
        mTargetID = intent.getIntExtra(EXTRA_TARGET_ID, -1)
        mTargetColumn = intent.getStringExtra(EXTRA_TARGET_COLUMN)

        targetEditText!!.setText(targetText, TextView.BufferType.NORMAL)

        mBookOpenHelper = BookOpenHelper(this)

        updateButton.setOnClickListener {
            mBookOpenHelper!!.update(mTargetID!!, targetEditText.text.toString(), mTargetColumn!!)
            finish()
        }
    }
}
