package com.example.recycleapp


import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


/**/
class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val array = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
        val listView = findViewById<ListView>(R.id.list_View)
        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array)
        listView.adapter = adapter

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
