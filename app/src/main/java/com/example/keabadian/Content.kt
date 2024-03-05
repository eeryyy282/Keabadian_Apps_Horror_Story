package com.example.keabadian

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Content (
    val titleContent: String,
    val storyContent: String,
    val storyPhoto: Int,
    val dateContent: String,
    val locationContent: String
): Parcelable
