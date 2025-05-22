package com.example.multiple_app_diariestodesire

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class namelogin_sharepreference : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        const val PREF_NAME = "UserPrefs"
        const val KEY_NAME = "user_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_namelogin_sharepreference)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nameEditText = findViewById(R.id.entername)

        //  SharedPreferences
        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        // to check if name exist in sharedPreference
        val savedName = sharedPreferences.getString(KEY_NAME, "")
        if (savedName != "") {
            nameEditText.setText(savedName)
        }
    }

    fun save(view: View) {
        val name = nameEditText.text.toString()

        // Save name
        val editor = sharedPreferences.edit()
        editor.putString(KEY_NAME, name)
        editor.apply()

        // to instruction screen
        navigateToInstructionScreen()
    }

    fun clear(view: View) {
        // Clear Text
        nameEditText.setText("")

        // Clear SharedPreferences
        val editor = sharedPreferences.edit()
        editor.remove(KEY_NAME)
        editor.apply()
    }

    fun skip(view: View) {
        // skip to instruction screen without saving name
        navigateToInstructionScreen()
    }

    private fun navigateToInstructionScreen() {
        val intent = Intent(this, Instruction_MainActivity::class.java)
        startActivity(intent)
    }
}