package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding

// ListAdpater es más eficiente que un RecyclerViewAdapter
class SleepNightAdapter(val clickListener: SleepNightListener) :
    ListAdapter<SleepNight, SleepNightAdapter.ViewHolder>(SleepNightDiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        return ViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position) // Ya vienen implementada en ListAdpater
        // Le pasamos los resources a los views tienen asociados
        holder.bind(item, clickListener)
    }

    class ViewHolder
    private constructor(val binding: ListItemSleepNightBinding) : RecyclerView.ViewHolder(binding.root) {

        // Con Binding
        //val sleepLength: TextView = binding.sleepLength
        val quality: TextView = binding.qualityString
        val qualityImage: ImageView = binding.qualityImage

        // Hace el bindin de manera manual
        fun bind(
            item: SleepNight,
            clickListener: SleepNightListener
        ) {

            // Esto es por si queremos hacerlo por código
            /* val res = itemView.context.resources
             sleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
             quality.text = convertNumericQualityToString(item.sleepQuality, res)
             qualityImage.setImageResource(
                 when (item.sleepQuality) {
                     0 -> R.drawable.ic_sleep_0
                     1 -> R.drawable.ic_sleep_1
                     2 -> R.drawable.ic_sleep_2
                     3 -> R.drawable.ic_sleep_3
                     4 -> R.drawable.ic_sleep_4
                     5 -> R.drawable.ic_sleep_5
                     else -> R.drawable.ic_sleep_active
                 }
             )*/
            // Con Binding en las vistas
            binding.sleep = item
            binding.executePendingBindings()
            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding = ListItemSleepNightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

// De esta manera vamos a mejorar nuestro código para saber qué elementos pintar
class SleepNightDiffCallback : DiffUtil.ItemCallback<SleepNight>() {
    override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        // son el mismo item si tienen la misma id
        return oldItem.nightId == newItem.nightId
    }

    override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        // Son el mismo contenido, si son iguales (equals)
        return oldItem == newItem
    }
}

// Para un evento de click en un item de la lista
class SleepNightListener(val clickListener: (sleepId: Long) -> Unit) {
    fun onClick(night: SleepNight) = clickListener(night.nightId)
}