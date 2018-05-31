package com.hzl.proticekotlin

/**
 * Created by hzl on 2018/5/29.
 */
open class BaseResponse<T> {
    open var httpCode: String = "" // 状态码，0为成功
    open var msg: String = "" // （错误）信息
    open var data: T? = null
//    public Object args;
//    public ArrayList<T> object;
}