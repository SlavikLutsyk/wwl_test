package com.example.wwltask.data.api

import com.example.wwltask.Constants
import com.example.wwltask.data.model.GiftResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GifAPI {
    @GET("gifs/trending")
    suspend fun getGifResponse(
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): GiftResponse
}