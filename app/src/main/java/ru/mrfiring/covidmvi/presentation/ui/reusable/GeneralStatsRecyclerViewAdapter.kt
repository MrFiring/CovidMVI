package ru.mrfiring.covidmvi.presentation.ui.reusable

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.mrfiring.covidmvi.domain.DomainGeneralStats

class GeneralStatsRecyclerViewAdapter(
    private val onClick: (DomainGeneralStats) -> Unit
): ListAdapter<DomainGeneralStats, GeneralStatsViewHolder>(MainDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneralStatsViewHolder {
        return GeneralStatsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: GeneralStatsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onClick)
    }

    private class MainDiffUtilCallback: DiffUtil.ItemCallback<DomainGeneralStats>(){
        override fun areItemsTheSame(
            oldItem: DomainGeneralStats,
            newItem: DomainGeneralStats
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: DomainGeneralStats,
            newItem: DomainGeneralStats
        ): Boolean {
            return oldItem == newItem
        }
    }
}