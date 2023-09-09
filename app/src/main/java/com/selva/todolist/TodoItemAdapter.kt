package com.selva.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.selva.todolist.databinding.TaskItemCellBinding
import com.selva.todolist.models.TodoItem

class TodoItemAdapter(
    private val todoItems: List<TodoItem>,
    private val clickListener: TodoItemListener
): RecyclerView.Adapter<TodoItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = TaskItemCellBinding.inflate(from, parent, false)
        return TodoItemViewHolder(parent.context, binding, clickListener)
    }

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        holder.bindTodoItem(todoItems[position])
    }

    override fun getItemCount(): Int = todoItems.size
}