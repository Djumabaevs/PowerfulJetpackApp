package com.djumabaevs.powerfuljetpackapp.business.datasource.network.main

import retrofit2.http.*

interface JetpackAppMainService {

    @GET("account/properties")
    suspend fun getAccount(
        @Header("Authorization") authorization: String
    ): AccountDto

    @PUT("account/properties/update")
    @FormUrlEncoded
    suspend fun updateAccount(
        @Header("Authorization") authorization: String,
        @Field("email") email: String,
        @Field("username") username: String
    ): GenericResponse

    @PUT("account/change_password/")
    @FormUrlEncoded
    suspend fun updatePassword(
        @Header("Authorization") authorization: String,
        @Field("old_password") currentPassword: String,
        @Field("new_password") newPassword: String,
        @Field("confirm_new_password") confirmNewPassword: String
    ): GenericResponse
}