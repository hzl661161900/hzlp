package com.hzl.proticekotlin.ui

import android.annotation.TargetApi
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.transition.Transition
import com.hzl.proticekotlin.R
import com.orhanobut.logger.Logger
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer
import kotlinx.android.synthetic.main.activity_video.*

class VideoActivity : AppCompatActivity() {


    private var transition: Transition? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        initTransition()
    }

    private fun initTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            postponeEnterTransition()
            ViewCompat.setTransitionName(mVideoView, "IMG_TRANSITION")
            addTransitionListener()
            startPostponedEnterTransition()
        } else {
            loadVideoInfo()
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun addTransitionListener() {
        transition = window.sharedElementEnterTransition
        transition?.addListener(object : Transition.TransitionListener {
            override fun onTransitionResume(p0: Transition?) {
            }

            override fun onTransitionPause(p0: Transition?) {
            }

            override fun onTransitionCancel(p0: Transition?) {
            }

            override fun onTransitionStart(p0: Transition?) {
            }

            override fun onTransitionEnd(p0: Transition?) {
                Logger.d("onTransitionEnd()------")

                loadVideoInfo()
                transition?.removeListener(this)
            }

        })
    }

    override fun onBackPressed() {
//        orientationUtils?.backToProtVideo()
//        if (StandardGSYVideoPlayer.backFromWindowFull(this))
//            return
//        //释放所有
//        mVideoView.setStandardVideoAllCallBack(null)
//        GSYVideoPlayer.releaseAllVideos()
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) run {
            super.onBackPressed()
        } else {
            finish()
//            overridePendingTransition(R.anim.anim_out, R.anim.anim_in)
        }
    }

    private fun loadVideoInfo() {
        mVideoView.setUp("http://ali.cdn.kaiyanapp.com/f5c4e2fc1ad070d2a7b9e1171497590e_1280x720.mp4?auth_key=1528028191-0-0-81966cbff69523c78c5a482b09eb5166",false,"lll")
        mVideoView.startPlayLogic()
    }
}
