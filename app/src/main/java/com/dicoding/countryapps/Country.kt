package com.dicoding.countryapps

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    val name: String,
    val photo: String,
    val description: String,
    val detail: String,
    val place: String,
    val detailPhoto: String,
    val stats: String,
) : Parcelable
