
package com.example.recycleapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


/**/
class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val array = arrayOf("スーパーマルト　缶回収", "2", "3", "4", "5", "6", "7", "8", "9", "10")
        val listView = findViewById<ListView>(R.id.list_View)
        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array)
        listView.adapter = adapter

        val buttonback: Button = findViewById(R.id.button_back)
        buttonback.setOnClickListener {
            // ここにボタン押下時の処理を記述
            Log.d("INFO", "BUTTON DUMMY WAS PRESSED")

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}

/*　package com.example.recycleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
    }
}　*/
