package com.example.multiple_app_diariestodesire

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupWindow
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Diaries : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_diaries)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val label2 : ImageButton = findViewById(R.id.label2)
        label2.setOnClickListener{
            val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView = inflater.inflate(R.layout.activity_traveljournal,null)
            val traveljournal = PopupWindow(popupView, 990,1200,true)
            traveljournal.showAtLocation(popupView, Gravity.CENTER,0,0)

            val closeButton : Button = popupView.findViewById(R.id.closebtn2)
            closeButton.setOnClickListener{
                traveljournal.dismiss()
            }
        }
        val label1 : ImageButton = findViewById(R.id.label1)
        label1.setOnClickListener{
            val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView = inflater.inflate(R.layout.activity_creativejournal,null)
            val creativejournal = PopupWindow(popupView, 990,1200,true)
            creativejournal.showAtLocation(popupView, Gravity.CENTER,0,0)

            val closeButton : Button = popupView.findViewById(R.id.closebtn1)
            closeButton.setOnClickListener{
                creativejournal.dismiss()
            }
        }
        val label3 : ImageButton = findViewById(R.id.label3)
        label3.setOnClickListener{
            val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView = inflater.inflate(R.layout.activity_artjournal,null)
            val artjournal = PopupWindow(popupView, 990,1200,true)
            artjournal.showAtLocation(popupView, Gravity.CENTER,0,0)

            val closeButton : Button = popupView.findViewById(R.id.closebtn3)
            closeButton.setOnClickListener{
                artjournal.dismiss()
            }
        }
        val label4 : ImageButton = findViewById(R.id.label4)
        label4.setOnClickListener{
            val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView = inflater.inflate(R.layout.activity_naturejournal,null)
            val naturejournal= PopupWindow(popupView, 1000,1500,true)
            naturejournal.showAtLocation(popupView, Gravity.CENTER,0,0)

            val closeButton : Button = popupView.findViewById(R.id.closebtn4)
            closeButton.setOnClickListener{
                naturejournal.dismiss()
            }
        }
        val label5 : ImageButton = findViewById(R.id.label5)
        label5.setOnClickListener{
            val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView = inflater.inflate(R.layout.activity_gratitudejournal,null)
            val gratitudejournal= PopupWindow(popupView, 990,1200,true)
            gratitudejournal.showAtLocation(popupView, Gravity.CENTER,0,0)

            val closeButton : Button = popupView.findViewById(R.id.closebtn5)
            closeButton.setOnClickListener{
                gratitudejournal.dismiss()
            }
        }
        val label6 : ImageButton = findViewById(R.id.label6)
        label6.setOnClickListener{
            val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView = inflater.inflate(R.layout.activity_healthjournal,null)
            val healthjournal= PopupWindow(popupView, 990,1200,true)
            healthjournal.showAtLocation(popupView, Gravity.CENTER,0,0)

            val closeButton : Button = popupView.findViewById(R.id.closebtn6)
            closeButton.setOnClickListener{
                healthjournal.dismiss()
            }
        }
    }
}