package com.hzl.proticekotlin.mvp.model

import com.hzl.proticekotlin.AccountApi
import com.hzl.proticekotlin.RetrofitUtil
import com.hzl.proticekotlin.mvp.model.bean.HomeBean
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers



/**
 * Created by xuhao on 2017/11/21.
 * desc: 首页精选 model
 */

class HomeModel{

    /**
     * 获取首页 Banner 数据
     */
    fun requestHomeData(num:Int):Observable<HomeBean>{
        return RetrofitUtil.createApi(AccountApi::class.java)
                .getFirstHomeData(num)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    }

    /**
     * 加载更多
     */
    fun loadMoreData(url:String):Observable<HomeBean>{

        return RetrofitUtil.createApi(AccountApi::class.java)
                .getMoreHomeData(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }



}
