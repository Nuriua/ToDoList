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
        var spinner = binding.prioritySpinner
        val spinnerItems = resources.getStringArray(R.array.priority_array)
//        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItems)
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        spinner.adapter = adapter
        var selectedItem = "medium"
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Обработка выбора элемента
                selectedItem = spinnerItems[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Вызывается, когда ничего не выбрано
            }
        }
        if (todoItem == null){
            val newTodo = TodoItem("null", name, false, selectedItem)
            todoViewModel.addToDoItem(newTodo)
        }
        else{
            todoViewModel.updateToDoItem(todoItem!!.id, name, todoItem!!.flag, todoItem!!.importance)
        }
        binding.name.setText("")
        dismiss()
    }
}