package com.example.wwltask.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wwltask.Constants
import com.example.wwltask.data.api.GifAPI
import com.example.wwltask.data.model.GifObject
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GifViewModel() : ViewModel() {

    private val retrofit = Retrofit.Builder().
    addConverterFactory(GsonConverterFactory.create()).
    baseUrl(Constants.base_url).build()

    private val gifService = retrofit.create(GifAPI::class.java)

    val gifState = mutableStateOf(GifState())
        get() = field

    init {
        fetchMeals()
    }

    private fun fetchMeals(){
        viewModelScope.launch {
            try {
                val response = gifService.getGifResponse()
                gifState.value = gifState.value.copy(
                    loading = false,
                    list = response.data,
                    error = null
                )
            }
            catch (e:Exception){
                gifState.value = gifState.value.copy(
                    loading = false,
                    error = e.message
                )
            }
        }
    }

    data class GifState(
        val loading: Boolean = true,
        val list: List<GifObject> = emptyList(),
        val error: String? = null
    )
}