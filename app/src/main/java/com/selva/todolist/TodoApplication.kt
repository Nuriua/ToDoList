package com.selva.todolist

import android.app.Application

class TodoApplication: Application() {
    private val database by lazy { TodoItemDatabase.getDatabase(this)}
    val repository by lazy { TodoItemRepository(database.todoItemDao())}
}