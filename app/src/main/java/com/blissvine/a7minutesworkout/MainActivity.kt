package com.blissvine.a7minutesworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.FrameLayout
import android.widget.Toast
import com.blissvine.a7minutesworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.flStart?.setOnClickListener {
            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)

        }

        binding?.flBMI?.setOnClickListener{
             val intent = Intent(this@MainActivity, BMIActivity::class.java)
              startActivity(intent)
       }

        binding?.flHistory?.setOnClickListener{
            val intent = Intent(this@MainActivity, HistoryActivity::class.java)
            startActivity(intent)
        }

    }

    // Always use this to  set binding back to null as it is a safe way and we can prevent
    // errors/memory leaks
    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }

}