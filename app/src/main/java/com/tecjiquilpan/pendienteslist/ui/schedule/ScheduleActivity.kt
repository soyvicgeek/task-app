package com.tecjiquilpan.pendienteslist.ui.schedule

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker
import com.tecjiquilpan.pendienteslist.R
import com.tecjiquilpan.pendienteslist.data.local.room.entity.ScheduleEntity
import com.tecjiquilpan.pendienteslist.databinding.ActivityScheduleBinding
import com.tecjiquilpan.pendienteslist.ui.main.MainActivity
import java.text.SimpleDateFormat

class ScheduleActivity : AppCompatActivity() {

    private val viewModel: ScheduleViewModel by viewModels()
    private lateinit var binding: ActivityScheduleBinding
    private var dateEventsName: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() = with(binding) {
        observer()
        setupDatePicker()
        addTask.setOnClickListener {
            if (addTaskTitle.text.toString() != "" && addTaskDescription.text.toString() != "" && dateEventsName != "" && taskTime.text.toString() != "") {
                val data = ScheduleEntity(
                    0,
                    title = addTaskTitle.text.toString(),
                    description = addTaskDescription.text.toString(),
                    date = dateEventsName ?: "",
                    hour = taskTime.text.toString()
                )
                viewModel.addSchedule(data)
            } else {
                Toast.makeText(this@ScheduleActivity, "Llena todos los campos", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun observer() {
        viewModel.addSchedule.observe(this) {
            if (it != null) {
                Toast.makeText(this, "Se a guardado correctamente", Toast.LENGTH_LONG).show()
                goToScheduleActivity()
            } else {
                Toast.makeText(this, "Error al guardar", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setupDatePicker() {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val constraintsBuilder =
            CalendarConstraints.Builder()
        val materialDateBuilder: MaterialDatePicker.Builder<*> =
            datePicker()
        materialDateBuilder.setTitleText("Agrega una fecha")
        val materialDatePicker = materialDateBuilder
            .setCalendarConstraints(constraintsBuilder.build())
            .setTheme(R.style.ThemeOverlay_App_MaterialCalendar)
            .build()

        binding.dateEventsEditText.setOnClickListener {
            materialDatePicker.show(
                this.supportFragmentManager,
                "MATERIAL_DATE_PICKER"
            )
        }

        materialDatePicker.addOnPositiveButtonClickListener {
            binding.dateEventsEditText.setText(materialDatePicker.headerText)
            dateEventsName = materialDatePicker.headerText
        }
    }

    private fun goToScheduleActivity() {
        val intent =
            Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}