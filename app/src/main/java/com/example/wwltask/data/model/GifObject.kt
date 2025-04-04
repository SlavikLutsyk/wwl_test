package com.example.wwltask.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GifObject(
    val images: Images
): Parcelable
