package ru.mrfiring.covidmvi.presentation.ui.reusable.detailstats

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.mrfiring.covidmvi.databinding.ItemListSimpleStatsBinding

class SimpleStatsViewHolder(
    private val binding: ItemListSimpleStatsBinding
) {

    val root = binding.root

    fun bind(item: StatsListDataItem.Simple){
        with(binding){
            simpleTitle.text = root.resources.getString(item.titleRes)
            simpleTitleValue.text = item.titleValue
        }
    }

    companion object{
        fun from(parent: ViewGroup): SimpleStatsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemListSimpleStatsBinding.inflate(inflater, parent, false)
            return SimpleStatsViewHolder(binding)
        }
    }
}