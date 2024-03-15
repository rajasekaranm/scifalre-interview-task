package com.scifalre.chat.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatMessage(val message: String):Parcelable
