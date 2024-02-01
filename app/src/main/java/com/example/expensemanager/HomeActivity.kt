package com.example.expensemanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.RadioButton
import com.example.expensemanager.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initview()
    }

    private fun initview() {
        with(binding) {
            crdIncome.setOnClickListener {
                var intent = Intent(this@HomeActivity,IncomeActivity::class.java)
                intent.putExtra("Type","Income")
                startActivity(intent)
            }

            crdExpenses.setOnClickListener {
                var intent = Intent(this@HomeActivity,IncomeActivity::class.java)
                intent.putExtra("Type","Expenses")
                startActivity(intent)
            }

            imgDrawer.setOnClickListener {
                drewerlayout.openDrawer(Gravity.START)
            }

            crdReports.setOnClickListener {
                var intent = Intent(this@HomeActivity,ReportsActivity::class.java)
                startActivity(intent)
            }

            crdCategory.setOnClickListener {
                var intent = Intent(this@HomeActivity,CategoryActivity::class.java)
                startActivity(intent)
            }
        }
    }
}