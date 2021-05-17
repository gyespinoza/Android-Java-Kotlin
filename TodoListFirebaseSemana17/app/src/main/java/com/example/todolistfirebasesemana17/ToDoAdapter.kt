package com.example.todolistfirebasesemana17

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView

class ToDoAdapter(context: Context, toDoList:MutableList<Model>):BaseAdapter() {
    private val inflater:LayoutInflater= LayoutInflater.from(context)
    private var itemList = toDoList
    private var updateAndDeleteInterface:UpdateAndDeleteInterface= context as UpdateAndDeleteInterface

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val id: String = itemList.get(position).id as String
        val itemText = itemList.get(position).item as String
        val done: Boolean=itemList.get(position).done as Boolean

        val view:View
        val viewHolder: ListViewHolder

        if(convertView==null){
            view = inflater.inflate(R.layout.itemlayout, parent, false)
            viewHolder=ListViewHolder(view)
            view.tag=viewHolder
        }else{
            view=convertView
            viewHolder=view.tag as ListViewHolder
        }
        viewHolder.textLabel.text=itemText
        viewHolder.isDone.isChecked=done

        viewHolder.isDone.setOnClickListener{
            updateAndDeleteInterface.modifyItem(id,!done)
        }
        viewHolder.isDeleted.setOnClickListener {
            updateAndDeleteInterface.onItemDelete(id)
        }
        return  view
    }

    override fun getItem(position: Int):Any{
        return itemList.get(position)
    }
    override  fun getItemId(position: Int):Long{
        return position.toLong()
    }

    override fun getCount(): Int {
        return itemList.size
    }

}

class ListViewHolder(row:View?) {
    val textLabel:TextView=row!!.findViewById(R.id.item_textview) as TextView
    val isDone: CheckBox = row!!.findViewById(R.id.checkbox) as CheckBox
    val isDeleted: ImageButton = row!!.findViewById(R.id.delete) as ImageButton
}
