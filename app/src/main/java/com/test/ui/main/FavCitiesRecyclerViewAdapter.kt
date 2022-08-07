package com.test.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.base.flagLoad
import com.test.databinding.FragmentCityListItemBinding
import com.test.domain.model.model.CityListModel
import com.test.ui.city.CityRecyclerViewAdapter

class FavCitiesRecyclerViewAdapter(var context: Context, var citySelected:(CityListModel)-> Unit) : ListAdapter<CityListModel, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderListItem(FragmentCityListItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false))
    }
//WHEN ITEM IS CLICKED DOES SHIT
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    val item = getItem(position)
    when(holder){
        is CityRecyclerViewAdapter.ViewHolderListItem -> {
            holder.countryFlagImageView.flagLoad(context, item.countryCode)
            holder.cityNameTextView.text = item.name
            holder.containerView.setOnClickListener {
                citySelected(item)
            }
            holder.imageViewIsFavourite.visibility = if (item.isFavourite) View.VISIBLE else View.GONE
        }
    }

    }



//ARE THIS BASIC RECYCLER METHODS?
class DiffCallback : DiffUtil.ItemCallback<CityListModel>() {
    override fun areItemsTheSame(oldItem: CityListModel, newItem: CityListModel): Boolean {
        return oldItem.id == oldItem.id
    }

    override fun areContentsTheSame(oldItem: CityListModel, newItem: CityListModel): Boolean {
        return oldItem.toString() == newItem.toString()
    }
}

}