package com.selva.todolist.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate

class TodoViewModel: ViewModel() {
    var todoItems = MutableLiveData<MutableList<TodoItem>>()

    init {
        todoItems.value = mutableListOf()
    }

    fun addToDoItem(newTodoItem: TodoItem){
        val list = todoItems.value
        list!!.add(newTodoItem)
        todoItems.postValue(list)
    }

    fun updateToDoItem(id:String, text:String, flag:Boolean){
        val list = todoItems.value
        val todo = list!!.find{it.id == id}!!
        todo.text = text
        todo.flag = flag
//        todo.importance = importance
        todoItems.postValue(list)
    }

    fun setCompleted(todoItem: TodoItem){
        val list = todoItems.value
        val todo = list!!.find{it.id == todoItem.id}!!
        todo.flag = true
        todoItems.postValue(list)
    }
}