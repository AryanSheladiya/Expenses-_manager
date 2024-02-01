package com.example.expensemanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensemanager.databinding.ActivityReportsBinding
import dbName


class ReportsActivity : AppCompatActivity() {

    lateinit var binding: ActivityReportsBinding
    lateinit var dataBaseHalper: MyDataBaseHalper
    lateinit var reportsAdapter: ReportsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reports)

        binding = ActivityReportsBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        initview()
    }

    private fun initview() {

        with(binding) {
//            Database Adapter
            dataBaseHalper = MyDataBaseHalper(this@ReportsActivity, dbName)
            var list = dataBaseHalper.DisplayRecord_1()
//            back intent
            imgBack.setOnClickListener {
                onBackPressed()
            }

//            RecyclerView
            reportsAdapter = ReportsAdapter(list)
            var manager =
                LinearLayoutManager(this@ReportsActivity, LinearLayoutManager.VERTICAL, false)

            rcvReports.adapter = reportsAdapter
            rcvReports.layoutManager = manager
        }
    }
}