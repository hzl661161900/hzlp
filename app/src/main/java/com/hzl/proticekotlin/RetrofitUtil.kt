package com.hzl.proticekotlin

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by hzl on 2018/5/29.
 */
object RetrofitUtil {
    var SINGLETON: Retrofit? = null
//    private val endpoint = "http://120.78.218.241/yy-door-web-leelen2/"
//    private val endpoint = "http://192.168.1.107:8080/"
    private val endpoint = "http://baobab.kaiyanapp.com/api/"

    fun <T> createApi(clazz: Class<T>): T {
        if (SINGLETON == null) {
            synchronized(RetrofitUtil::class.java) {
                if (SINGLETON == null) {
                    SINGLETON = getRetrofit(endpoint)
                }
            }
        }
        return SINGLETON!!.create(clazz)
    }

    private fun getRetrofit( endpoint: String): Retrofit {
        val rfAPIBuilder = Retrofit.Builder()
        rfAPIBuilder.baseUrl(endpoint)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())

        val okClientBuilder = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)

        //        if (BuildConfig.DEBUG) {
        //            okClientBuilder.addNetworkInterceptor(new StethoInterceptor());
        //        }

        rfAPIBuilder.client(okClientBuilder.build())

        return rfAPIBuilder.build()
    }
}