package com.example.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        val age = intent.getIntExtra("age", 0)

        val infoText = findViewById<TextView>(R.id.info_text)

        if (age != 0) {
            val displayText = when (age) {
                in 0..13 -> "Вам $age лет. Вы - ребенок."
                in 14..17 -> "Вам $age лет. Вы - подросток."
                in 18..64 -> "Вам $age лет. Вы - взрослый."
                else -> "Вам $age лет. Вы - пожилой человек."
            }
            infoText.text = displayText
        } else {
            infoText.text = "Что-то пошло не так"
        }
    }
}
