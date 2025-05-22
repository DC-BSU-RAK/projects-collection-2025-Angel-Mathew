package com.example.cocomix

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
           super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_splash )

            val imageView = findViewById<ImageView>(R.id.iconscreen)

            // Apply fade-in animation
            val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fadeing)
            imageView.startAnimation(fadeIn)

            // Delay 3 seconds and go to main activity
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, MainActivity ::class.java)
                startActivity(intent)
                finish()
            }, 3000)
        }
    }