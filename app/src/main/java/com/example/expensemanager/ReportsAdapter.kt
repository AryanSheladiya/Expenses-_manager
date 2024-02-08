package com.example.expensemanager

import IncomeExpenses
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ReportsAdapter(var list: ArrayList<IncomeExpenses>, var deleteClick: ((id: Int) -> Unit)) :
    RecyclerView.Adapter<ReportsAdapter.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var linContainer: LinearLayout = view.findViewById(R.id.linContainer)

        var txtType: TextView = view.findViewById(R.id.txtType)
        var txtAmount: TextView = view.findViewById(R.id.txtAmount)
        var txtCategory: TextView = view.findViewById(R.id.txtCategory)
        var txtDate: TextView = view.findViewById(R.id.txtDate)
        var txtMode: TextView = view.findViewById(R.id.txtMode)
        var txtNote: TextView = view.findViewById(R.id.txtNote)

        var imgDelete: ImageView = view.findViewById(R.id.imgDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.reports_item_file, parent, false)
        var holder = MyViewHolder(view)
        return holder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        with(holder) {
            txtType.setText(list[position].incomeExpenses)
            txtAmount.setText(list[position].amount.toString())
            txtCategory.setText(list[position].category)
            txtDate.setText(list[position].date)
            txtMode.setText(list[position].mode)
            txtNote.setText(list[position].note)

            var context = itemView.context

            var background = if (list[position].incomeExpenses == "Expenses") {
                R.color.red
            } else {
                R.color.green
            }

            linContainer.backgroundTintList = ContextCompat.getColorStateList(context, background)

            imgDelete.setOnClickListener {
                deleteClick.invoke(list[position].id)
            }
        }
    }

    fun UpdateData(list: ArrayList<IncomeExpenses>) {
        this.list = ArrayList()
        this.list = list
        notifyDataSetChanged()
    }
}