package com.dicoding.jetpacksubmission.presentation.splashscreen

import com.dicoding.jetpacksubmission.R
import com.dicoding.jetpacksubmission.base.BaseActivity
import com.dicoding.jetpacksubmission.presentation.main.MainActivity
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SplashScreenActivity : BaseActivity(), CoroutineScope {

    private var job = Job()

    override val layoutResourceId: Int = R.layout.activity_splash_screen

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun initUI() {
    }

    override fun initIntent() {
    }

    override fun initAction() {
    }

    override fun initProcess() {
        launch {
            delay(3000)
            toNextActivity()
        }
    }

    override fun initObservable() {
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }

    private fun toNextActivity() {
        MainActivity.start(
            this
        )
        finishActivity()
    }
}