package com.dicoding.jetpacksubmission.base

import android.annotation.TargetApi
import android.app.ProgressDialog
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.dicoding.jetpacksubmission.R
import com.dicoding.jetpacksubmission.utils.ContextProvider
import kotlin.jvm.internal.Intrinsics

abstract class BaseActivity : AppCompatActivity(), BaseView {

    private var mProgressDialog: ProgressDialog? = null

    protected abstract val layoutResourceId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ContextProvider.initialize(applicationContext)

        setContentView(layoutResourceId)

        initIntent()
        initObservable()
        initUI()
        initAction()
        initProcess()
    }

    override fun showLoading() {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog(this)
            mProgressDialog!!.setMessage(getString(R.string.message_please_wait))
            mProgressDialog!!.isIndeterminate = true
            mProgressDialog!!.setCancelable(false)
            mProgressDialog!!.setCanceledOnTouchOutside(false)
        }
        mProgressDialog!!.show()
    }

    override fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.cancel()
        }
    }

    override fun finishActivity() {
        finish()
    }

    override fun setupToolbar(toolbar: Toolbar?, isChild: Boolean, title: String) {
        toolbar?.let {
            setSupportActionBar(toolbar)
        }

        if (supportActionBar != null) {
            supportActionBar!!.title = title
            supportActionBar!!.setDisplayHomeAsUpEnabled(isChild)
        }
    }

    abstract fun initUI()
    abstract fun initIntent()
    abstract fun initAction()
    abstract fun initProcess()
    abstract fun initObservable()

    @TargetApi(23)
    fun hasPermission(permission: String): Boolean {
        Intrinsics.checkParameterIsNotNull(permission, "permission")
        return Build.VERSION.SDK_INT < 23 || this.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    @TargetApi(23)
    fun requestPermissionsSafely(permissions: Array<String?>?, requestCode: Int) {
        Intrinsics.checkParameterIsNotNull(permissions, "permissions")
        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(permissions!!, requestCode)
        }
    }
}