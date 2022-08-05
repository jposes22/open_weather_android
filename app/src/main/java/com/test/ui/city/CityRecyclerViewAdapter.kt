package com.test.ui.city

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.databinding.FragmentCityListItemBinding
import com.test.domain.model.entity.CityEntity

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class CityRecyclerViewAdapter() : ListAdapter<CityEntity, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ViewHolderListItem(
            FragmentCityListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when(holder){
            is ViewHolderListItem -> {
                holder.cityName.text = item.name
                holder.countryName.text = item.country
            }
        }
    }


    //override fun getItemCount(): Int = values.size

    inner class ViewHolderListItem(binding: FragmentCityListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val cityName: TextView = binding.cityName
        val countryName: TextView = binding.textViewCountry

        override fun toString(): String {
            return super.toString() + " '" + "contentView.text" + "'"
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<CityEntity>() {
        override fun areItemsTheSame(oldItem: CityEntity, newItem: CityEntity): Boolean {
            return oldItem.id == oldItem.id
        }

        override fun areContentsTheSame(oldItem: CityEntity, newItem: CityEntity): Boolean {
            return oldItem.toString() == newItem.toString()
        }
    }

}