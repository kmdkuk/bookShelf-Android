package com.example.kouki.myfirstapplication.activity

import android.content.Intent
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CursorAdapter
import android.widget.SimpleCursorAdapter
import com.example.kouki.myfirstapplication.database.BookContract
import com.example.kouki.myfirstapplication.database.BookOpenHelper
import com.example.kouki.myfirstapplication.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var mBookOpenHelper: BookOpenHelper? = null
    private var mCursor: Cursor? = null
    private var mSimpleCursorAdapter: SimpleCursorAdapter? = null

    private val TAG = MainActivity::class.java!!.getSimpleName();


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBookOpenHelper = BookOpenHelper(this)

        // データを取得
        mCursor = mBookOpenHelper!!.readDb()
        // UIにバインドするデータのカラム名
        val from = arrayOf(BookContract.COLUMN_NAME_BOOK_TITLE, BookContract.COLUMN_NAME_BOOK_AUTHORS)
        // 指定したカラムのデータを表示するViewのIDを指定します。
        val to = intArrayOf(R.id.titleTextView, R.id.authorsTextView)
        // 第2引数 リストに表示するレイアウトファイル
        // 第3引数 データベースから取得してきたCursorを指定します
        // 第4引数 UIにバインドするデータのカラム名を指定します
        // 第5引数 第4引数で指定したカラムのデータを表示するViewのIDを指定します。
        // また、第4引数の配列の並び順とViewIDの並び順は対応させる必要があります。
        // 第6引数 Adapterの振る舞いを指定します。
        mSimpleCursorAdapter = SimpleCursorAdapter(this, R.layout.book_list, mCursor, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER)

        listView!!.adapter = mSimpleCursorAdapter

        listView!!.setOnItemClickListener { _, _, position, _ ->
            Log.i(TAG, "$position")
            // リスト項目をタップしたときの処理
            val intent = Intent(this.applicationContext, BookDescriptionActivity::class.java)
            // clickされたpositionのtextとphotoのID
            val selectedBook = mBookOpenHelper!!.getBook(position+1)
            // インテントにセット
            intent.putExtra(BookDescriptionActivity.EXTRA_SELECTED_ID, selectedBook.id)
            // Activity をスイッチする
            startActivity(intent)
        }

        addButton.setOnClickListener {
            //insert(mBookOpenHelper);
            // データを再読み込みしてListの表示を最新のものにします
            //mSimpleCursorAdapter.getCursor();
            val intent = Intent(this, AddBookActivity::class.java)
            //遷移先の画面を起動
            startActivity(intent)
        }

        resetButton.setOnClickListener {
            mBookOpenHelper!!.resetDb()
            refreshList()
        }
    }

    override fun onStart() {
        super.onStart()
        refreshList()
    }

    private fun refreshList() {
        mSimpleCursorAdapter!!.swapCursor(mBookOpenHelper!!.readDb())
    }
}
