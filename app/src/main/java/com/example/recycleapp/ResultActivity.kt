package com.example.recycleapp


import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity

/**/
class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        /*val array = arrayOf("カワチ", "サンユー", "ヨーカドー", "カスミ", "ヒタチエ")
        val listView = findViewById<ListView>(R.id.list_View)
        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array)
        listView.adapter = adapter*/
        val lv:ListView =findViewById(R.id.list_View)
        val data = mutableListOf(
            mapOf("main" to "カワチ" ,"sub" to "10:00~20:00"),
            mapOf("main" to "カスミ" ,"sub" to "9:00~24:00"),
            mapOf("main" to "ヒタチエ" ,"sub" to "10:00~20:00"),
            mapOf("main" to "サンユー" ,"sub" to "9:00~21:00"),
            mapOf("main" to "ヨーカドー" ,"sub" to "10:00~22:00")
        )


        val adapter =SimpleAdapter(
            this,
            data,
            android.R.layout.simple_list_item_2,
            arrayOf("main","sub"),//第2引数のmapのキー(配列)
            intArrayOf(android.R.id.text1,android.R.id.text2)//第3引数のレイアウトのビューのid(int配列)
        )

        //３）リストにセットして表示
        lv.adapter =adapter


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
