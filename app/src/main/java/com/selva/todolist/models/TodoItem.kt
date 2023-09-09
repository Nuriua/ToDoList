package com.selva.todolist.models

import android.content.Context
import androidx.core.content.ContextCompat
import com.selva.todolist.R
import java.time.LocalDate

data class TodoItem(
    var id: String,
    var text: String,
    var importance: String,
    var deadline: LocalDate?,
    var flag: Boolean,
    var dateOfCreation: LocalDate?,
    var dateOfChange: LocalDate?) {

    fun isCompleted() = flag != false
    fun imageResourse(): Int = if(isCompleted()) R.drawable.checked_24 else R.drawable.unchecked_24
    fun imageColor(context: Context): Int = if(isCompleted()) green(context) else dark(context)

    private fun green(context: Context) = ContextCompat.getColor(context, R.color.teal_200)
    private fun dark(context: Context) = ContextCompat.getColor(context, R.color.teal_700)
}