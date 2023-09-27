package com.selva.todolist

import android.app.TimePickerDialog
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
import java.time.LocalTime

class NewTaskSheet(var todoItem: TodoItem?) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentNewTaskSheetBinding
    private lateinit var todoViewModel: TodoViewModel
    private var dueTime: LocalTime? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()

        if (todoItem != null){
            binding.taskTitle.text = "EditTask"
            val editable = Editable.Factory.getInstance()
            binding.name.text = editable.newEditable(todoItem!!.text)
            binding.desc.text = editable.newEditable(todoItem!!.desc)
            if (todoItem!!.dueTime() != null){
                dueTime = todoItem!!.dueTime()!!
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
        binding.timePickerButton.setOnClickListener{
            openTimePicker()
        }
    }

    private fun openTimePicker() {
        if(dueTime ==null)
            dueTime = LocalTime.now()
        val listener = TimePickerDialog.OnTimeSetListener{_, selectedHour, selectedMinute->
            dueTime = LocalTime.of(selectedHour, selectedMinute)
            updateTimeButtonText()
        }
        val dialog = TimePickerDialog(activity, listener, dueTime!!.hour, dueTime!!.minute, true)
        dialog.setTitle("Task Due")
        dialog.show()
    }

    private fun updateTimeButtonText() {
        binding.timePickerButton.text = String.format("%02d:%02d", dueTime!!.hour, dueTime!!.minute)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewTaskSheetBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun saveAction(){
        val name = binding.name.text.toString()
        val desk = binding.desc.text.toString()
        val dueTimeString = if (dueTime == null) null else TodoItem.timeFormatter.format(dueTime)
        if (todoItem == null){
            val newTodo = TodoItem(name, desk, dueTimeString, null)
            todoViewModel.addToDoItem(newTodo)
        }
        else{
            todoItem!!.text = name
            todoItem!!.desc = desk
            todoItem!!.dueTimeString = dueTimeString
            todoViewModel.updateToDoItem(todoItem!!)
        }
        binding.name.setText("")
        binding.desc.setText("")
        dismiss()
    }
}
