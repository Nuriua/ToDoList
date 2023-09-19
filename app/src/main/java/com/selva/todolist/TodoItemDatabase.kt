package com.selva.todolist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.selva.todolist.models.TodoItem

@Database(entities = [TodoItem:class], version = 1, exportSchema = falce)
abstract class TodoItemDatabase: RoomDatabase() {
    abstract fun todoItemDao(): TodoItemDao

    companion object{
        @Volatile
        private var INSTANCE: TodoItemDatabase?=null

        fun getDatabase(context: Context): TodoItemDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoItemDatabase::class.java,
                    "task_item_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}