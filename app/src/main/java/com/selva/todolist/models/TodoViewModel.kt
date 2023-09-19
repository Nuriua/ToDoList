package com.selva.todolist.models

import androidx.lifecycle.*
import com.selva.todolist.TodoItemRepository
import kotlinx.coroutines.launch
import java.time.LocalDate

class TodoViewModel(private val repository: TodoItemRepository): ViewModel() {
    var todoItems: LiveData<List<TodoItem>> = repository.allTodoItems.asLiveData()

    fun addToDoItem(newTodoItem: TodoItem) = viewModelScope.launch{
        repository.insertTodoItem(newTodoItem)
    }

    fun updateToDoItem(todoItem: TodoItem) = viewModelScope.launch{
        repository.updateTodoItem(todoItem)
    }

    fun setCompleted(todoItem: TodoItem) = viewModelScope.launch{
        repository.updateTodoItem(todoItem)
    }
}

class TodoItemModelFactory(private val repository: TodoItemRepository): ViewModelProvider.Factory{

}