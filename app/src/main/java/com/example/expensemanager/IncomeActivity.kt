package com.example.expensemanager

import CategoryModel
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.DatePicker
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.view.get
import com.example.expensemanager.databinding.ActivityIncomeBinding
import dbName
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class IncomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityIncomeBinding
    var calendar = Calendar.getInstance()
    lateinit var myDataBaseHalper: MyDataBaseHalper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income)

        binding = ActivityIncomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initview()
    }

    private fun initview() {

        myDataBaseHalper = MyDataBaseHalper(this, dbName)
        var list = myDataBaseHalper.DisplayRecord()

        var categoryAdapter = CategoryAdapter(this, list)

        binding.spnCategory.adapter = categoryAdapter

        with(binding) {

            imgBack.setOnClickListener {
                onBackPressed()
            }

            txtDate.setOnClickListener {
                ShowDatePickerDialog()
            }

            var data = intent.getStringExtra("Type")

            if (data == "Expenses") {
                rbExpense.isChecked = true
                txtName.text = "Add Expenses"
            } else {
                rbIncome.isChecked = true
                txtName.text = "Add Income"
            }

            imgAdd.setOnClickListener {

                var incomeExpenses = data.toString()
                var amount = edtAmount.text.toString().toInt()
                var date = txtDate.text.toString()
                var mode = edtMode.text.toString()
                var note = edtNote.text.toString()

                var category = spnCategory.selectedItem

                if (category is CategoryModel) {
                    myDataBaseHalper.InsertRecord_1(
                        incomeExpenses,
                        amount,
                        category.category,
                        date,
                        mode,
                        note
                    )

                    Toast.makeText(this@IncomeActivity, "submit data", Toast.LENGTH_SHORT).show()

                    Log.d(
                        "TAG",
                        "initview: " + incomeExpenses + "  " + amount + "  " + category.category +
                                "  " + date + "  " + mode + "  " + "  " + note
                    )
                    var intent = Intent(this@IncomeActivity, ReportsActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    fun ShowDatePickerDialog() {
        var datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun updateDateInView() {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.txtDate.text = sdf.format(calendar.time)
    }
}