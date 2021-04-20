package ru.mrfiring.covidmvi.presentation.ui.reusable.detailstats

import ru.mrfiring.covidmvi.domain.DomainGlobalStats

internal fun DomainGlobalStats.asDataItemList(): List<StatsListDataItem>{
    val items = mutableListOf<StatsListDataItem>()

    //Cases
    items.add(
        StatsListDataItem.Detailed.Full(
            titleRes = 0,
            titleValue = cases.toString(),
            todayValue = todayCases.toString(),
            perMillionValue = casesPerOneMillion.toString()
        )
    )

    //Recovered
    items.add(
        StatsListDataItem.Detailed.Full(
            titleRes = 0,
            titleValue = recovered.toString(),
            todayValue = todayRecovered.toString(),
            perMillionValue = recoveredPerOneMillion.toString()
        )
    )

    //Deaths
    items.add(
        StatsListDataItem.Detailed.Full(
            titleRes = 0,
            titleValue = deaths.toString(),
            todayValue = todayDeaths.toString(),
            perMillionValue = deathsPerOneMillion.toString()
        )
    )

    //Tests
    items.add(
        StatsListDataItem.Detailed.WithNoToday(
            titleRes = 0,
            titleValue = tests.toString(),
            perMillionValue = testsPerOneMillion.toString()
        )
    )

    items.add(
        StatsListDataItem.Simple(
            titleRes = 0,
            titleValue = affectedCountries.toString()
        )
    )

    return items
}