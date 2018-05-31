package com.hzl.proticekotlin.mvp.presenter

import com.hzl.proticekotlin.Base.BasePresenter
import com.hzl.proticekotlin.Utils.ExceptionHandle
import com.hzl.proticekotlin.mvp.contract.HomeContract
import com.hzl.proticekotlin.mvp.model.HomeModel
import com.hzl.proticekotlin.mvp.model.bean.HomeBean
import rx.functions.Action1

class HomePresenter : BasePresenter<HomeContract.View>(), HomeContract.Presenter{

    private var bannerHomeBean: HomeBean? = null

    private var nextPageUrl:String?=null     //加载首页的Banner 数据+一页数据合并后，nextPageUrl没 add

    private val homeModel: HomeModel by lazy {

        HomeModel()
    }
    override fun requestHomeData(num: Int) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable =homeModel.requestHomeData(num)
                .flatMap { homebean ->
                    val bannerItemList = homebean.issueList[0].itemList

                    bannerItemList.filter { item ->
                        item.type=="banner2"|| item.type=="horizontalScrollCard"
                    }.forEach{ item ->
                        //移除 item
                        bannerItemList.remove(item)
                    }

                    bannerHomeBean = homebean //记录第一页是当做 banner 数据


                    //根据 nextPageUrl 请求下一页数据
                    homeModel.loadMoreData(homebean.nextPageUrl)
                }.subscribe(  {
                    mRootView?.apply {
                        dismissLoading()

                        nextPageUrl = it.nextPageUrl
                        //过滤掉 Banner2(包含广告,等不需要的 Type), 具体查看接口分析
                        val newBannerItemList = it.issueList[0].itemList

                        newBannerItemList.filter { item ->
                            item.type=="banner2"||item.type=="horizontalScrollCard"
                        }.forEach{ item ->
                            //移除 item
                            newBannerItemList.remove(item)
                        }
                        // 重新赋值 Banner 长度
                        bannerHomeBean!!.issueList[0].count = bannerHomeBean!!.issueList[0].itemList.size

                        //赋值过滤后的数据 + banner 数据
                        bannerHomeBean?.issueList!![0].itemList.addAll(newBannerItemList)

                        setHomeData(bannerHomeBean!!)
                    }
                },{
                    mRootView?.apply {
                        dismissLoading()
                        showError(ExceptionHandle.handleException(it),ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    override fun loadMoreData() {
        val isposable = nextPageUrl?.let {
            homeModel.loadMoreData(it).subscribe({
                mRootView?.apply {
                    //过滤掉 Banner2(包含广告,等不需要的 Type), 具体查看接口分析
                    val newItemList = it.issueList[0].itemList

                    newItemList.filter { item ->
                        item.type=="banner2"||item.type=="horizontalScrollCard"
                    }.forEach{ item ->
                        //移除 item
                        newItemList.remove(item)
                    }

                    nextPageUrl = it.nextPageUrl
                    setMoreData(newItemList)
                }
            },{
                mRootView?.apply {
                    showError(ExceptionHandle.handleException(it),ExceptionHandle.errorCode)
                }
            })
        }
        if (isposable != null) {
            addSubscription(isposable)
        }
    }


}