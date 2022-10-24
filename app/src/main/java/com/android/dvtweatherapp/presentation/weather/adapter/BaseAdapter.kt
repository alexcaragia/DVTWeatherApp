package com.android.dvtweatherapp.presentation.weather.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.android.dvtweatherapp.domain.weather.ForecastDisplayData

abstract class ForecastAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {
    private val items = mutableListOf<T>()

    abstract fun update(newItems: List<ForecastDisplayData>)

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(listItem: T)
}
