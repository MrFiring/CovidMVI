package ru.mrfiring.covidmvi.presentation.ui.reusable

sealed class StatsListDataItem {
    abstract val titleRes: Int
    abstract val titleValue: String

    data class Simple(
        override val titleRes: Int,
        override val titleValue: String
    ) : StatsListDataItem()

    sealed class Detailed : StatsListDataItem() {

        data class Full(
            override val titleRes: Int,
            override val titleValue: String,
            val todayValue: String,
            val perMillionValue: String,
        ) : Detailed()

        data class WithNoToday(
            override val titleRes: Int,
            override val titleValue: String,
            val perMillionValue: String
        ) : Detailed()
    }
}