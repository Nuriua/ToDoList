package com.selva.todolist

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        if (todoItem == null){
            val newTodo = TodoItem("null", name, false)
            todoViewModel.addToDoItem(newTodo)
        }
        else{
            todoViewModel.updateToDoItem(todoItem!!.id, name, null)
        }
        binding.name.setText("")
        dismiss()
    }
}