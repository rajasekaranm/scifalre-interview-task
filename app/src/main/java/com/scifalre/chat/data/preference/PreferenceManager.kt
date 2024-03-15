package  com.scifalre.chat.data.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.scifalre.chat.data.model.ChatMessage

interface PreferenceManager {

    fun addMessage(message: ChatMessage)
    fun getMessage(): List<String>
}

class PreferenceManagerImpl(context: Context) : PreferenceManager {


    var preference: SharedPreferences

    init {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        preference = EncryptedSharedPreferences.create(
            "secured_shared_preference",
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    override fun addMessage(message: ChatMessage) {
        val existingMessage = preference.getString(PreferenceConstants.MESSAGES, null)
        val updatedMessage =if (existingMessage == null) message.message else (existingMessage + " ||| " + message.message)
                preference.edit()
                    .putString(PreferenceConstants.MESSAGES,updatedMessage).apply()
    }

    override fun getMessage(): List<String> {
        val existingMessage = preference.getString(PreferenceConstants.MESSAGES, null)
        return existingMessage?.split(" ||| ")?.toList()?: mutableListOf<String>()
    }
}

object PreferenceConstants {
    const val MESSAGES = "messages"

}