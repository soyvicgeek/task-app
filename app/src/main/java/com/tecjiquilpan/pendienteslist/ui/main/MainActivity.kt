package com.tecjiquilpan.pendienteslist.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
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
            },
            onUpdateClicked = { item, position, list ->
                run {
                    updateSchedule(item, position, list, false)
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

    private fun updateSchedule(item: ScheduleEntity, position: Int, list: MutableList<ScheduleEntity>, b: Boolean) {
        val intent =
            Intent(applicationContext, ScheduleActivity::class.java)
        intent.putExtra("title", item.title)
        intent.putExtra("description", item.description)
        intent.putExtra("time", item.date)
        intent.putExtra("hour", item.hour)
        intent.putExtra("isEdit", true)
        intent.putExtra("id", item.id)
        startActivity(intent)
        finish()
    }

    private fun goToScheduleActivity() {
        val intent =
            Intent(applicationContext, ScheduleActivity::class.java)
        intent.putExtra("isEdit", false)
        startActivity(intent)
        finish()
    }

    private fun observer() {
        viewModel.getScheduleList.observe(this) {
            if (it != null) {
                adapter.updateList(it.toMutableList())
                binding.taskRecycler.adapter = adapter
                binding.noDataImage.visibility = View.GONE
                binding.taskRecycler.visibility = View.VISIBLE
            } else {
                binding.noDataImage.visibility = View.VISIBLE
                binding.taskRecycler.visibility = View.GONE
            }
        }
    }

    private fun updateDataLocal(
        item: ScheduleEntity,
        position: Int,
        list: MutableList<ScheduleEntity>
    ) {
        viewModel.deleteSchedule(item.id.toString())

        viewModel.getScheduleList.observe(this) {
            if (it != null) {
                Toast.makeText(this, "Se elimino correctamente", Toast.LENGTH_LONG).show()
            }
        }
        viewModel.geScheduleList()
    }

    private fun setupRecycleView() = with(binding) {
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