package ru.ponomarenko.gbkotlinforandroid.ui.main

import ru.ponomarenko.gbkotlinforandroid.model.Weather


sealed class AppState {
    data class Success(val weatherData: Weather) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
