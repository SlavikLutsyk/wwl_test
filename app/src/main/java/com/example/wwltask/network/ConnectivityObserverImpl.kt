package com.example.wwltask.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ConnectivityObserverImpl(context: Context) : ConnectivityObserver {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val _networkStatus = MutableStateFlow(ConnectivityObserver.Status.UNAVAILABLE)
    override fun getNetworkStatus() = _networkStatus.asStateFlow()

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            _networkStatus.value = ConnectivityObserver.Status.AVAILABLE
        }

        override fun onLost(network: Network) {
            _networkStatus.value = ConnectivityObserver.Status.UNAVAILABLE
        }

        override fun onLosing(network: Network, maxMsToLive: Int) {
            _networkStatus.value = ConnectivityObserver.Status.UNAVAILABLE
        }

        override fun onUnavailable() {
            _networkStatus.value = ConnectivityObserver.Status.UNAVAILABLE
        }
    }

    init {
        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager.registerNetworkCallback(request, networkCallback)
    }
}