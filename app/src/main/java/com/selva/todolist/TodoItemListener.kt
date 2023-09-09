package com.selva.todolist

import com.selva.todolist.models.TodoItem

interface TodoItemListener {
    fun editTodoItem(todoItem: TodoItem)
    fun completedTodoItem(todoItem: TodoItem)
}