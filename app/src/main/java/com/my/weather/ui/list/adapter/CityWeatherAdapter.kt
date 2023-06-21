package com.my.weather.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.my.weather.R
import com.my.weather.data.CityWeather
import com.my.weather.databinding.ItemCityWeatherBinding
import com.my.weather.ui.list.CityWeatherListFragmentDirections
import com.my.weather.util.WeatherUtil.temperatureFromKelvinToCelsius


class CityWeatherAdapter(private var data: List<CityWeather>) :
    RecyclerView.Adapter<CityWeatherAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemCityWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val view = binding.root

        val tvTemp: TextView = binding.tvTemp
        val tvCityCountryName: TextView = binding.tvCityCountryName
        val tvWeatherCondition: TextView = binding.tvWeatherCondition
        val ivWeatherConditionIcon: ImageView = binding.ivWeatherIcon
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCityWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            tvTemp.text =
                holder.view.context.getString(
                    R.string.temp_format,
                    data[position].main.temp.temperatureFromKelvinToCelsius()
                )
            tvCityCountryName.text = view.context.getString(
                R.string.city_name_country_name,
                data[position].city.name,
                data[position].city.country
            )

            data[position].weather.firstOrNull()?.let {
                tvWeatherCondition.text = it.main ?: ""

                Glide.with(holder.view.context)
                    .load("http://openweathermap.org/img/wn/${it.icon ?: ""}@2x.png")
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(holder.ivWeatherConditionIcon)
            }

            view.setOnClickListener {
                it.findNavController()
                    .navigate(
                        CityWeatherListFragmentDirections.actionCityWeatherListFragmentToCityWeatherDetailsFragment(
                            cityId = data[position].city.id
                        )
                    )
            }
        }
    }

    override fun getItemCount() = data.size

    fun updateData(newData: List<CityWeather>) {
        data = newData
        notifyDataSetChanged()
    }
}