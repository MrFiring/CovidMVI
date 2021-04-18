package ru.mrfiring.covidmvi.domain

enum class ResolutionType(val str: String) {
    TWO_WEEKS("14"),
    ONE_MONTH("31"),
    SIX_MONTHS("182"),
    ONE_YEAR("365"),
    ALL("all")
}