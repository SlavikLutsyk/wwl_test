package com.example.wwltask.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Images(
    @SerializedName("original")
    val original: OriginalGif,
    @SerializedName("preview_gif")
    val preview: PreviewGif
): Parcelable
