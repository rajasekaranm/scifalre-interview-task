package  com.scifalre.chat.data.network.manager

import  com.scifalre.chat.BuildConfig
import  com.scifalre.chat.data.network.api.AppApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkManager {

    private val okhttpClient = OkHttpClient.Builder()
        .connectTimeout(3, TimeUnit.MINUTES)
        .readTimeout(3, TimeUnit.MINUTES)
        .retryOnConnectionFailure(false)
        .addNetworkInterceptor(HttpLoggingInterceptor().apply {
            level= HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private fun getInstance(): Retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okhttpClient)
        .build()

    fun getApiInstance(): AppApi = getInstance().create(AppApi::class.java)

}