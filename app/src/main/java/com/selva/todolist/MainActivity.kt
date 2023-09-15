package com.selva.todolist//·	реализовать дата класс Дела (TodoItem)

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.selva.todolist.databinding.ActivityMainBinding
import com.selva.todolist.models.TodoItem
import com.selva.todolist.models.TodoViewModel

class MainActivity : AppCompatActivity(), TodoItemListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        todoViewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
        binding.newTaskButton.setOnClickListener{
            NewTaskSheet(null).show(supportFragmentManager, "newTaskTag")
        }
        setRecyclerView()
    }

    private fun setRecyclerView(){
        val mainActivity = this
        todoViewModel.todoItems.observe(this){
            binding.todoListResyclerView.apply{
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = TodoItemAdapter(it, mainActivity)
            }
        }
    }

    override fun editTodoItem(todoItem: TodoItem) {
        NewTaskSheet(todoItem).show(supportFragmentManager,"newTaskTag")
    }

    override fun completedTodoItem(todoItem: TodoItem) {
        todoViewModel.setCompleted(todoItem)
    }
}