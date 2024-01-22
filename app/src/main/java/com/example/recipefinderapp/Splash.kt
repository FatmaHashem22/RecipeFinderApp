package com.example.recipefinderapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.example.recipefinderapp.ui.theme.MainActivity

class Splash : AppCompatActivity() {

    lateinit var startButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startButton = findViewById(R.id.get_started_btn)
        startButton.setAlpha(0f)
        startButton.setTranslationY(50F)
        startButton.animate().alpha(1f).translationY(-50F).setDuration(1500)

        startButton.setOnClickListener {
            val intent = Intent(this@Splash, MainActivity::class.java)
            startActivity(intent)
        }

    }
}
