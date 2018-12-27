package com.example.kouki.myfirstapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    private BookOpenHelper mBookOpenHelper;
    private Cursor mCursor;
    private SimpleCursorAdapter mSimpleCursorAdapter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listview);

        mBookOpenHelper = new BookOpenHelper(this);

        // データを取得
        mCursor = read(mBookOpenHelper);
        // UIにバインドするデータのカラム名
        String[] from = {
                Book.COLUMN_NAME_BOOK_TITLE, Book.COLUMN_NAME_BOOK_PUBLISHER,Book.COLUMN_NAME_BOOK_PRICE
        };
        // 指定したカラムのデータを表示するViewのIDを指定します。
        int[] to = {
                R.id.Title, R.id.Publisher, R.id.Price

        };
        // 第2引数 リストに表示するレイアウトファイル
        // 第3引数 データベースから取得してきたCursorを指定します
        // 第4引数 UIにバインドするデータのカラム名を指定します
        // 第5引数 第4引数で指定したカラムのデータを表示するViewのIDを指定します。
        // また、第4引数の配列の並び順とViewIDの並び順は対応させる必要があります。
        // 第6引数 Adapterの振る舞いを指定します。
        mSimpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.book_list, mCursor, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        mListView.setAdapter(mSimpleCursorAdapter);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        refleshList();
    }

    public void deleteDB(View view)
    {
        SQLiteDatabase db = mBookOpenHelper.getWritableDatabase();
        mBookOpenHelper.resetDb(db);
        refleshList();
    }

    public void addBook(View view) {
        //insert(mBookOpenHelper);
        // データを再読み込みしてListの表示を最新のものにします
        //mSimpleCursorAdapter.getCursor();
        Intent intent = new Intent(this, AddBookActivity.class);
        //遷移先の画面を起動
        startActivity(intent);
    }

    private void refleshList()
    {
        mSimpleCursorAdapter.swapCursor(read(mBookOpenHelper));
    }

    private Cursor read(BookOpenHelper bookOpenHelper) {

        SQLiteDatabase db = bookOpenHelper.getReadableDatabase();

        String[] projection = {
                Book._ID,
                Book.COLUMN_NAME_BOOK_TITLE,
                Book.COLUMN_NAME_BOOK_PUBLISHER,
                Book.COLUMN_NAME_BOOK_PRICE
        };

        String selection = Book.COLUMN_NAME_BOOK_PRICE + " = ?";
        String[] selectionArgs = {
                "PRICE1"
        };

        Cursor cursor = db.query(Book.BOOK_TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        return cursor;
    }
}
