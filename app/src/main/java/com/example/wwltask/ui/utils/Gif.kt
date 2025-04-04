package com.example.wwltask.ui.utils

import android.os.Build.VERSION.SDK_INT
import coil.size.Size
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest

@Composable
fun GifImage(
    gifUrl: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(gifUrl)
            .size(Size.ORIGINAL)
            .build(),
        imageLoader = imageLoader
    )

    Box(contentAlignment = Alignment.Center){
        if (painter.state is coil.compose.AsyncImagePainter.State.Loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(48.dp)
            )
        }

        Image(
            painter = painter,
            contentDescription = null,
            modifier = modifier.fillMaxWidth()
        )
    }
}