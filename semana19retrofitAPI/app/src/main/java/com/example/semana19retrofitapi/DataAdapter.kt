package com.example.semana19retrofitapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class DataAdapter(private var dataList:List<Cat>, private val context:Context):
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent:ViewGroup, viewType:Int):ViewHolder{
            return ViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.list_item,
                    parent,
                    false
                )
            )
    }
    override fun getItemCount():Int{
        return  dataList.size
    }
    override fun onBindViewHolder(holder:ViewHolder, position:Int){
        val catModel = dataList.get(position)

        holder.txtName.text= catModel.name
        holder.txtOrigin.text=catModel.origin
        holder.txtDescription.text= catModel.description

        val imageUrl = "https://cdn2.thecatapi.com/images/"+catModel.reference_image_id+".jpg"
        Picasso.get().load(imageUrl).into(holder.imgView)

    }

    class ViewHolder(itemLayoutView:View):RecyclerView.ViewHolder(itemLayoutView){
        var txtName: TextView = itemLayoutView.findViewById(R.id.name)
        var txtOrigin: TextView =itemLayoutView.findViewById(R.id.origin)
        var txtDescription: TextView= itemLayoutView.findViewById(R.id.description)
        var imgView: ImageView = itemLayoutView.findViewById(R.id.imgCat)
    }
}
