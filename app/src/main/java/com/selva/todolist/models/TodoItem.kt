package com.selva.todolist.models

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.selva.todolist.R
import java.time.LocalDate
@Entity(tableName = "task_item_table")
class TodoItem(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "text") var text: String,
    @ColumnInfo(name = "flag") var flag: Boolean,
    ) {

    fun isCompleted() = flag != false
    fun imageResource(): Int = if(isCompleted()) R.drawable.checked_24 else R.drawable.unchecked_24
    fun imageColor(context: Context): Int = if(isCompleted()) green(context) else dark(context)

    private fun green(context: Context) = ContextCompat.getColor(context, R.color.teal_200)
    private fun dark(context: Context) = ContextCompat.getColor(context, R.color.teal_700)
}