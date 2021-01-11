package com.devdossantos.roomtest.api

import com.devdossantos.roomtest.model.CharacterDataWrapper
import com.devdossantos.roomtest.utils.NetworkUtils
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterEndpoint {

    @GET("/v1/public/characters")
    suspend fun getCharacter(
        @Query("id") id: Int,
        @Query("ts") ts: String?,
        @Query("hash") hash: String?,
        @Query("apikey") apikey: String?
    ): CharacterDataWrapper

    companion object {
        val Endpoint: CharacterEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(CharacterEndpoint::class.java)
        }
    }
}