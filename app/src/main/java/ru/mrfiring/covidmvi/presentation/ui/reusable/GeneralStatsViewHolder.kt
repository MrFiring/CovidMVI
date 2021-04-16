package ru.mrfiring.covidmvi.presentation.ui.reusable

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.mrfiring.covidmvi.databinding.ItemListMainBinding
import ru.mrfiring.covidmvi.domain.DomainGeneralStats

class GeneralStatsViewHolder(
    private val binding: ItemListMainBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: DomainGeneralStats, onClick: (DomainGeneralStats) -> Unit){
        with(binding){
            itemMainName.text = item.name
            itemMainTodayCases.text = item.todayCases.toString()
            itemMainTotalCases.text = item.cases.toString()

            itemMainTotalRecovered.text = item.recovered.toString()
            itemMainTodayRecovered.text = item.todayRecovered.toString()

            itemMainTotalDeaths.text = item.deaths.toString()
            itemMainTodayDeaths.text = item.todayDeaths.toString()

            root.setOnClickListener { onClick(item) }
        }
    }

    companion object {
        fun from(parent: ViewGroup): GeneralStatsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemListMainBinding.inflate(layoutInflater, parent, false)
            return GeneralStatsViewHolder(binding)
        }
    }
}