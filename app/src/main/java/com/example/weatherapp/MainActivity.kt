package com.example.weatherapp

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent


class MainActivity : AppCompatActivity() {
    val days = arrayOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

    val minTemp = IntArray(7)
    val maxTemp = IntArray(7)
    val weather = Array(7) { "" }

    var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etMin = findViewById<EditText>(R.id.etMin)
        val etMax = findViewById<EditText>(R.id.etMax)
        val etCondition = findViewById<EditText>(R.id.etCondition)

        val tvAverage = findViewById<TextView>(R.id.tvAverage)

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnClear = findViewById<Button>(R.id.btnClear)
        val btnView = findViewById<Button>(R.id.btnView)
        btnView.setOnClickListener {
            btnClear.setOnClickListener {

                // Reset index
                index = 0

                // Clear input fields
                etMin.text.clear()
                etMax.text.clear()
                etCondition.text.clear()

                // Reset output
                tvAverage.text = "Cleared"

                // Reset arrays (optional but good practice)
                for (i in minTemp.indices) {
                    minTemp[i] = 0
                    maxTemp[i] = 0
                    weather[i] = ""
                }
            }

            val intent = Intent(this, DetailActivity::class.java)

            intent.putExtra("days", days)
            intent.putExtra("min", minTemp)
            intent.putExtra("max", maxTemp)
            intent.putExtra("weather", weather)

            startActivity(intent)
        }
        btnAdd.setOnClickListener {

            try {
                val min = etMin.text.toString().toInt()
                val max = etMax.text.toString().toInt()
                val condition = etCondition.text.toString()

                minTemp[index] = min
                maxTemp[index] = max
                weather[index] = condition

                index++

                var total = 0

                for (i in 0 until index) {
                    total += (minTemp[i] + maxTemp[i]) / 2
                }

                val avg = total / index
                tvAverage.text = "Average: $avg"

            } catch (e: Exception) {
                etMin.error = "Enter valid data"

                btnClear.setOnClickListener {
                    index = 0
                    tvAverage.text = "Cleared"
                }

            }
        }
    }
}
