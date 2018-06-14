package com.lemnistech.weatherapp.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.lemnistech.weatherapp.R
import com.lemnistech.weatherapp.Request
import com.lemnistech.weatherapp.adapters.ForecastRecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync

class MainActivity : AppCompatActivity(), ForecastRecyclerAdapter.OnForecastClickListener {

    private val url = "http://api.openweathermap.org/data/2.5/forecast/daily?" +
            "APPID=15646a06818f61f7b8d7823ca833e1ce&q=94043&mode=json&units=metric&cnt=7"

    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17", "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17", "Thurs 6/26 - Rainy - 18/11",
            "Fri 6/27 - Foggy - 21/10", "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
            "Sun 6/29 - Sunny - 20/7"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecastRecyclerView.layoutManager = LinearLayoutManager(this)
        forecastRecyclerView.adapter = ForecastRecyclerAdapter(this, items, this)
        forecastRecyclerView.setHasFixedSize(true)

        doAsync {
            Request(url).getForecastJson()
        }
    }

    override fun onForecastClick(position: Int) {
        Toast.makeText(this, items.get(position), Toast.LENGTH_SHORT).show()
    }
}
