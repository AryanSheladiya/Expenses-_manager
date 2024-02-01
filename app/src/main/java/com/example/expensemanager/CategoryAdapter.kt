package com.example.expensemanager

import CategoryModel
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CategoryAdapter(var context: Context, var list: ArrayList<CategoryModel>): BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var inflater = LayoutInflater.from(context)
        var itemView = convertView ?: inflater.inflate(R.layout.category_item_file,parent,false)

        var txtCategory : TextView = itemView.findViewById(R.id.txtCategory)
        txtCategory.setText(list[position].category)

        return itemView
    }
}