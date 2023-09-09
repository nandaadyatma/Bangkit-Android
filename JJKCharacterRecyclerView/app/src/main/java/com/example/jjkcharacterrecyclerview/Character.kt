package com.example.jjkcharacterrecyclerview

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Character(
    val name: String,
    val description: String,
    val photo: Int,
    val gender: String,
    val birthday: String,
    val ability: String
) : Parcelable
