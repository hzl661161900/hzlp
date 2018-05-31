package com.hzl.proticekotlin

import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * Created by hzl on 2018/5/29.
 */
interface AccountApi {
    @POST("entry/login")
    fun login(@Body request: LoginRequest): Observable<LoginResponse>
}