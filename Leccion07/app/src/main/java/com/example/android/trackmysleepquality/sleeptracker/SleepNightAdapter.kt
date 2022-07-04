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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private val ITEM_VIEW_TYPE_HEADER = 0
private val ITEM_VIEW_TYPE_ITEM = 1

// ListAdpater es más eficiente que un RecyclerViewAdapter
class SleepNightAdapter(private val myListeners: SleepNightAdaptorListeners) :
    ListAdapter<DataItem, RecyclerView.ViewHolder>(SleepNightAdaptorItemsDiffCallback()) {

    // para corutinas
    private val adapterScope = CoroutineScope(Dispatchers.Default)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> HeaderTextViewHolder.from(parent)
            // si tenemos mushos eventos es mejor pasarlos por aquí y no en el onBind
            ITEM_VIEW_TYPE_ITEM -> SleepNightItemViewHolder.from(parent, myListeners)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    fun addHeaderAndSubmitList(list: List<SleepNight>?) {
        /*
         launch a coroutine in the adapterScope to manipulate the list.
         Then switch to the Dispatchers.Main context to submit the list, as shown in the code below.
         */
        adapterScope.launch {
            val items = when (list) {
                null -> listOf(DataItem.Header)
                else -> listOf(DataItem.Header) + list.map { DataItem.SleepNightItem(it) }
            }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SleepNightItemViewHolder -> {
                // Ya vienen implementada en ListAdpater
                val nightItem = getItem(position) as DataItem.SleepNightItem
                // Le pasamos los resources a los views tienen asociados
                /*holder.itemView.setOnClickListener {
                    onItemClicked(nightItem.sleepNight)
                }*/
                // Esta parte no es necesaria
                //holder.bind(nightItem.sleepNight, onItemClicked)
                // Le pasamos los eventos a los views tienen asociados
                //holder.bind(nightItem.sleepNight, myListeners)
                // Esta opción es para la solcion 2 de eventos
                // Teoriccamente es mejor hacerlo en el create que en el onbind
                holder.bind(nightItem.sleepNight)
            }
        }
    }


    class HeaderTextViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup): HeaderTextViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return HeaderTextViewHolder(binding.root)
            }
        }
    }

    class SleepNightItemViewHolder
    private constructor(
        val binding: ListItemSleepNightBinding,
        val myListeners: SleepNightAdaptorListeners
    ) : RecyclerView.ViewHolder(binding.root) {


        // Con Binding
        //val sleepLength: TextView = binding.sleepLength
        val quality: TextView = binding.qualityString
        val qualityImage: ImageView = binding.qualityImage

        // Hace el bindin de manera manual
        fun bind(
            item: SleepNight,
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
            // Definimos los eventos
            binding.root.setOnClickListener { myListeners.onClick(item) }
            binding.root.setOnLongClickListener { myListeners.onLongClick(item) }
            // binding.clickListener = sleepNightListeners // Si lo queremos en la vista
        }

        companion object {
            fun from(parent: ViewGroup, myListeners: SleepNightAdaptorListeners): SleepNightItemViewHolder {
                val binding = ListItemSleepNightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return SleepNightItemViewHolder(binding, myListeners)
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
class SleepNightAdaptorItemsDiffCallback : DiffUtil.ItemCallback<DataItem>() {
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

sealed class DataItem {
    abstract val id: Long

    data class SleepNightItem(val sleepNight: SleepNight) : DataItem() {
        override val id = sleepNight.nightId
    }

    object Header : DataItem() {
        override val id = Long.MIN_VALUE
    }

}

// Para un evento de click en un item de la lista, esto esta bien si tenemos muchos
// eventos y queremos una clase que los encapsule
class SleepNightAdaptorListeners(
    val onClick: (sleepNight: SleepNight) -> Unit,
    val onLongClick: (sleepNight: SleepNight) -> Boolean
)
