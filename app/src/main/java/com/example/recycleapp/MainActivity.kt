package com.example.recycleapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup.LayoutParams;
import androidx.appcompat.app.AppCompatActivity
import java.util.*


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
        val buttonText:TextView = findViewById(R.id.text_by_button)  // ボタンに連動するテキスト
        // 選択した資源ゴミの回収用日をハイライトする線
        val highlightLine:View = findViewById(R.id.highlight_line)
        val mlp:MarginLayoutParams = highlightLine.layoutParams as MarginLayoutParams

        // ボタンクリックを検出するイベントリスナー(カン)
        val buttonCan:Button = findViewById(R.id.button_can)
        buttonCan.setOnClickListener {
            // ここに[カン]ボタン押下時の処理を記述
            Log.d("INFO","BUTTON CAN WAS PRESSED")
            buttonText.text = getString(R.string.txt_can)
            // カレンダー制御
            highlightLine.setVisibility(View.VISIBLE)
            mlp.setMargins(490,190,0,0)         //マージンを設定
            highlightLine.layoutParams = mlp
        }
        // ボタンクリックを検出するイベントリスナー(ビン)
        val buttonBin:Button = findViewById(R.id.button_bin)
        buttonBin.setOnClickListener {
            // ここに[カン]ボタン押下時の処理を記述
            Log.d("INFO","BUTTON BIN WAS PRESSED")
            buttonText.text = getString(R.string.txt_bin)
            // カレンダー制御
            highlightLine.setVisibility(View.VISIBLE)
            mlp.setMargins(765,190,0,0)         //マージンを設定
            highlightLine.layoutParams = mlp
        }
    }

    // CalendarViewで日にちが選択された時に呼び出されるリスナークラス
    private inner class DateChangeListener : CalendarView.OnDateChangeListener {
        override fun onSelectedDayChange(calendarView: CalendarView, year: Int, month: Int, dayOfMonth: Int) {
            // monthは0起算のため+1します。
            val displayMonth = month + 1
            //Toast.makeText(applicationContext, "$year/$displayMonth/$dayOfMonth/test", Toast.LENGTH_SHORT).show()

            //カレンダーインスタンスにCalendarViewで取得した日付を渡して曜日を取得,日にち情報を表示
            val today:Calendar = Calendar.getInstance()
            today.set(year, displayMonth, dayOfMonth)
            val dayofweek = today.get(Calendar.DAY_OF_WEEK) // 曜日ソース(int)を取得、なぜか1=木曜日~7=水曜日, なぜか11月にするとバグる?
            val week = arrayOf("木", "金", "土", "日", "月", "火", "水")
            val dayinfoText:TextView = findViewById(R.id.day_info)  // 日付変更に連動するテキストを設定、年日時曜日を代入して表示
            dayinfoText.text = getString(R.string.selected_day_info, year, displayMonth, dayOfMonth, week[dayofweek - 1])

            // その日の回収対象資源ゴミをテキスト表示
            val target = arrayOf("dummy", "ビン", "dummy", "dummy", "dummy", "dummy", "カン") // 超簡易的な曜日に対する対象の資源ゴミ
            val targetText:TextView = findViewById(R.id.target_info)  // 日付変更に連動するテキストを設定、年日時曜日を代入して表示
            if(dayofweek == 7 || dayofweek == 2){ //水曜ならカン, 金曜はビンの回収日
                targetText.text = getString(R.string.target_info, target[dayofweek - 1])
            }
            else{ //それ以外の曜日は資源ゴミの回収無し
                targetText.text = getString(R.string.no_target_info)
            }
        }
    }
}
