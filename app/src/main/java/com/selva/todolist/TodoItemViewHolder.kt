package com.selva.todolist

import android.content.Context
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.selva.todolist.databinding.TaskItemCellBinding
import com.selva.todolist.models.TodoItem

class TodoItemViewHolder(
    private val context: Context,
    private val binding: TaskItemCellBinding,
    private val clickListener: TodoItemListener
): RecyclerView.ViewHolder(binding.root) {
    fun bindTodoItem(todoItem: TodoItem){
        binding.name.text = todoItem.text

        if (todoItem.isCompleted()){
            binding.name.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
//            binding.deadline.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }

        binding.completeButton.setImageResource(todoItem.imageResource())
        binding.completeButton.setColorFilter(todoItem.imageColor(context))

        binding.completeButton.setOnClickListener{
            clickListener.completedTodoItem(todoItem)
        }
        binding.taskCellContainer.setOnClickListener{
            clickListener.editTodoItem(todoItem)
        }
//        if (todoItem.deadline != null){
//            binding.deadline.text.
//        }
    }
}