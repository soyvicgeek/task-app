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
import java.util.Calendar

class ScheduleActivity : AppCompatActivity() {

    private val viewModel: ScheduleViewModel by viewModels()
    private lateinit var binding: ActivityScheduleBinding
    private var dateEventsName: String? = ""
    private var isEdit: Boolean = false
    private var title: String? = ""
    private var description: String? = ""
    private var time: String? = ""
    private var hour: String? = ""
    private var idSchedule: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() = with(binding) {
        getSchedule()
        observer()
        setupDatePicker()

        cancelTask.setOnClickListener {
            goToScheduleActivity()
        }

        addTask.setOnClickListener {
            if (isEdit) {
                val data = ScheduleEntity(
                    idSchedule ?: 0,
                    title = addTaskTitle.text.toString(),
                    description = addTaskDescription.text.toString(),
                    date = dateEventsName ?: "",
                    hour = taskTime.text.toString()
                )
                viewModel.updateTypeLikeMotorcycle(data)
            } else {
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
    }

    private fun getSchedule() = with(binding) {
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            title = bundle.getString("title").toString()
            description = bundle.getString("description").toString()
            time = bundle.getString("time").toString()
            hour = bundle.getString("hour").toString()
            isEdit = bundle.getBoolean("isEdit")
            idSchedule = bundle.getInt("id")

            if (isEdit) {
                addTaskTitle.setText(title)
                addTaskDescription.setText(description)
                taskTime.setText(hour)
                taskTime.text.toString().chunked(2).joinToString(":")
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


        viewModel.updateLikeAndInterests.observe(this) {
            if (it != null) {
                Toast.makeText(this, "Se a actualizo correctamente", Toast.LENGTH_LONG).show()
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
        constraintsBuilder.setFirstDayOfWeek(Calendar.SUNDAY) // Set the first day of the week
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