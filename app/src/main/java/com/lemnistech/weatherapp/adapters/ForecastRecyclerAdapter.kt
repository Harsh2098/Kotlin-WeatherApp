package com.lemnistech.weatherapp.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lemnistech.weatherapp.R
import kotlinx.android.synthetic.main.forecast_list_item.view.*

class ForecastRecyclerAdapter(private val context: Context, private var forecastList: List<String>?, private var listener: OnForecastClickListener) : RecyclerView.Adapter<ForecastRecyclerAdapter.ForecastViewHolder>() {

    interface OnForecastClickListener {
        fun onForecastClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ForecastViewHolder(LayoutInflater.from(context).inflate(R.layout.forecast_list_item, parent, false))


    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.itemView.forecastWeatherTextView.text = forecastList?. get(position)
    }

    override fun getItemCount() = forecastList?. size ?: 0

    fun swapData(newList: List<String>) {
        forecastList = newList
        notifyDataSetChanged()
    }

    class ForecastViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView?.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            // listener.onForecastClick(adapterPosition)
        }
    }
}