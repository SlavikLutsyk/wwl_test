package com.example.wwltask.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.wwltask.data.model.GifObject
import com.example.wwltask.ui.utils.GifImage
import com.example.wwltask.viewmodel.GifViewModel

@Composable
fun MainScreen(
    gifState: GifViewModel.GifState,
    onClick: (String)->Unit
){
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            gifState.loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            gifState.error != null ->{
                Toast.makeText(LocalContext.current, gifState.error, Toast.LENGTH_LONG).show()
            }
            else-> GifsView(gifState.list, onClick)
        }
    }
}


@Composable
fun GifsView(gifsList: List<GifObject>, onClick: (String)->Unit){

    val scroll = rememberScrollState()
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .verticalScroll(scroll),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        gifsList.forEach {
                item ->
            val gif = item.images.original.url
            GifImage(
                gif,
                Modifier.clickable{ onClick(gif) }
                    .height(150.dp)
            )
        }
    }
}