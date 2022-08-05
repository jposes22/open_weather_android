package com.test.base

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.test.R



    fun ImageView.flagLoad(context: Context,countryCode: String?){
//TODO: Select images to error
        Glide.with(context)
            .load("https://countryflagsapi.com/png/${countryCode}").placeholder(
                com.google.android.material.R.drawable.mtrl_ic_error).error(R.drawable.ic_launcher_foreground)
            .into(this);
    }
