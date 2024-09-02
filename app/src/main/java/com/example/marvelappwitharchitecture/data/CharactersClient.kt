package com.example.marvelappwitharchitecture.data

import com.example.marvelappwitharchitecture.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.create
import java.math.BigInteger
import java.security.MessageDigest


object CharactersClient {

    private val okHttpClient = okhttp3.OkHttpClient.Builder()
        .addInterceptor(::apiKeyAsQuery)
        .build()


    val instance: CharactersService = Retrofit.Builder()
        .baseUrl("https://gateway.marvel.com")
        .client(okHttpClient)
        .build()
        .create<CharactersService>()

}

private fun apiKeyAsQuery(chain: Interceptor.Chain): Response {
    val originalRequest = chain.request()
    val originalHttpUrl = originalRequest.url

    val ts = System.currentTimeMillis().toString()
    val publicKey = BuildConfig.MARVEL_API_KEY
    val privateKey = BuildConfig.MARVEL_PRIVATE_API_KEY
    val hash = (ts + privateKey + publicKey).md5()

    val url = originalHttpUrl.newBuilder()
        .addQueryParameter("ts", ts)
        .addQueryParameter("apikey", publicKey)
        .addQueryParameter("hash", hash)
        .build()

    val requestBuilder = originalRequest.newBuilder()
        .url(url)

    val request = requestBuilder.build()
    return chain.proceed(request)
}
fun String.md5(): String {
    val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
    return BigInteger(1, bytes).toString(16).padStart(32, '0')
}
