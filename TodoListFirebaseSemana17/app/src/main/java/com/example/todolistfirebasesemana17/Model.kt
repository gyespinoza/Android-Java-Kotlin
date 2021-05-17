package com.example.todolistfirebasesemana17

class Model {
    companion object Factory{
        fun createList(): Model=Model()
    }

    //properties
    var id: String? = null
    var item: String? = null
    var done: Boolean? = null
}