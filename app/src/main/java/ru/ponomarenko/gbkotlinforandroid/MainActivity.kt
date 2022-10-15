package ru.ponomarenko.gbkotlinforandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.ponomarenko.gbkotlinforandroid.view.main.MainFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance()).commitNow()
        }
    }
}