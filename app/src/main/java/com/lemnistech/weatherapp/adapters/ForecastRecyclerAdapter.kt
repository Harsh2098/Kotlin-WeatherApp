package com.lemnistech.weatherapp.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lemnistech.weatherapp.R
import com.lemnistech.weatherapp.data.Forecast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.forecast_list_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class ForecastRecyclerAdapter(private val context: Context, var forecastList: List<Forecast>?, var listener: OnForecastClickListener) : RecyclerView.Adapter<ForecastRecyclerAdapter.ForecastViewHolder>() {

    private val baseImageUrl: String = "http://openweathermap.org/img/w/"

    interface OnForecastClickListener {
        fun onForecastClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ForecastViewHolder(LayoutInflater.from(context).inflate(R.layout.forecast_list_item, parent, false))


    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {

        with(forecastList?.get(position)) {
            holder.itemView.forecastMainTextView.text = this?.weather?.get(0)?.main
            holder.itemView.forecastDescriptionTextView.text = getReadableDate(this?.dt)

            val minText = this?.temp?.min.toString() + " " + Typography.degree + "C"
            holder.itemView.minTemperatureTextView.text = minText

            val maxText = this?.temp?.max.toString() + " " + Typography.degree + "C"
            holder.itemView.maxTemperatureTextView.text = maxText

            Picasso.get().load(baseImageUrl + this?.weather?.get(0)?.icon + ".png").into(holder.itemView.weatherImageView)
        }
    }

    override fun getItemCount() = forecastList?. size ?: 0

    fun swapData(newList: List<Forecast>?) {
        forecastList = newList
        notifyDataSetChanged()
    }

    private fun getReadableDate(timeInMillis: Long?): String {
        val formatter = SimpleDateFormat("MMM dd, yyyy", Locale.US)
        return formatter.format(Date(timeInMillis!! * 1000))
    }

    class ForecastViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView?. setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            //listener.onForecastClick(adapterPosition)
        }
    }
}