package com.selva.todolist

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.selva.todolist.databinding.ActivityMainBinding
import com.selva.todolist.databinding.FragmentNewTaskSheetBinding
import com.selva.todolist.models.TodoItem
import com.selva.todolist.models.TodoViewModel

class NewTaskSheet(var todoItem: TodoItem?) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentNewTaskSheetBinding
    private lateinit var todoViewModel: TodoViewModel
//    private val deadline:

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()

        if (todoItem != null){
            binding.taskTitle.text = "EditTask"
            val editable = Editable.Factory.getInstance()
            binding.name.text = editable.newEditable(todoItem!!.text)
            binding.name.desc = editable.newEditable(todoItem!!.desc)
            if (todoItem!!.dueTime() != null){
                dueTime = taskItem!!.dueTime()!!
                updateTimeButtonText()
            }
        }
        else{
            binding.taskTitle.text = "NewTask"
        }

        todoViewModel = ViewModelProvider(activity).get(TodoViewModel::class.java)
        binding.saveButton.setOnClickListener{
            saveAction()
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewTaskSheetBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun saveAction(){
        val name = binding.name.text.toString()
        val desk = binding.desk.text.toString()
        val dueTimeString = if (dueTime == null) null else TodoItem.timeFormatter.format(dueTime)
        if (todoItem == null){
            val newTodo = TodoItem(name, desk, dueTimeString, null)
            todoViewModel.addToDoItem(newTodo)
        }
        else{
            todoViewModel!!.text = name
            todoItem!!.desk = desk
            todoItem!!.dueTimeString = dueTimeString
            todoViewModel.updateToDoItem(todoItem!!)
        }
        binding.name.setText("")
        binding.desk.setText("")
        dismiss()
    }
}
