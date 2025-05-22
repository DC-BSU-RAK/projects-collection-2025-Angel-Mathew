package com.example.multiple_app_diariestodesire

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Instruction_MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.instructionscreen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get the displayusername TextView
        val displayUsernameTextView = findViewById<TextView>(R.id.displayusername)

        // Retrieve the name from SharedPreferences
        val sharedPreferences = getSharedPreferences(namelogin_sharepreference.PREF_NAME, Context.MODE_PRIVATE)
        val username = sharedPreferences.getString(namelogin_sharepreference.KEY_NAME, "")

        // Set the text with the username if present, otherwise hide the TextView
        if (username != null && username.isNotEmpty()) {
            displayUsernameTextView.text = "Welcome, $username!"
            displayUsernameTextView.visibility = View.VISIBLE
        } else {
            displayUsernameTextView.visibility = View.GONE
        }
    }

    fun next(view: View) {
        // Create an Intent to navigate to the diaries +activity
        val intent = Intent(this, Diaries::class.java)
        startActivity(intent)
    }
}