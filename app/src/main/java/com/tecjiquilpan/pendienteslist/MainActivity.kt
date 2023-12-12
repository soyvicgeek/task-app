package com.tecjiquilpan.pendienteslist

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tecjiquilpan.pendienteslist.databinding.ActivityMainBinding
import com.tecjiquilpan.pendienteslist.utils.setupDialog

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    private val addTaskDialog : Dialog by lazy {
        Dialog(this).apply {
            setupDialog(R.layout.add_task)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.addTask.setOnClickListener {
            addTaskDialog.show()
        }


    }
}