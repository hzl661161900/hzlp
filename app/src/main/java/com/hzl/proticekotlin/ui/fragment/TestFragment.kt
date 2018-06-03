package com.hzl.proticekotlin.ui.fragment

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.hzl.proticekotlin.Base.BaseFragment
import com.hzl.proticekotlin.GlideApp
import com.hzl.proticekotlin.R
import com.hzl.proticekotlin.Utils.ErrorStatus
import com.hzl.proticekotlin.Utils.StatusBarUtil
import com.hzl.proticekotlin.mvp.contract.HomeContract
import com.hzl.proticekotlin.mvp.model.bean.HomeBean
import com.hzl.proticekotlin.mvp.presenter.HomePresenter
import com.hzl.proticekotlin.ui.SearchActivity
import com.hzl.proticekotlin.ui.VideoActivity
import com.hzl.proticekotlin.ui.adpter.HomeAdapter
import com.orhanobut.logger.Logger
import com.scwang.smartrefresh.header.MaterialHeader
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_test.*
import java.text.SimpleDateFormat
import java.util.*

class TestFragment : BaseFragment(), HomeContract.View {

    private val mPresenter by lazy { HomePresenter() }

    private var mTitle: String? = null

    private var num: Int = 1

    private var mHomeAdapter: HomeAdapter? = null

    private var loadingMore = false

    private var isRefresh = false
    private var mMaterialHeader: MaterialHeader? = null

    companion object {
        fun getInstance(title: String): TestFragment {
            val fragment = TestFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    private val linearLayoutManager by lazy {
        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }


    private val simpleDateFormat by lazy {
        SimpleDateFormat("- MMM. dd, 'Brunch' -", Locale.ENGLISH)
    }


    override fun getLayoutId(): Int = R.layout.fragment_test


    /**
     * 初始化 ViewI
     */
    override fun initView() {

//        StatusBarUtil.darkMode(this.activity!!)
//        StatusBarUtil.setPaddingSmart(this.activity!!, toolbar)
        iv_search1.setOnClickListener {
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this.activity!!, iv_search1, iv_search1.transitionName)
            startActivity(Intent(activity, SearchActivity::class.java),options.toBundle())
        }
        GlideApp.with(this)
                .load("http://img.kaiyanapp.com/8164ded95cfde8c5f42acf243c6ca3e6.jpeg?imageMogr2/quality/60/format/jpg")
                .placeholder(R.drawable.placeholder_banner)
                .transition(DrawableTransitionOptions().crossFade())
                .into(iv_cover_feed)

        iv_cover_feed.setOnClickListener {
            goToVideoPlayer(this.activity!!,iv_cover_feed)
        }
    }
    private fun goToVideoPlayer(activity: Activity, view: View) {
        val intent = Intent(activity, VideoActivity::class.java)
//        intent.putExtra(Constants.BUNDLE_VIDEO_DATA, itemData)
        intent.putExtra("IMG_TRANSITION", true)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            val pair = Pair<View, String>(view, "IMG_TRANSITION")
            val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity, pair)
            ActivityCompat.startActivity(activity, intent, activityOptions.toBundle())
        } else {
            activity.startActivity(intent)
//            activity.overridePendingTransition(R.anim.anim_in, R.anim.anim_out)
        }
    }
    private fun openSearchActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, iv_search, iv_search.transitionName)
//            startActivity(Intent(activity, SearchActivity::class.java), options.toBundle())
        } else {
//            startActivity(Intent(activity, SearchActivity::class.java))
        }
    }

    override fun lazyLoad() {
        mPresenter.requestHomeData(num)
    }


    /**
     * 显示 Loading （下拉刷新的时候不需要显示 Loading）
     */
    override fun showLoading() {
        if (!isRefresh) {
            isRefresh = false
            mLayoutStatusView?.showLoading()
        }
    }

    /**
     * 隐藏 Loading
     */
    override fun dismissLoading() {
        mRefreshLayout.finishRefresh()
    }

    /**
     * 设置首页数据
     */
    override fun setHomeData(homeBean: HomeBean) {
//        mLayoutStatusView?.showContent()
//        Logger.d(homeBean)
//
//        // Adapter
//        mHomeAdapter = HomeAdapter(this.activity!!, homeBean.issueList[0].itemList)
//        //设置 banner 大小
//        mHomeAdapter?.setBannerSize(homeBean.issueList[0].count)
//
//        mRecyclerView.adapter = mHomeAdapter
//        mRecyclerView.layoutManager = linearLayoutManager
//        mRecyclerView.itemAnimator = DefaultItemAnimator()

    }

    override fun setMoreData(itemList: ArrayList<HomeBean.Issue.Item>) {
//        loadingMore = false
//        mHomeAdapter?.addItemData(itemList)
    }


    /**
     * 显示错误信息
     */
    override fun showError(msg: String, errorCode: Int) {
//        showToast(msg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            mLayoutStatusView?.showNoNetwork()
        } else {
            mLayoutStatusView?.showError()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    fun getColor(colorId: Int): Int {
        return resources.getColor(colorId)
    }


}