package com.selva.todolist

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.selva.todolist.models.TodoItem
import java.util.concurrent.Flow

@Dao
interface TodoItemDao {
    @Query("SELECT * FROM task_item_table ORDER BY id ASK")
    fun allTodoItems(): Flow<List<TodoItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodoItem(todoItem: TodoItem)
    suspend fun updateTodoItem(todoItem: TodoItem)
    suspend fun deleteTodoItem(todoItem: TodoItem)
}