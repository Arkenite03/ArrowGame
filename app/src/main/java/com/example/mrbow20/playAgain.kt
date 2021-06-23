package com.example.mrbow20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast

class playAgain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_again)

        fullScreen()

        val flag : String = intent.getStringExtra("flag").toString()
        var resu : ImageView = findViewById(R.id.result) as ImageView

        if(flag == "1")
        {
            resu.setImageResource(R.drawable.win)
        }

        else
        {
            resu.setImageResource(R.drawable.dead)
        }

    }

    fun fullScreen()
    {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)

    }

    fun playAgain(view: View)
    {
        val newintent = Intent(this, stage_1::class.java)
        finish()
        startActivity(newintent)

        Toast.makeText(this, "Long Press Shoot for Spectral Arrow!", Toast.LENGTH_LONG).show()
    }
}