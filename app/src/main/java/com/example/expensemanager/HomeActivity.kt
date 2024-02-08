package com.example.expensemanager


import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import com.example.expensemanager.databinding.ActivityHomeBinding
import dbName
import java.util.Calendar

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    lateinit var dataBaseHalper: MyDataBaseHalper

    //    Notification

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        dataBaseHalper = MyDataBaseHalper(this@HomeActivity, dbName)

        initview()
        ScheduleAlarm()
    }

    override fun onResume() {
        super.onResume()

        UpdateBalance()
    }

    private fun UpdateBalance() {

        var symbol = "$"

        val totalIncome = dataBaseHalper.calculateTotalIncome()
        val totalExpenses = dataBaseHalper.calculateTotalExpenses()
        val balance = totalIncome - totalExpenses

        with(binding) {
            txtIncome.text = symbol + totalIncome.toString()
            txtExpense.text = symbol + totalExpenses.toString()
            txtBalance.text = symbol + balance.toString()
        }
    }

    @SuppressLint("RemoteViewLayout")
    private fun initview() {

        with(binding) {
            crdIncome.setOnClickListener {
                var intent = Intent(this@HomeActivity, IncomeActivity::class.java)
                intent.putExtra("Type", "Income")
                startActivity(intent)
            }

            crdExpenses.setOnClickListener {
                var intent = Intent(this@HomeActivity, IncomeActivity::class.java)
                intent.putExtra("Type", "Expenses")
                startActivity(intent)
            }

            imgDrawer.setOnClickListener {
                drewerlayout.openDrawer(Gravity.START)
            }

            crdReports.setOnClickListener {
                var intent = Intent(this@HomeActivity, ReportsActivity::class.java)
                startActivity(intent)
            }

            crdCategory.setOnClickListener {
                var intent = Intent(this@HomeActivity, CategoryActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun ScheduleAlarm() {
        val alarmMgr = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(this@HomeActivity, AlarmReceiver::class.java).let { intent ->
            intent.action = "ALARM_ACTION"
            PendingIntent.getBroadcast(this@HomeActivity, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        }

//         Set the alarm to start at 10 M every day
        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 22)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }

//         Schedule the alarm
        alarmMgr.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis, alarmIntent
        )
    }
}