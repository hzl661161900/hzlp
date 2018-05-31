package com.hzl.proticekotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.flyco.tablayout.listener.CustomTabEntity
import com.hzl.proticekotlin.Base.BaseActivity
import com.hzl.proticekotlin.mvp.model.bean.TabEntity
import kotlinx.android.synthetic.main.activity_main2.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.schedulers.Schedulers
import java.util.ArrayList
import javax.security.auth.login.LoginException

class MainActivity : BaseActivity() {
    private var mIndex = 0

    private val mTitles = arrayOf("每日精选", "发现", "热门", "我的")

    // 未被选中的图标
    private val mIconUnSelectIds = intArrayOf(R.mipmap.ic_home_normal, R.mipmap.ic_discovery_normal, R.mipmap.ic_hot_normal, R.mipmap.ic_mine_normal)
    // 被选中的图标
    private val mIconSelectIds = intArrayOf(R.mipmap.ic_home_selected, R.mipmap.ic_discovery_selected, R.mipmap.ic_hot_selected, R.mipmap.ic_mine_selected)

    private val mTabEntities = ArrayList<CustomTabEntity>()
    override fun layoutId(): Int {
        return R.layout.activity_main2
    }

    override fun initData() {

    }

    override fun initView() {

    }

    override fun start() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            mIndex = savedInstanceState.getInt("currTabIndex")
        }
        super.onCreate(savedInstanceState)
        (0 until mTitles.size)
                .mapTo(mTabEntities) { TabEntity(mTitles[it], mIconSelectIds[it], mIconUnSelectIds[it]) }
    }


//    fun login(phone: String, password: String): Observable<LoginResponse>? {
//        return RetrofitUtil.createApi(AccountApi::class.java)
//                .login(LoginRequest(phone, password, null))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//    }
}
