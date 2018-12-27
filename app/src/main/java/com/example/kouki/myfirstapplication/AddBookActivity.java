package com.example.kouki.myfirstapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddBookActivity extends AppCompatActivity {

    private BookOpenHelper mBookOpenHelper;
    private Cursor mCursor;
    EditText Title;
    EditText Author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        mBookOpenHelper = new BookOpenHelper(this);

        Title = findViewById(R.id.title_text);
        Author = findViewById(R.id.author_text);
        
    }

    public void registBook(View view)
    {
        insert(mBookOpenHelper, Title.getText().toString(), Author.getText().toString());
        finish();
    }
    private void insert(BookOpenHelper bookOpenHelper, String title, String author) {

        SQLiteDatabase db = bookOpenHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Book.COLUMN_NAME_BOOK_TITLE, title.toString());
        values.put(Book.COLUMN_NAME_BOOK_PUBLISHER, author.toString());
        values.put(Book.COLUMN_NAME_BOOK_PRICE, "PRICE1");

        db.insert(Book.BOOK_TABLE_NAME, null, values);
    }
}
