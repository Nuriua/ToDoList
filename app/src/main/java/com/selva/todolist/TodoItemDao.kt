package com.selva.todolist

import androidx.room.Dao
import androidx.room.Query
import com.selva.todolist.models.TodoItem
import java.util.concurrent.Flow

@Dao
interface TodoItemDao {
    @Query("SELECT * FROM task_item_table ORDER BY id ASK")
    fun allTodoItems(): Flow<TodoItem>

    suspend fun insertTodoItem(todoItem: TodoItem)
    suspend fun updateTodoItem(todoItem: TodoItem)
}