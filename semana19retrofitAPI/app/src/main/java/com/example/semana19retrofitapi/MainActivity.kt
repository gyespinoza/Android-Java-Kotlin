package com.example.semana19retrofitapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var dataList = ArrayList<Cat>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //agregando adaptador a recyclerView
        recycler_view.adapter= DataAdapter(dataList, this)
        recycler_view.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        //getData
        getData()
    }
    private fun getData(){
        val call: Call<List<Cat>> = ApiClient.getClient.getCats()
        call.enqueue(object:Callback<List<Cat>>{
            override fun onResponse(call: Call<List<Cat>>, response: Response<List<Cat>>) {
                dataList.addAll(response!!.body()!!)
                recycler_view.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<Cat>>, t: Throwable) {
               Toast.makeText(applicationContext, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}