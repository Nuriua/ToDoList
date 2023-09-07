package com.selva.todolist.models

import androidx.lifecycle.MutableLiveData
import java.time.LocalDate

class TodoItemsRepository(item: TodoItem){
    var todoItems = MutableLiveData<MutableList<TodoItem>>()

    init {
        todoItems.value = mutableListOf()
    }

    fun addTodoItem(newTodoItem: TodoItem){
        val list = todoItems.value
        list!!.add(newTodoItem)
        newTodoItem.dateOfCreation = LocalDate.now()
        todoItems.postValue(list)
    }

    fun updateTodoItem(id: String, text: String, deadline: LocalDate?, dateOfChange: LocalDate){
        val list = todoItems.value
        val task = list!!.find { it.id == id }
        task.text = text
        task.deadline = deadline
        task.dateOfChange = LocalDate.now()
        todoItems.postValue(list)
    }

    fun setCompletedTodoItem(todoItem: TodoItem){
        val list = todoItems.value
        val todoItem = list!!.find { it.id == todoItem.id }
        todoItem.flag = true
        todoItems.postValue(list)
    }
}