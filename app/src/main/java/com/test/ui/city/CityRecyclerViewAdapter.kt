package com.test.ui.city

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.base.flagLoad
import com.test.databinding.FragmentCityListItemBinding
import com.test.domain.model.entity.CityEntity
import com.test.domain.model.model.CityListModel

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class CityRecyclerViewAdapter(var context: Context,var citySelected:(CityListModel)-> Unit) : ListAdapter<CityListModel, RecyclerView.ViewHolder>(DiffCallback()) {

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
                holder.countryFlagImageView.flagLoad(context, item.countryCode)
                holder.cityNameTextView.text = item.name
                holder.containerView.setOnClickListener {
                    citySelected(item)
                }
                holder.imageViewIsFavourite.visibility = if (item.isFavourite) View.VISIBLE else View.GONE
            }
        }
    }


    //override fun getItemCount(): Int = values.size

    inner class ViewHolderListItem(binding: FragmentCityListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val cityNameTextView: TextView = binding.cityName
        val countryFlagImageView: ImageView = binding.imageViewFlag
        val containerView: View = binding.containerCity
        val imageViewIsFavourite: ImageView = binding.imageViewIsFavourite

        override fun toString(): String {
            return super.toString() + " '" + "contentView.text" + "'"
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<CityListModel>() {
        override fun areItemsTheSame(oldItem: CityListModel, newItem: CityListModel): Boolean {
            return oldItem.id == oldItem.id
        }

        override fun areContentsTheSame(oldItem: CityListModel, newItem: CityListModel): Boolean {
            return oldItem.toString() == newItem.toString()
        }
    }

}