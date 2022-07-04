package com.example.android.trackmysleepquality.sleeptracker

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.HeaderBinding
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding

private val ITEM_VIEW_TYPE_HEADER = 0
private val ITEM_VIEW_TYPE_ITEM = 1

// ListAdpater es más eficiente que un RecyclerViewAdapter
class SleepNightAdapter(val clickListener: SleepNightListener) :
    ListAdapter<DataItem, RecyclerView.ViewHolder>(SleepNightDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> TextViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> ViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    fun addHeaderAndSubmitList(list: List<SleepNight>?) {
        val items = when (list) {
            null -> listOf(DataItem.Header)
            else -> listOf(DataItem.Header) + list.map { DataItem.SleepNightItem(it) }
        }
        submitList(items)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                // Ya vienen implementada en ListAdpater
                val nightItem = getItem(position) as DataItem.SleepNightItem
                // Le pasamos los resources a los views tienen asociados
                holder.bind(nightItem.sleepNight, clickListener)
            }
        }
    }


    class TextViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup): TextViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return TextViewHolder(binding.root)
            }
        }
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

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.SleepNightItem -> ITEM_VIEW_TYPE_ITEM
        }
    }
}

// De esta manera vamos a mejorar nuestro código para saber qué elementos pintar
class SleepNightDiffCallback : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        // son el mismo si tienen la misma id
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    // tiene el mismo contenido (equals)
    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }
}

// Para un evento de click en un item de la lista
class SleepNightListener(val clickListener: (sleepId: Long) -> Unit) {
    fun onClick(night: SleepNight) = clickListener(night.nightId)
}

sealed class DataItem {
    abstract val id: Long

    data class SleepNightItem(val sleepNight: SleepNight) : DataItem() {
        override val id = sleepNight.nightId
    }

    object Header : DataItem() {
        override val id = Long.MIN_VALUE
    }
}