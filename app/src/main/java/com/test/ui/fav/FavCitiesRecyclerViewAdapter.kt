package com.test.ui.fav

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.databinding.FragmentFavCitiesItemBinding
import com.test.domain.model.model.FavCityModel

class FavCitiesRecyclerViewAdapter(var context: Context, var citySelected:(FavCityModel)-> Unit) : ListAdapter<FavCityModel, RecyclerView.ViewHolder>(
    DiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderListItem(FragmentFavCitiesItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false))
    }
//WHEN ITEM IS CLICKED DOES SHIT
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    val item = getItem(position)
    when(holder){
        is ViewHolderListItem -> {
            holder.cityNameTextView.text = item.name
            holder.temperatureTextView.text = item.temperature.toString()
        }
    }

    }

    inner class ViewHolderListItem(binding: FragmentFavCitiesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val cityNameTextView: TextView = binding.textViewCityName
        val temperatureTextView: TextView = binding.textViewTemperature

        override fun toString(): String {
            return super.toString() + " '" + "contentView.text" + "'"
        }
    }



//ARE THIS BASIC RECYCLER METHODS?
class DiffCallback : DiffUtil.ItemCallback<FavCityModel>() {
    override fun areItemsTheSame(oldItem: FavCityModel, newItem: FavCityModel): Boolean {
        return oldItem.id == oldItem.id
    }

    override fun areContentsTheSame(oldItem: FavCityModel, newItem: FavCityModel): Boolean {
        return oldItem.toString() == newItem.toString()
    }
}

}