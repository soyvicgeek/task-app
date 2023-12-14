package com.tecjiquilpan.pendienteslist.ui.schedule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tecjiquilpan.pendienteslist.data.local.room.entity.ScheduleEntity
import com.tecjiquilpan.pendienteslist.databinding.ItemTaskBinding
import kotlin.random.Random


class ScheduleAdapter(
    val onClicked: (ScheduleEntity, Int, MutableList<ScheduleEntity>) -> Unit,
    val onUpdateClicked: (ScheduleEntity, Int, MutableList<ScheduleEntity>) -> Unit
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
            binding.title.text = item.title
            binding.description.text = item.description
            val date = item.date.split(" ")
            if (date.isNotEmpty() && date.size > 1) {
                binding.date.text = date[0]
                val month = date[2].substring(0, 3)
                binding.month.text = month
            }
            val random = Random.Default
            val randomNumber = random.nextInt(7)
            val diasSemana = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")
            val diasAleatorios = diasSemana.shuffled()
            binding.day.text = diasAleatorios[randomNumber].substring(0, 3)

            binding.time.text = item.hour

            binding.containerTask.setOnLongClickListener { v ->
                selectedItemPosition = position
                notifyDataSetChanged()
                onClicked.invoke(item, position, list)
                false
            }

            binding.containerTask.setOnClickListener {
                notifyDataSetChanged()
                onUpdateClicked.invoke(item, position, list)
            }
        }
    }
}