package com.example.weatherapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ListView
import android.widget.ArrayAdapter
import android.widget.Button

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val days = intent.getStringArrayExtra("days")
        val min = intent.getIntArrayExtra("min")
        val max = intent.getIntArrayExtra("max")
        val weather = intent.getStringArrayExtra("weather")

        val list = ArrayList<String>()

        // ✅ LOOP ONLY DOES THIS
        for (i in days!!.indices) {
            list.add("${days[i]}: Min ${min!![i]} Max ${max!![i]} ${weather!![i]}")
        }

        // ✅ EVERYTHING BELOW IS OUTSIDE LOOP
        val listView = findViewById<ListView>(R.id.listView)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            list
        )

        listView.adapter = adapter

        val btnBack = findViewById<Button>(R.id.btnBack)

        btnBack.setOnClickListener {
            finish()
        }
    }
}
