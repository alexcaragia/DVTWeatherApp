package com.android.dvtweatherapp.presentation.weather.adapter

import androidx.recyclerview.widget.DiffUtil
import com.android.dvtweatherapp.domain.weather.ForecastDisplayData

class ForecastAdapterDiffUtilCallback(
    private val oldList: List<ForecastDisplayData>,
    private val newList: List<ForecastDisplayData>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
