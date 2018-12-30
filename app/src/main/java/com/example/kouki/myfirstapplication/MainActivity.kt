package com.example.kouki.myfirstapplication

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CursorAdapter
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.AdapterView



class MainActivity : AppCompatActivity(){

    private var mBookOpenHelper: BookOpenHelper? = null
    private var mCursor: Cursor? = null
    private var mSimpleCursorAdapter: SimpleCursorAdapter? = null
    private var mListView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mListView = findViewById<View>(R.id.listview) as ListView

        mBookOpenHelper = BookOpenHelper(this)

        // データを取得
        mCursor = read(mBookOpenHelper!!)
        // UIにバインドするデータのカラム名
        val from = arrayOf(Book.COLUMN_NAME_BOOK_TITLE, Book.COLUMN_NAME_BOOK_PUBLISHER, Book.COLUMN_NAME_BOOK_PRICE)
        // 指定したカラムのデータを表示するViewのIDを指定します。
        val to = intArrayOf(R.id.Title, R.id.Publisher, R.id.Price)
        // 第2引数 リストに表示するレイアウトファイル
        // 第3引数 データベースから取得してきたCursorを指定します
        // 第4引数 UIにバインドするデータのカラム名を指定します
        // 第5引数 第4引数で指定したカラムのデータを表示するViewのIDを指定します。
        // また、第4引数の配列の並び順とViewIDの並び順は対応させる必要があります。
        // 第6引数 Adapterの振る舞いを指定します。
        mSimpleCursorAdapter = SimpleCursorAdapter(this, R.layout.book_list, mCursor, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER)

        mListView!!.adapter = mSimpleCursorAdapter

        mListView!!.setOnItemClickListener {parent, view, position, id ->
            // リスト項目をタップしたときの処理
            val intent = Intent(this.applicationContext, BookDescriptionActivity::class.java)
            // clickされたpositionのtextとphotoのID
            val c = read(mBookOpenHelper!!)
            c.moveToPosition(position)
            val selectedText = arrayOf(c.getString(1), c.getString(2), c.getString(3))
            c.close()
            // インテントにセット
            intent.putExtra("Text", selectedText)
            // Activity をスイッチする
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        refleshList()
    }

    fun deleteDB(view: View) {
        val db = mBookOpenHelper!!.writableDatabase
        mBookOpenHelper!!.resetDb(db)
        refleshList()
    }

    fun addBook(view: View) {
        //insert(mBookOpenHelper);
        // データを再読み込みしてListの表示を最新のものにします
        //mSimpleCursorAdapter.getCursor();
        val intent = Intent(this, AddBookActivity::class.java)
        //遷移先の画面を起動
        startActivity(intent)
    }

    private fun refleshList() {
        mSimpleCursorAdapter!!.swapCursor(read(mBookOpenHelper!!))
    }

    private fun read(bookOpenHelper: BookOpenHelper): Cursor {

        val db = bookOpenHelper.readableDatabase

        val projection = arrayOf(Book._ID, Book.COLUMN_NAME_BOOK_TITLE, Book.COLUMN_NAME_BOOK_PUBLISHER, Book.COLUMN_NAME_BOOK_PRICE)

        val selection = Book.COLUMN_NAME_BOOK_PRICE + " = ?"
        val selectionArgs = arrayOf("PRICE1")

        return db.query(Book.BOOK_TABLE_NAME, projection, selection, selectionArgs, null, null, null)
    }
}
