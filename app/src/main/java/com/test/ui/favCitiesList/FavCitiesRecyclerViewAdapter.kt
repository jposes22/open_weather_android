<<<<<<< Updated upstream:app/src/main/java/com/test/ui/fav/FavCitiesRecyclerViewAdapter.kt
package com.test.ui.fav
=======
package com.test.ui.favCitiesList
>>>>>>> Stashed changes:app/src/main/java/com/test/ui/favCitiesList/FavCitiesRecyclerViewAdapter.kt

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.databinding.FragmentFavCitiesItemBinding
import com.test.domain.model.model.FavCityModel

<<<<<<< Updated upstream:app/src/main/java/com/test/ui/fav/FavCitiesRecyclerViewAdapter.kt
class FavCitiesRecyclerViewAdapter(var context: Context, var citySelected:(FavCityModel)-> Unit) : ListAdapter<FavCityModel, RecyclerView.ViewHolder>(
    DiffCallback()
) {

=======
class FavCitiesRecyclerViewAdapter(var context: Context, var citySelected:(CityListModel)-> Unit) : ListAdapter<CityListModel, RecyclerView.ViewHolder>(
    DiffCallback()
) {
//INFLATES VIEWHOLDER
>>>>>>> Stashed changes:app/src/main/java/com/test/ui/favCitiesList/FavCitiesRecyclerViewAdapter.kt
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderListItem(FragmentFavCitiesItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false))
    }
<<<<<<< Updated upstream:app/src/main/java/com/test/ui/fav/FavCitiesRecyclerViewAdapter.kt
    //WHEN ITEM IS CLICKED DOES SHIT
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when(holder){
            is ViewHolderListItem -> {
                holder.cityNameTextView.text = item.name
                holder.temperatureTextView.text = item.temperature.toString()
                holder.container.setOnClickListener {
                    citySelected(item)
                }
            }
        }

=======
//UPDATES RECYCLERVIEW CONTENT
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    val item = getItem(position)
    when(holder){
        is ViewHolderListItem -> {
//            SHOULD SEARCH BY ID OF FAVCITIESLIST AND SHOW THE NAME
            holder.cityNameTextView.text = item.name
            holder.temperatureTextView.text = "20º"
        }
    }
>>>>>>> Stashed changes:app/src/main/java/com/test/ui/favCitiesList/FavCitiesRecyclerViewAdapter.kt
    }
// CHARGES LIST ITEMS??? NOT SURE AT ALL
    inner class ViewHolderListItem(binding: FragmentFavCitiesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val cityNameTextView: TextView = binding.textViewCityName
        val temperatureTextView: TextView = binding.textViewTemperature
        val container:ConstraintLayout = binding.favCityContainer

        override fun toString(): String {
            return super.toString() + " '" + "contentView.text" + "'"
        }
    }



<<<<<<< Updated upstream:app/src/main/java/com/test/ui/fav/FavCitiesRecyclerViewAdapter.kt
    //ARE THIS BASIC RECYCLER METHODS?
    class DiffCallback : DiffUtil.ItemCallback<FavCityModel>() {
        override fun areItemsTheSame(oldItem: FavCityModel, newItem: FavCityModel): Boolean {
            return oldItem.id == oldItem.id
        }
=======
//CHECKS IF ITEMS ARE REPEATED WHILE INDEXING
class DiffCallback : DiffUtil.ItemCallback<CityListModel>() {

    override fun areItemsTheSame(oldItem: CityListModel, newItem: CityListModel): Boolean {
        return oldItem.id == oldItem.id
    }
>>>>>>> Stashed changes:app/src/main/java/com/test/ui/favCitiesList/FavCitiesRecyclerViewAdapter.kt

        override fun areContentsTheSame(oldItem: FavCityModel, newItem: FavCityModel): Boolean {
            return oldItem.toString() == newItem.toString()
        }
    }

}