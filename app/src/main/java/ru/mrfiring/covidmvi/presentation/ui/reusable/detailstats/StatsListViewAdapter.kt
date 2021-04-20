package ru.mrfiring.covidmvi.presentation.ui.reusable.detailstats

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

private const val VIEW_TYPE_SIMPLE = 0
private const val VIEW_TYPE_DETAILED = 1

class StatsListViewAdapter : BaseAdapter() {
    private var items: List<StatsListDataItem> = emptyList()

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): StatsListDataItem = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is StatsListDataItem.Simple -> VIEW_TYPE_SIMPLE

        is StatsListDataItem.Detailed -> VIEW_TYPE_DETAILED
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View =
        when (getItemViewType(position)) {
            VIEW_TYPE_SIMPLE -> {
                val holder = SimpleStatsViewHolder.from(requireNotNull(parent))
                holder.bind(getItem(position) as StatsListDataItem.Simple)
                holder.root
            }

            VIEW_TYPE_DETAILED -> {
                val holder = DetailedStatsViewHolder.from(requireNotNull(parent))
                holder.bind(getItem(position) as StatsListDataItem.Detailed)
                holder.root
            }

            else -> {
                throw TypeCastException("Unknown viewType at position: $position")
            }

        }


    fun submitList(items: List<StatsListDataItem>){
        this.items = items
        notifyDataSetChanged()
    }

}