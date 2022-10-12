package ru.ponomarenko.gbkotlinforandroid.view.weatherlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.ponomarenko.gbkotlinforandroid.viewmodel.AppState
import java.lang.Thread.sleep

class WeatherListViewModel(val liveData:MutableLiveData<AppState> = MutableLiveData<AppState>()) : ViewModel() {

    fun sentRequest(){
        liveData.postValue(AppState.Loading)


        Thread{
            sleep(2000L)
            liveData.postValue(AppState.Success(Any()))
        }.start()
    }
}