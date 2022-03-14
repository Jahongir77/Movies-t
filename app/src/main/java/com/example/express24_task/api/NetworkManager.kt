package com.example.express24_task.api

import com.example.express24_task.BuildConfig
import com.example.express24_task.utils.Constans
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkManager{
    var retrofit: Retrofit? = null
    var api: Api? = null
    fun getApiService(): Api {
        if (api == null) {
            val client = buildClient()
            retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(Constans.BASE_URL)
                .build()
            api = retrofit!!.create(Api::class.java)
        }
        return api!!
    }
    private fun buildClient(): OkHttpClient {

        val client = OkHttpClient.Builder()

        val logging = HttpLoggingInterceptor().apply{
            level =
                if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE
        }
        client.addInterceptor(logging)
        client.readTimeout(60L, TimeUnit.SECONDS)
        client.writeTimeout(60L, TimeUnit.SECONDS)
        return client.build()
    }
}
