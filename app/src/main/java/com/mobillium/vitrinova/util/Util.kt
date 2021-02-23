package com.mobillium.vitrinova.util

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mobillium.vitrinova.R

fun ImageView.downloadFromUrl(url: String?) {


    Glide.with(context)

        .load(url)
        .error(R.drawable.ic_launcher_background)
        .into(this)


}