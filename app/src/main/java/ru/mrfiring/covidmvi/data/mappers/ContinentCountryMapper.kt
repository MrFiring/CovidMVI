package ru.mrfiring.covidmvi.data.mappers

import ru.mrfiring.covidmvi.data.database.DatabaseContinentCountry

fun List<String>.asDatabaseContinentCountryList(
    continent: String
): List<DatabaseContinentCountry> =
    map {
        DatabaseContinentCountry(
            countryName = it,
            continentName = continent
        )
    }

fun List<DatabaseContinentCountry>.asStringArray(
): List<String> = map { it.countryName }