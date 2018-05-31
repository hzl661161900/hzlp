package com.hzl.proticekotlin

import com.hzl.proticekotlin.mvp.model.bean.HomeBean
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by hzl on 2018/5/29.
 */
interface AccountApi {
    @POST("entry/login")
    fun login(@Body request: LoginRequest): Observable<LoginResponse>

    @FormUrlEncoded
    @POST("admin/login")
    fun loginLocal(@Field("username") name:String,@Field("password") password:String):Observable<LoginInfo>

    @GET("v2/feed?")
    fun getFirstHomeData(@Query("num") num:Int): Observable<HomeBean>

    /**
     * 根据 nextPageUrl 请求数据下一页数据
     */
    @GET
    fun getMoreHomeData(@Url url: String): Observable<HomeBean>
}