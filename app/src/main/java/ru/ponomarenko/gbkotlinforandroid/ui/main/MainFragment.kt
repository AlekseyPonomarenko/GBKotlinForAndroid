package ru.ponomarenko.gbkotlinforandroid.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import ru.ponomarenko.gbkotlinforandroid.R
import ru.ponomarenko.gbkotlinforandroid.databinding.MainFragmentBinding
import ru.ponomarenko.gbkotlinforandroid.model.Weather

class MainFragment : Fragment() {

    private lateinit var _binding: MainFragmentBinding

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = MainFragmentBinding.inflate(inflater, container, false)
        val view = _binding.root
        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.getLiveData().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        viewModel.getWeather()
    }

    private fun renderData(appState: AppState) {
        val loadingLayout = _binding.loadingLayout
        when (appState) {
            is AppState.Success -> {
                val weatherData = appState.weatherData
                loadingLayout.visibility = View.GONE
                setData(weatherData)

            }
            is AppState.Loading -> {
                loadingLayout.visibility = View.VISIBLE
            }

            is AppState.Error -> {
                loadingLayout.visibility = View.GONE
                Snackbar
                    .make(_binding.mainView, "Error", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload") { viewModel.getWeather() }
                    .show()
            }
        }


    }

    private fun setData(weatherData: Weather) {
        _binding.cityName.text = weatherData.city.city
        _binding.cityCoordinates.text = String.format(
            getString(R.string.city_coordinates),
            weatherData.city.lat.toString(),
            weatherData.city.lon.toString()
        )
        _binding.temperatureValue.text = weatherData.temperature.toString()
        _binding.feelsLikeValue.text = weatherData.feelsLike.toString()

    }

}