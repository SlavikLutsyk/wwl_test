package com.example.wwltask.network

import kotlinx.coroutines.flow.StateFlow

interface ConnectivityObserver {
    fun getNetworkStatus(): StateFlow<Status>

    enum class Status {
        AVAILABLE, UNAVAILABLE
    }
}