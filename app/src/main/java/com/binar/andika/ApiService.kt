package com.binar.andika

import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("user")
    fun getUser() : Call<List<GetAllUserItem>>

    @POST("user")
    @FormUrlEncoded
    fun Register(
        @Field("name") name: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("address") address: String,
        @Field("umur") umur: String,
        @Field("image") image: String,

        ): Call<GetAllUserItem>

}