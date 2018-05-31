package com.hzl.proticekotlin

/**
 * Created by hzl on 2018/5/29.
 */
class LoginResponse : BaseResponse<CommunityName>()

class CommunityName {

    /**
     * organizationName : 星辉阁
     * devicesList : [{"Name":"一栋正门@QYDOOR-2FD6"},{"Name":"负一层@QYDOOR-A7BE"}]
     * type : 2
     * managerName : hzl
     */

    var organizationName: String? = null
    var type: Int = 0
    var managerName: String? = null
    var devicesList: List<DevicesListBean>? = null

    class DevicesListBean {
        /**
         * Name : 一栋正门@QYDOOR-2FD6
         */

        var name: String? = null
    }
}

class LoginInfo{
    var payload:String ?=null
    var success:String ?=null
    var msg:String ?= null
    var code:String ?= null
    var timestamp:String ? =null
}