package com.example.recycleapp

import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.app.NotificationCompat

class DummyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dummy)

        // ボタンクリックを検出するイベントリスナー
        val buttondummy: Button = findViewById(R.id.button_dummy)
        buttondummy.setOnClickListener {
            // ここにボタン押下時の処理を記述
            Log.d("INFO", "BUTTON DUMMY WAS PRESSED")

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}