package com.selva.todolist

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.selva.todolist.databinding.TaskItemCellBinding
import com.selva.todolist.models.TodoItem

class TodoItemViewHolder(
    private val context: Context,
    private val binding: TaskItemCellBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bindTodoItem(todoItem: TodoItem){
        binding.name.text = todoItem.text

//        if (todoItem.deadline != null){
//            binding.deadline.text.
//        }
    }
}