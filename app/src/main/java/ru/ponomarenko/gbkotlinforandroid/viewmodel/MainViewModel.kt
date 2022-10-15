package ru.ponomarenko.gbkotlinforandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.ponomarenko.gbkotlinforandroid.viewmodel.AppState
import ru.ponomarenko.gbkotlinforandroid.model.Repository
import ru.ponomarenko.gbkotlinforandroid.model.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: Repository = RepositoryImpl()


) : ViewModel() {

    fun getLiveData() = liveDataToObserve

    fun getWeatherFromLocalSourceRus()      = getDataFromLocalSource(isRussian = true)
    fun getWeatherFromLocalSourceWorld()    = getDataFromLocalSource(isRussian = false)
    fun getWeatherFromRemoteSource()        = getDataFromLocalSource(isRussian = true)

    private fun getDataFromLocalSource(isRussian: Boolean) {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(1000)
            liveDataToObserve.postValue(
                AppState.Success(
                    if (isRussian)
                        repositoryImpl.getWeatherFromLocalStorageRus() else
                        repositoryImpl.getWeatherFromLocalStorageWorld()
                )
            )
        }.start()
    }

}