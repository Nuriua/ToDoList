package com.selva.todolist.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.selva.todolist.TodoItemRepository
import java.time.LocalDate

class TodoViewModel(private val repository: TodoItemRepository): ViewModel() {
    var todoItems: LiveData<List<TodoItem>> = repository.all

    init {
        todoItems.value = mutableListOf()
    }

    fun addToDoItem(newTodoItem: TodoItem){
        val list = todoItems.value
        list!!.add(newTodoItem)
        todoItems.postValue(list)
    }

//    fun updateToDoItem(id:String, text:String, flag:Boolean){
//        val list = todoItems.value
//        todoItems.postValue(list)
//    }
//
//    fun setCompleted(todoItem: TodoItem){
//        val list = todoItems.value
//        todoItems.postValue(list)
//    }


}