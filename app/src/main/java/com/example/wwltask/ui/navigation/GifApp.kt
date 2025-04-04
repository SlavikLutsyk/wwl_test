package com.example.wwltask.ui.navigation

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wwltask.network.ConnectivityObserver
import com.example.wwltask.network.ConnectivityObserverImpl
import com.example.wwltask.ui.screens.DetailListScreen
import com.example.wwltask.ui.screens.MainScreen
import com.example.wwltask.viewmodel.GifViewModel

@Composable
fun GifApp(
    navHostController:NavHostController
) {
    val context = LocalContext.current
    val gifViewModel: GifViewModel = viewModel()
    val gifState by remember { gifViewModel.gifState }
    val connectivityObserver = remember { ConnectivityObserverImpl(context) }
    val networkStatus by connectivityObserver.getNetworkStatus().collectAsState()
    if (networkStatus == ConnectivityObserver.Status.UNAVAILABLE)
        Toast.makeText(context, "No internet connection", Toast.LENGTH_LONG).show()
    else
        gifViewModel.fetchGifs()
    NavHost(navController = navHostController, startDestination = NavigationRoute.ListScreen.route ){
        composable(NavigationRoute.ListScreen.route){
            MainScreen(gifState){
                navHostController.currentBackStackEntry?.savedStateHandle?.set("gif", it)
                navHostController.navigate(NavigationRoute.DetailListScreen.route)
            }
        }
        composable(NavigationRoute.DetailListScreen.route){
            val gif = navHostController.previousBackStackEntry?.savedStateHandle?.
            get<String>("gif")?:""
            DetailListScreen(gif){
                navHostController.navigate(NavigationRoute.ListScreen.route)
            }
        }
    }
}