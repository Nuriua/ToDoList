package com.selva.todolist.models

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.selva.todolist.R
import java.text.DateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Entity(tableName = "todo_item_table")
class TodoItem(
    @ColumnInfo(name = "text") var text: String,
    @ColumnInfo(name = "desc") var desc: String,
    @ColumnInfo(name = "dueTimeString") var dueTimeString: String?,
    @ColumnInfo(name = "completedDateString") var completedDateString: String?,
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    ) {

    fun completedDate(): LocalDate? = if (completedDateString == null) null
        else LocalDate.parse(completedDateString, timeFormatter)

    fun dueTime(): LocalTime? = if (completedDateString == null) null
    else LocalTime.parse(completedDateString, timeFormatter)

    fun isCompleted() = completedDate() != null
    fun imageResource(): Int = if(isCompleted()) R.drawable.checked_24 else R.drawable.unchecked_24
    fun imageColor(context: Context): Int = if(isCompleted()) green(context) else dark(context)

    private fun green(context: Context) = ContextCompat.getColor(context, R.color.teal_200)
    private fun dark(context: Context) = ContextCompat.getColor(context, R.color.teal_700)

    companion object{
        val timeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_TIME
        val dateFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE
    }
}