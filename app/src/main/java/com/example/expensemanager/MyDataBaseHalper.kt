package com.example.expensemanager

import CategoryModel
import IncomeExpenses
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDataBaseHalper(var context: Context, var dbName: String) :
    SQLiteOpenHelper(context, dbName, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {

//        Spinner Category
        var sql =
            "create table CategoryTB(CategoryId integer primary key autoincrement,category text)"
        db?.execSQL(sql)

//        IncomeActivity
        var sql_1 =
            "create table IncomeExpensesTB(IncomeExpensesId integer primary key autoincrement,IncomeExpenses text," +
                    "Amount integer,Category text,Date text," +
                    "Mode text,Note text)"
        db?.execSQL(sql_1)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    //    Spinner Category
    fun InsertRecord(category: String) {

        var database = writableDatabase

        var a = ContentValues()

        a.put("category", category)

        database.insert("CategoryTB", null, a)
    }

    //    Spinner Category
    fun DisplayRecord(): ArrayList<CategoryModel> {

        var list: ArrayList<CategoryModel> = ArrayList()
        var database = readableDatabase
        var sql = "select * from CategoryTB"
        var cursor = database.rawQuery(sql, null)

        if (cursor.count > 0) {

            cursor.moveToFirst()
            do {
                var categoryId = cursor.getInt(0)
                var category = cursor.getString(1)

                var model = CategoryModel(categoryId, category)
                list.add(model)
            } while (cursor.moveToNext())
        }
        return list
    }

    //    IncomeActivity
    fun InsertRecord_1(incomeExpenses: String,amount: Int,category: String, date: String, mode: String, note: String) {

        var db = writableDatabase
        var b = ContentValues()

        b.put("IncomeExpenses",incomeExpenses)
        b.put("Amount",amount)
        b.put("Category",category)
        b.put("Date",date)
        b.put("Mode",mode)
        b.put("Note",note)

        db.insert("IncomeExpensesTB",null,b)
    }
//    IncomeActivity
    fun DisplayRecord_1(): ArrayList<IncomeExpenses>{

        var list: ArrayList<IncomeExpenses> = ArrayList()
        var db = readableDatabase
        var sql = "select * from IncomeExpensesTB"
        var cursor = db.rawQuery(sql, null)

        if (cursor.count > 0) {

            cursor.moveToFirst()
            do {

                var incomeExpensesId = cursor.getInt(0)
                var incomeExpenses = cursor.getString(1)
                var amount = cursor.getInt(2)
                var category = cursor.getString(3)
                var date = cursor.getString(4)
                var mode = cursor.getString(5)
                var note = cursor.getString(6)

                var model_1 = IncomeExpenses(incomeExpensesId,incomeExpenses, amount,category,date,mode,note)

                list.add(model_1)

            } while (cursor.moveToNext())
        }
        return list
    }
}