package com.lemnistech.weatherapp.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.lemnistech.weatherapp.R
import com.lemnistech.weatherapp.Request
import com.lemnistech.weatherapp.adapters.ForecastRecyclerAdapter
import com.lemnistech.weatherapp.data.Forecast
import com.lemnistech.weatherapp.data.Result
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity(), ForecastRecyclerAdapter.OnForecastClickListener {

    private val url = "http://api.openweathermap.org/data/2.5/forecast/daily?" +
            "APPID=15646a06818f61f7b8d7823ca833e1ce&q=94043&mode=json&units=metric&cnt=7"

    private var forecastList: List<Forecast>? = null
    private var adapter: ForecastRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ForecastRecyclerAdapter(this, null, this)

        forecastRecyclerView.layoutManager = LinearLayoutManager(this)
        forecastRecyclerView.adapter = adapter
        forecastRecyclerView.setHasFixedSize(true)

        doAsync {
            val result = Request(url).getForecastResult()

            uiThread {
                forecastList = updateForecastUi(result)
                adapter?.swapData(forecastList)

                val text = result.city.name + ", "
                cityNameTextView.text = text

                countryNameTextView.text = result.city.country
            }
        }
    }

    private fun updateForecastUi(result: Result): List<Forecast>? {
        val list = mutableListOf<Forecast>()

        for (forecast in result.list) {
            list.add(Forecast(forecast.temp, forecast.weather, forecast.dt))
        }

        return list
    }

    override fun onForecastClick(position: Int) {
        Toast.makeText(this, forecastList?.get(position)?.weather?.get(0)?.main, Toast.LENGTH_SHORT).show()
    }
}
