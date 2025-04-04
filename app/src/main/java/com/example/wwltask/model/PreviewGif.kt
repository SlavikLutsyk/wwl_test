package com.example.wwltask.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PreviewGif(
    val url: String
): Parcelable
