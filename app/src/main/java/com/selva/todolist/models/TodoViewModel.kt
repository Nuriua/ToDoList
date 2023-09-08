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

    fun updateToDoItem(id:String, text:String, deadline: LocalDate?){
        val list = todoItems.value
        val todo = list!!.find{it.id == id}!!
        todo.text = text
        todo.deadline = deadline
        todoItems.postValue(list)
    }

    fun setCompleted(todoItem: TodoItem){
        val list = todoItems.value
        val todo = list!!.find{it.id == todoItem.id}!!
        todo.flag = true
        todoItems.postValue(list)
    }
}