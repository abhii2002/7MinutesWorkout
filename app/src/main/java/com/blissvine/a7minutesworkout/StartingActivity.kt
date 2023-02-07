package com.blissvine.a7minutesworkout

import android.content.Intent
import android.graphics.drawable.AnimatedVectorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.blissvine.a7minutesworkout.databinding.ActivityStartingBinding

class StartingActivity : AppCompatActivity() {
     private var binding : ActivityStartingBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartingBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )



        Handler(Looper.getMainLooper()).postDelayed({

           startActivity( Intent(this@StartingActivity, MainActivity::class.java))
           finish()
        }, 4000)



    }

//    private fun animationStart(){
//         binding?.imageView?.setImageResource(R.drawable.avd_anim)
//         val avdAnimation : AnimatedVectorDrawable = binding?.imageView?.drawable as AnimatedVectorDrawable
//        avdAnimation.start()
//    }

//    private fun animation(){
//         var layoutAnimation = AnimationUtils.loadAnimation(this@StartingActivity, com.airbnb.lottie.R.anim.abc_fade_in)
//    }
}