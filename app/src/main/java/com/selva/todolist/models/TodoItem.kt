package com.selva.todolist.models

import java.time.LocalDate
import java.util.Date

data class TodoItem(
    var id: String,
    var text: String,
    var importance: String,
    var deadline: LocalDate?,
    var flag: Boolean,
    var dateOfCreation: LocalDate?,
    var dateOfChange: LocalDate?) {
}