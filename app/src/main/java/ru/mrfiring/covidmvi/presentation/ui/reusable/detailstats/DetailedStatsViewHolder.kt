package ru.mrfiring.covidmvi.presentation.ui.reusable.detailstats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.mrfiring.covidmvi.databinding.ItemListDetailedStatsBinding

class DetailedStatsViewHolder(
    private val binding: ItemListDetailedStatsBinding
) {
    val root = binding.root

    fun bind(item: StatsListDataItem.Detailed){
        binding.detailedTitle.text = binding.root.resources.getString(item.titleRes)
        binding.detailedTitleValue.text = item.titleValue

        when(item){
            is StatsListDataItem.Detailed.Full -> {
                with(binding){
                    detailedTodayText.visibility = View.VISIBLE
                    detailedTodayValue.visibility = View.VISIBLE

                    detailedTodayValue.text = item.todayValue
                    detailedMillionValue.text = item.perMillionValue
                }
            }

            is StatsListDataItem.Detailed.WithNoToday -> {
                with(binding){
                    detailedTodayText.visibility = View.GONE
                    detailedTodayValue.visibility = View.GONE

                    detailedMillionValue.text = item.perMillionValue
                }
            }
        }


    }

    companion object{

        fun from(parent: ViewGroup): DetailedStatsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemListDetailedStatsBinding.inflate(inflater, parent, false)
            return DetailedStatsViewHolder(binding)
        }

    }

}