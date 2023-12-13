package com.tecjiquilpan.pendienteslist.ui.schedule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tecjiquilpan.pendienteslist.data.local.room.entity.ScheduleEntity
import com.tecjiquilpan.pendienteslist.databinding.ItemTaskBinding

class ScheduleAdapter(
    val onClicked: (ScheduleEntity, Int, MutableList<ScheduleEntity>) -> Unit
) : RecyclerView.Adapter<ScheduleAdapter.MotorcycleViewHolder>() {

    private var list: MutableList<ScheduleEntity> = arrayListOf()
    private var selectedItemPosition: Int? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MotorcycleViewHolder {
        val itemView =
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MotorcycleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MotorcycleViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item, position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(list: MutableList<ScheduleEntity>) {
        this.list = list
    }

    inner class MotorcycleViewHolder(val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ScheduleEntity, position: Int) {
            binding.description.text = item.motorcycleBrand

            binding.containerTask.setOnClickListener {
                selectedItemPosition = position
                notifyDataSetChanged()
                onClicked.invoke(item, position, list)
            }
        }
    }
}