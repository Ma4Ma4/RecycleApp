package com.example.recycleapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat //怪しい,コイツ周りのためにbuildをandroidXへ変更
import androidx.core.app.NotificationManagerCompat
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.view.ViewGroup.MarginLayoutParams;
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat.EXTRA_NOTIFICATION_ID
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //テストです
        /*----- 各種ビューの作成・登録-----*/
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
            // ここに[ビン]ボタン押下時の処理を記述
            Log.d("INFO","BUTTON BIN WAS PRESSED")
            buttonText.text = getString(R.string.txt_bin)
            // カレンダー制御
            highlightLine.setVisibility(View.VISIBLE)
            mlp.setMargins(765,190,0,0)         //マージンを設定
            highlightLine.layoutParams = mlp
        }

        /*----- 通知周り-----*/
        // 1-1. チャネルIDを作成
        val channelId = "NOTIFICATION_CHANNEL_ID"

        // チャネル
        //2-1. NotificationChannelオブジェクトの作成
        val name = getString(R.string.channel_name)
        val descriptionText = getString(R.string.channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            // 2-2. チャネルをシステムに登録
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        // 3-1. アプリ内アクティビティへのインテントを設定
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(EXTRA_NOTIFICATION_ID, 0)
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        // EX. 通知に2択ボタンを設けてアクティビティ遷移の土台にする
        // ここを遷移先のアクティビティ(出し忘れたので検索画面へ)を指定するインテントにすることで実装できそう
        val intentForgot = Intent(this, DummyActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(EXTRA_NOTIFICATION_ID, 0)
        }
        val pendingIntentForgot = PendingIntent.getActivity(this, 0, intentForgot, 0)

        val builder = NotificationCompat.Builder(this, channelId)
            // 1-2. 表示内容の設定
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("テスト通知")
            .setContentText("水曜日はカンの回収日です。ゴミ出しは完了しましたか?")
            .setStyle(NotificationCompat.BigTextStyle() //テキスト領域の拡大
                .bigText("水曜日はカンの回収日です。ゴミ出しは完了しましたか?"))
            // 1-3. 優先度の設定
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            // 3-2. インテントをbuilderに設定
            .setContentIntent(pendingIntent) //通知をタップしたらインテントを読み込む
            .addAction( //ゴミ出しできた選択肢
                com.google.android.material.R.drawable.ic_clear_black_24, getString(R.string.submit), //アイコンは適当
            pendingIntent)
            .addAction( //ゴミ出し忘れた選択肢
                com.google.android.material.R.drawable.ic_clear_black_24, getString(R.string.forgot), //アイコンは適当
                pendingIntentForgot)
            .setAutoCancel(true) //通知をタップしたら通知が消去される

        // ボタンクリックを検出するイベントリスナー(通知トリガー用)
        val buttonNotify:Button = findViewById(R.id.button_notify)
        buttonNotify.setOnClickListener {
            // ここに[通知を発令]ボタン押下時の処理を記述
            Log.d("INFO","BUTTON NOTIFY WAS PRESSED")
            with(NotificationManagerCompat.from(this)) {
                // notificationIDとbuilder.build()を渡します
                notify(12345, builder.build())
            }
        }

        // ボタンクリックを検出するイベントリスナー(検索結果画面ジャンプ用)
        val buttonResult:Button = findViewById(R.id.button_result)
        buttonResult.setOnClickListener {
            // ここに[通知を発令]ボタン押下時の処理を記述
            Log.d("INFO", "BUTTON RESULT WAS PRESSED")
            val intentResult = Intent(this, ResultActivity::class.java)
            startActivity(intentResult)
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
