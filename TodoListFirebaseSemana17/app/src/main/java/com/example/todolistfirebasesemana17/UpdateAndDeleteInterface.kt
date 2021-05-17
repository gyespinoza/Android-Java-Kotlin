package com.example.todolistfirebasesemana17

interface UpdateAndDeleteInterface {
    fun modifyItem(itemId: String, isDone: Boolean)
    fun onItemDelete(itemId: String)
}