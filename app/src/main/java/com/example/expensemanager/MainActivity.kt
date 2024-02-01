package com.example.expensemanager

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.expensemanager.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initview()
    }

    private fun initview() {
        Handler().postDelayed(Runnable { // This method will be executed once the timer is over
            val i = Intent(this@MainActivity, HomeActivity::class.java)
            startActivity(i)
            finish()
        }, 1500)
    }
}