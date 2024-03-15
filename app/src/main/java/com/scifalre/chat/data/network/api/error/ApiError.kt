package  com.scifalre.chat.data.network.api.error

import org.json.JSONObject
import java.lang.Exception

class ApiError(private val response: String) {
    fun errorMessage(): String {
        return try {
            JSONObject(response).optJSONObject("messages").optString("error")
        } catch (e: Exception) {
            "Oops! Something wrong try again !"
        }
    }
}