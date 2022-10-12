package ru.ponomarenko.gbkotlinforandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.ponomarenko.gbkotlinforandroid.view.weatherlist.WeatherListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WeatherListFragment.newInstance())
                .commitNow()
        }
    }
}