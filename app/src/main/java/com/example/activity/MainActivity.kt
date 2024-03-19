package com.example.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    private lateinit var editViewSurname: EditText
    private lateinit var editViewName: EditText
    private lateinit var editViewFatherName: EditText
    private lateinit var editViewAge: EditText
    private lateinit var editViewHobby: EditText
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        editViewSurname = findViewById(R.id.edit_view_surname)
        editViewName = findViewById(R.id.edit_view_name)
        editViewFatherName = findViewById(R.id.edit_view_father_name)
        editViewAge = findViewById(R.id.edit_view_age)
        editViewHobby = findViewById(R.id.edit_view_hobby)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        if (savedInstanceState == null) {
            editViewSurname.setText(sharedPreferences.getString("surname", ""))
            editViewName.setText(sharedPreferences.getString("name", ""))
            editViewFatherName.setText(sharedPreferences.getString("father_name", ""))
            editViewAge.setText(sharedPreferences.getString("age", ""))
            editViewHobby.setText(sharedPreferences.getString("hobby", ""))
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java)
            val editViewAge = findViewById<EditText>(R.id.edit_view_age)
            val ageText = editViewAge.text.toString()
            val age = ageText.toIntOrNull()
            intent.putExtra("age", age)
            startActivity(intent)
        }
    }

    override fun onPause() {
        super.onPause()
        val editor = sharedPreferences.edit()
        editor.putString("surname", editViewSurname.text.toString())
        editor.putString("name", editViewName.text.toString())
        editor.putString("father_name", editViewFatherName.text.toString())
        editor.putString("age", editViewAge.text.toString())
        editor.putString("hobby", editViewHobby.text.toString())
        editor.apply()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_save -> {
                saveFieldsToSharedPreferences()
                return true
            }
            R.id.action_change_background -> {
                changeBackground()
                return true
            }
            R.id.action_go_to_screen -> {
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun saveFieldsToSharedPreferences() {
        val editor = sharedPreferences.edit()
        editor.putString("surname", editViewSurname.text.toString())
        editor.putString("name", editViewName.text.toString())
        editor.putString("father_name", editViewFatherName.text.toString())
        editor.putString("age", editViewAge.text.toString())
        editor.putString("hobby", editViewHobby.text.toString())
        editor.apply()
    }

    private fun changeBackground() {
        val backgroundView = findViewById<View>(R.id.main)

        val currentBackgroundColor = sharedPreferences.getInt("background_color", Color.WHITE)

        val newBackgroundColor = if (currentBackgroundColor == Color.WHITE) {
            Color.YELLOW
        } else {
            Color.WHITE
        }

        backgroundView.setBackgroundColor(newBackgroundColor)

        val editor = sharedPreferences.edit()
        editor.putInt("background_color", newBackgroundColor)
        editor.apply()
    }

}