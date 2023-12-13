package com.tecjiquilpan.pendienteslist.ui.main

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.tecjiquilpan.pendienteslist.R
import com.tecjiquilpan.pendienteslist.data.local.room.entity.ScheduleEntity
import com.tecjiquilpan.pendienteslist.databinding.ActivityMainBinding
import com.tecjiquilpan.pendienteslist.ui.schedule.ScheduleActivity
import com.tecjiquilpan.pendienteslist.ui.schedule.ScheduleAdapter
import com.tecjiquilpan.pendienteslist.ui.schedule.ScheduleViewModel
import com.tecjiquilpan.pendienteslist.utils.setupDialog

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private val addTaskDialog : Dialog by lazy {
        Dialog(this).apply {
            setupDialog(R.layout.add_task)
        }
    }

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

    private fun updateDataLocal(item: ScheduleEntity, position: Int, list: MutableList<ScheduleEntity>) {

    }

    private fun setupRecycleView() = with(binding){
        val staggeredGridLayoutManager =
            GridLayoutManager(
                binding.root.context,
                1,
                androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,
                false
            )

        //adapter.updateList()
        taskRecycler.adapter = adapter
        taskRecycler.isNestedScrollingEnabled = false
        taskRecycler.layoutManager = staggeredGridLayoutManager
    }
}