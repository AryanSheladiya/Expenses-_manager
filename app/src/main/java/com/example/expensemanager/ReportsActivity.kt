package com.example.expensemanager

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
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
            reportsAdapter = ReportsAdapter(list, deleteClick = {id ->
                Log.d("TAG", "initview: "+id)

                val builder = AlertDialog.Builder(this@ReportsActivity)
                val inflater = layoutInflater
                val dialogLayout = inflater.inflate(R.layout.alert_dilog_box, null)

                builder.setView(dialogLayout)

                val dialog = builder.create()

                // Initialize buttons
                val imgYes = dialogLayout.findViewById<ImageView>(R.id.imgYes)
                val imgNo = dialogLayout.findViewById<ImageView>(R.id.imgNo)

                imgNo.setOnClickListener {
                    // Handle Cancel button click
                    dialog.dismiss()
                }

                imgYes.setOnClickListener {
                    // Handle Delete button click
                    // Put your delete logic here

                    dataBaseHalper.DeleteData(id)
                    var list = dataBaseHalper.DisplayRecord_1()
                    reportsAdapter.UpdateData(list)
                    dialog.dismiss()
                }

                dialog.show()
            })
            var manager =
                LinearLayoutManager(this@ReportsActivity, LinearLayoutManager.VERTICAL, false)

            rcvReports.adapter = reportsAdapter
            rcvReports.layoutManager = manager
        }
    }
}