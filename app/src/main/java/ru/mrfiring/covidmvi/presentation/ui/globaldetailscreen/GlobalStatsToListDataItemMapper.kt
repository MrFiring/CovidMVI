package ru.mrfiring.covidmvi.presentation.ui.globaldetailscreen

import ru.mrfiring.covidmvi.R
import ru.mrfiring.covidmvi.domain.DomainGlobalStats
import ru.mrfiring.covidmvi.presentation.ui.reusable.detailstats.StatsListDataItem

internal fun DomainGlobalStats.asDataItemList(): List<StatsListDataItem>{
    val items = mutableListOf<StatsListDataItem>()

    //Cases
    items.add(
        StatsListDataItem.Detailed.Full(
            titleRes = R.string.cases,
            titleValue = cases.toString(),
            todayValue = todayCases.toString(),
            perMillionValue = casesPerOneMillion.toString()
        )
    )

    //Recovered
    items.add(
        StatsListDataItem.Detailed.Full(
            titleRes = R.string.recovered,
            titleValue = recovered.toString(),
            todayValue = todayRecovered.toString(),
            perMillionValue = recoveredPerOneMillion.toString()
        )
    )

    //Deaths
    items.add(
        StatsListDataItem.Detailed.Full(
            titleRes = R.string.deaths,
            titleValue = deaths.toString(),
            todayValue = todayDeaths.toString(),
            perMillionValue = deathsPerOneMillion.toString()
        )
    )

    //Tests
    items.add(
        StatsListDataItem.Detailed.WithNoToday(
            titleRes = R.string.tests,
            titleValue = tests.toString(),
            perMillionValue = testsPerOneMillion.toString()
        )
    )

    items.add(
        StatsListDataItem.Simple(
            titleRes = R.string.affected_countries,
            titleValue = affectedCountries.toString()
        )
    )

    return items
}