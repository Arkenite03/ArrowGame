package com.example.mrbow20

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout



class stage_1 : AppCompatActivity() {

    var chPower = 5
    var villPower = 5
    var globalFlag = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stage_1)


        val character : ImageView = findViewById(R.id.character) as ImageView
        val blast : ImageView = findViewById(R.id.blast) as ImageView
        val rocket : ImageView = findViewById(R.id.rocket) as ImageView
        val bullet : ImageView = findViewById(R.id.bullet) as ImageView
        val shoot : ImageButton = findViewById(R.id.shoot) as ImageButton
        val screen : ConstraintLayout = findViewById(R.id.bg) as ConstraintLayout


        fullScreen()  // For fullscreen
        villainMove() // Villain Animation

        val handler = Handler()
        val handler1 = Handler()



        val runnableCode: Runnable = object : Runnable {
            override fun run() {
                rocket.visibility = View.VISIBLE
                fireVillainArrow(rocket, 1800)
                handler.postDelayed(this, 6000)
            }
        }

        handler.post(runnableCode)



        val runnableCode1: Runnable = object : Runnable {
            override fun run() {
                bullet.visibility = View.VISIBLE
                fireVillainArrow(bullet, 2300)
                handler1.postDelayed(this, 2500)
            }
        }

        handler1.post(runnableCode1)



        screen.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(view: View, event: MotionEvent): Boolean {

                if(event.action == MotionEvent.ACTION_MOVE)
                {
                    character.y = event.y - 300F
                }

                return true
            }
        })



        shoot.setOnLongClickListener{

            blast.visibility = View.VISIBLE
            fireArrow(blast, 1000)

            return@setOnLongClickListener true
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


    fun shoot(view: View)
    {
        val arrow : ImageView = findViewById(R.id.playerArrow) as ImageView
        arrow.visibility = View.VISIBLE
        fireArrow(arrow, 1800)

    }



    private fun villainMove() {
        val villain : ImageView = findViewById(R.id.villain) as ImageView
        val translationYFrom = 100f
        val translationYTo = -550f
        val valueAnimator = ValueAnimator.ofFloat(translationYFrom, translationYTo).apply {
            interpolator = LinearInterpolator()
            duration = 3000
        }
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            villain.translationY = value
        }

        val del : Long = 1000

        valueAnimator.setRepeatCount(ValueAnimator.INFINITE)
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE)
        valueAnimator.startDelay = del
        valueAnimator.start()
    }



    private fun fireArrow(arrow : ImageView, dur : Long) {

        val character : ImageView = findViewById(R.id.character) as ImageView
        val villain : ImageView = findViewById(R.id.villain) as ImageView
        var villainEnergy : ImageView = findViewById(R.id.v_bar) as ImageView
        val handler = Handler()
        val durArrow : Long = 1800
        val durBlast : Long = 1000

        handler.postDelayed({
            arrow.visibility = View.INVISIBLE

            if(dur == durArrow)
            {
                if(villPower > 0 && arrow.y >= villain.y - 25F && arrow.y <= villain.y + 240F)
                {
                    villPower -= 1
                    var imgName = "v_energy_" + villPower.toString()
                    villainEnergy.setImageResource(getResources().getIdentifier(imgName, "drawable", getPackageName()))

                }

                else if(villPower <= 0 && globalFlag == 1)
                {
                    globalFlag = 0
                    val intent = Intent(this, playAgain::class.java)
                    intent.putExtra("flag", "1")
                    finish()
                    startActivity(intent)
                }

            }

            else if(dur == durBlast)
            {
                if(villPower > 0 && arrow.y >= villain.y - 35F && arrow.y <= villain.y + 250F)
                {
                    villPower -= 2
                    var imgName = "v_energy_" + villPower.toString()
                    villainEnergy.setImageResource(getResources().getIdentifier(imgName, "drawable", getPackageName()))

                }

                else if(villPower <= 0 && globalFlag == 1)
                {
                    globalFlag = 0
                    val intent = Intent(this, playAgain::class.java)
                    intent.putExtra("flag", "1")
                    finish()
                    startActivity(intent)
                }

            }

        }, dur)


        val cl = character.x
        val cu = character.y + 250f

        arrow.x = cl
        arrow.y = cu

        val translationXFrom = cl
        val translationXTo = cl + 1050f

        val valueAnimator = ValueAnimator.ofFloat(translationXFrom, translationXTo).apply {
            interpolator = LinearInterpolator()
            duration = dur
        }
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            arrow.translationX = value
        }

        valueAnimator.start()
    }



    private fun fireVillainArrow(arrow : ImageView, dur : Long) {

        val character : ImageView = findViewById(R.id.villain) as ImageView
        val villain : ImageView = findViewById(R.id.character) as ImageView
        var villainEnergy : ImageView = findViewById(R.id.h_bar) as ImageView
        val handler = Handler()
        val durArrow : Long = 2300
        val durBlast : Long = 1800

        handler.postDelayed({
            arrow.visibility = View.INVISIBLE

            if(dur == durArrow)
            {
                if(chPower > 0 && arrow.y >= villain.y + 60F && arrow.y <= villain.y + 300F)
                {
                    chPower -= 1
                    var imgName = "h_energy_" + chPower.toString()
                    villainEnergy.setImageResource(getResources().getIdentifier(imgName, "drawable", getPackageName()))

                }

                else if(chPower <= 0 && globalFlag == 1)
                {
                    globalFlag = 0
                    val intent = Intent(this, playAgain::class.java)
                    intent.putExtra("flag", "0")
                    finish()
                    startActivity(intent)
                }

            }

            else if(dur == durBlast)
            {
                if(chPower > 0 && arrow.y >= villain.y + 120F && arrow.y <= villain.y + 350F)
                {
                    chPower -= 2
                    var imgName = "h_energy_" + chPower.toString()
                    villainEnergy.setImageResource(getResources().getIdentifier(imgName, "drawable", getPackageName()))

                }

                else if(chPower <= 0 && globalFlag == 1)
                {
                    globalFlag = 0
                    val intent = Intent(this, playAgain::class.java)
                    intent.putExtra("flag", "0")
                    finish()
                    startActivity(intent)
                }

            }

        }, dur)

        val cl = character.x
        val cu = character.y

        arrow.x = cl
        arrow.y = cu

        val translationXFrom = -30f
        val translationXTo = -1370f

        val valueAnimator = ValueAnimator.ofFloat(translationXFrom, translationXTo).apply {
            interpolator = LinearInterpolator()
            duration = dur
        }
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            arrow.translationX = value
        }

        valueAnimator.start()
    }



    fun back(view: View)
    {
        val intent = Intent(this, MainActivity::class.java)
        finish()
        startActivity(intent)
    }

}