package com.example.wwltask.ui.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.wwltask.ui.utils.GifImage


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailListScreen(url: String, onClick: ()->Unit){
    Scaffold(
        topBar = {
            TopAppBar(
                title = @Composable{
                    Spacer(modifier = Modifier.width(80.dp))
                    Text(text = "Gif",
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = @Composable{
                    IconButton(onClick = { onClick() }) {
                        Icon(Icons.Default.ArrowBack,"")
                    }
                }
            )
        }, content = {
        })
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GifImage(url,
            Modifier.fillMaxSize())
    }
}