package com.example.recycleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.Toast
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //テストです
        // CalendarViewに現在日時を設定します。
        val calendarView = findViewById<CalendarView>(R.id.calendarView)
        calendarView.date = System.currentTimeMillis()

        // CalendarViewで日にちが選択された時に呼び出されるリスナー
        val listener = DateChangeListener()
        calendarView.setOnDateChangeListener(listener)

        // ボタンクリックを検出するイベントリスナー(カン)
        val buttonCan:Button = findViewById(R.id.button_can)
        buttonCan.setOnClickListener {
            // ここに[カン]ボタン押下時の処理を記述
            Log.d("INFO","BUTTON CAN WAS PRESSED")
            val buttonText:TextView = findViewById(R.id.text_by_button)  // ボタンに連動するテキスト
            buttonText.text = getString(R.string.txt_by_bt_clicked)
            // カレンダー制御系

        }
    }

    // CalendarViewで日にちが選択された時に呼び出されるリスナークラス
    private inner class DateChangeListener : CalendarView.OnDateChangeListener {
        override fun onSelectedDayChange(calendarView: CalendarView, year: Int, month: Int, dayOfMonth: Int) {
            // monthは0起算のため+1します。
            val displayMonth = month + 1
            Toast.makeText(applicationContext, "$year/$displayMonth/$dayOfMonth/test", Toast.LENGTH_SHORT).show()
        }
    }
}
