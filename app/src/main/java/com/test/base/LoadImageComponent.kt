package com.test.base

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.test.R


fun ImageView.flagLoad(context: Context,countryCode: String?){
    Glide.with(context)
        .load("https://countryflagsapi.com/png/${countryCode}")
        .placeholder(R.drawable.ic_image_placeholder)
        .error(R.drawable.ic_image_error)
        .circleCrop()
        .into(this)
}

fun ImageView.iconWeatherLoad(context: Context,code: String?){
    Glide.with(context)
        .load("https://openweathermap.org/img/wn/${code}@2x.png")
        .placeholder(R.drawable.ic_image_placeholder)
        .error(R.drawable.ic_image_error)
        .circleCrop()
        .into(this)
}
