package com.android.dvtweatherapp.presentation.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.android.dvtweatherapp.databinding.ItemForecastBinding
import com.android.dvtweatherapp.domain.weather.ForecastDisplayData

class DefaultForecastAdapter(
    private val inflater: LayoutInflater,
    private val itemsList: MutableList<ForecastDisplayData> = mutableListOf()
) : ForecastAdapter<ForecastDisplayData>() {
    override fun update(newItems: List<ForecastDisplayData>) {
        val diffResult =
            DiffUtil.calculateDiff(ForecastAdapterDiffUtilCallback(itemsList, newItems))
        itemsList.clear()
        itemsList.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ForecastDisplayData> {
        return ForecastItemViewHolder(ItemForecastBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ForecastDisplayData>, position: Int) {
        holder.bind(itemsList[position])
    }

    override fun getItemCount(): Int = itemsList.size
}

class ForecastItemViewHolder(private val binding: ItemForecastBinding) :
    BaseViewHolder<ForecastDisplayData>(binding.root) {
    override fun bind(listItem: ForecastDisplayData) {
        binding.forecastData = listItem
        binding.executePendingBindings()
    }
}
