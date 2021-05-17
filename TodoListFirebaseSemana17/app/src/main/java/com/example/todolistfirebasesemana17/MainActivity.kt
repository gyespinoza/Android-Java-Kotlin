package com.example.todolistfirebasesemana17

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.*

class MainActivity : AppCompatActivity(), UpdateAndDeleteInterface {
    lateinit var database: DatabaseReference
    var todoList: MutableList<Model>?=null
    lateinit var adapter: ToDoAdapter
    private var listViewItem: ListView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listViewItem=findViewById(R.id.item_listview)

        database= FirebaseDatabase.getInstance().reference


        todoList = mutableListOf<Model>()
        adapter = ToDoAdapter(this, todoList!!)
        listViewItem!!.adapter=adapter
        database.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "No se ha podido agregar", Toast.LENGTH_LONG).show()
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                todoList!!.clear()
                addItemTolist(snapshot)
            }
        })

    }

    private fun addItemTolist(snapshot: DataSnapshot) {
        val items= snapshot.children.iterator()

        if(items.hasNext()){
            val todoIndexedValue = items.next()
            val itemsIterator= todoIndexedValue.children.iterator()

            while(itemsIterator.hasNext()){
                val currentItem = itemsIterator.next()
                val todoItemData = Model.createList()

                val map = currentItem.value as HashMap<*, *>


                todoItemData.id = currentItem.key
                todoItemData.done = map.get("done") as Boolean?
                todoItemData.item=map.get("item") as String?
                todoList!!.add(todoItemData)
            }
        }
        adapter.notifyDataSetChanged()
    }

    override fun modifyItem(itemId: String, isDone: Boolean) {
        val itemReference = database.child("todo").child(itemId)
        itemReference.child("done").setValue(isDone)
    }

    override fun onItemDelete(itemId: String) {
        val itemReference = database.child("todo").child(itemId)
        itemReference.removeValue()
        adapter.notifyDataSetChanged()
    }

    fun ejecutar(view: View){
        val alertDialog= AlertDialog.Builder(this)
        val textEditText = EditText(this)
        alertDialog.setMessage("Agregar nueva tarea")
        alertDialog.setTitle("NUEVA TAREA")
        alertDialog.setView(textEditText)
        alertDialog.setPositiveButton("Crear"){
            dialog, _->
            val todoItemData = Model.createList()
            todoItemData.item=textEditText.text.toString()
            todoItemData.done=false
            val newItemData = database.child("todo").push()
            todoItemData.id = newItemData.key

            newItemData.setValue(todoItemData)

            dialog.dismiss()
            Toast.makeText(this, "Se agrego la tarea!", Toast.LENGTH_LONG).show()
        }
        alertDialog.show()
    }
}