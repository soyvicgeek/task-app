package com.tecjiquilpan.pendienteslist.ui.schedule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.tecjiquilpan.pendienteslist.data.local.room.entity.ScheduleEntity
import com.tecjiquilpan.pendienteslist.databinding.ActivityScheduleBinding

class ScheduleActivity : AppCompatActivity() {

    private val viewModel: ScheduleViewModel by viewModels()
    private lateinit var binding: ActivityScheduleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() = with(binding) {
        observer()
    }

    private fun updateDataLocal(item: ScheduleEntity, position: Int, list: MutableList<ScheduleEntity>) {

    }

    private fun observer() {
         viewModel.addSchedule.observe(this) {
             if (it != null) {
                 Toast.makeText(this, "Se a gusrdado correctamente", Toast.LENGTH_LONG).show()
             }
         }
     }
}