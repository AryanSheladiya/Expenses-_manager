package com.example.expensemanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.expensemanager.databinding.ActivityCategoryBinding
import dbName

class CategoryActivity : AppCompatActivity() {

    lateinit var binding: ActivityCategoryBinding
    lateinit var myDataBaseHalper: MyDataBaseHalper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        binding = ActivityCategoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initview()
    }

    private fun initview() {

        myDataBaseHalper = MyDataBaseHalper(this,dbName)

        with(binding){
            imgBack.setOnClickListener {
                var intent = Intent(this@CategoryActivity,HomeActivity::class.java)
                startActivity(intent)
            }

            imgAdd.setOnClickListener {
                myDataBaseHalper.InsertRecord(edtCategory.text.toString())
                Toast.makeText(this@CategoryActivity, "Submit Data", Toast.LENGTH_SHORT).show()

                var intent = Intent(this@CategoryActivity,HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }
}