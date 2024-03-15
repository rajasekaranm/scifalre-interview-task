package  com.scifalre.chat.data.network.api

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AppApi {
    @FormUrlEncoded
    @POST(ApiConstants.API_TEST)
    suspend fun testAPI(
        @Field("email") email: String, @Field("password") password: String
    ): Response<Unit>


}

object ApiConstants {
   const val API_TEST="/api/test"
}