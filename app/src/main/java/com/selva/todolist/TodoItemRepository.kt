package com.selva.todolist

import androidx.annotation.WorkerThread
import com.selva.todolist.models.TodoItem
import kotlinx.coroutines.flow.Flow

class TodoItemRepository(private val todoItemDao: TodoItemDao) {
    val allTodoItems: Flow<TodoItem> = todoItemDao.allTodoItems()

    @WorkerThread
    suspend fun insertTodoItem(todoItem: TodoItem){
        todoItemDao.insertTodoItem(todoItem)
    }

    @WorkerThread
    suspend fun updateTodoItem(todoItem: TodoItem){
        todoItemDao.insertTodoItem(todoItem)
    }
}