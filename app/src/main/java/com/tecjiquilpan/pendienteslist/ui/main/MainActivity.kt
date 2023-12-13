package com.tecjiquilpan.pendienteslist.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.tecjiquilpan.pendienteslist.data.local.room.entity.ScheduleEntity
import com.tecjiquilpan.pendienteslist.databinding.ActivityMainBinding
import com.tecjiquilpan.pendienteslist.ui.schedule.ScheduleActivity
import com.tecjiquilpan.pendienteslist.ui.schedule.ScheduleAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private val adapter by lazy {
        ScheduleAdapter(
            onClicked = { item, position, list ->
                run {
                    updateDataLocal(item, position, list)
                }
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() = with(binding) {
        setupRecycleView()
        observer()

        viewModel.geScheduleList()

        addTask.setOnClickListener {
            goToScheduleActivity()
        }
    }

    private fun goToScheduleActivity() {
        val intent =
            Intent(applicationContext, ScheduleActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun observer() {
        viewModel.getScheduleList.observe(this) {
            if (it != null) {
                adapter.updateList(it.toMutableList())
                binding.taskRecycler.adapter = adapter
            }
        }
    }

    private fun updateDataLocal(item: ScheduleEntity, position: Int, list: MutableList<ScheduleEntity>) {
        viewModel.deleteSchedule(item.id.toString())

        viewModel.getScheduleList.observe(this) {
            if (it != null) {
                Toast.makeText(this, "Se elimino correctamente", Toast.LENGTH_LONG).show()
                viewModel.geScheduleList()
            }
        }
    }

    private fun setupRecycleView() = with(binding){
        val staggeredGridLayoutManager =
            GridLayoutManager(
                binding.root.context,
                1,
                androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,
                false
            )

        taskRecycler.isNestedScrollingEnabled = false
        taskRecycler.layoutManager = staggeredGridLayoutManager
    }
}