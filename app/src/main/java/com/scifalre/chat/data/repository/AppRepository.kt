package com.scifalre.chat.data.repository

import android.content.Context
import android.util.Base64
import com.scifalre.chat.data.model.ChatMessage
import com.scifalre.chat.data.preference.PreferenceManager
import com.scifalre.chat.data.preference.PreferenceManagerImpl
import com.scifalre.chat.utils.livedata.SingleLiveData
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

interface AppRepository {
    val messageLiveData: SingleLiveData<Pair<String, Any>>

    fun addMessage(message: String)
    fun getMessage()


}

class AppRepositoryImpl(context: Context) : AppRepository {

    private val cipher = Cipher.getInstance("AES/CTR/NoPadding")
    private val secretKeySpec = SecretKeySpec("Your16ByteAESKey".toByteArray(), "AES")
    private val ivParameterSpec = IvParameterSpec("Your16ByteIVHere".toByteArray())

    private val _messageLiveData = SingleLiveData<Pair<String, Any>>()
    private val preferenceManager: PreferenceManager

    override val messageLiveData: SingleLiveData<Pair<String, Any>>
        get() = _messageLiveData

    init {
        preferenceManager = PreferenceManagerImpl(context)
    }

    override fun addMessage(message: String) {
        val encryptText = encryptAES(message)
        preferenceManager.addMessage(ChatMessage(encryptText))
        getMessage()

    }

    override fun getMessage() {
        messageLiveData.postValue(Pair("MessageList",preferenceManager.getMessage().map { ChatMessage(decryptAES(it)) }.toList()))
    }

    private fun encryptAES(plainText: String): String {

        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec)
        val encryptedBytes = cipher.doFinal(plainText.toByteArray())
        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT)
    }

    private fun decryptAES(encryptedText: String): String {

        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec)
        val encryptedBytes = Base64.decode(encryptedText, Base64.DEFAULT)
        val decryptedBytes = cipher.doFinal(encryptedBytes)
        return String(decryptedBytes)
    }

}