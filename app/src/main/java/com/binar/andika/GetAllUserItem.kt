package com.binar.andika


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GetAllUserItem(
    @SerializedName("address")
    val address: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("pass")
    val pass: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("umur")
    val umur: String,
    @SerializedName("username")
    val username: String
):Parcelable