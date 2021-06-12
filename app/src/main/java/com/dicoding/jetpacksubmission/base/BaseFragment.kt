package com.dicoding.jetpacksubmission.base

import android.annotation.TargetApi
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.dicoding.jetpacksubmission.R
import kotlin.jvm.internal.Intrinsics

abstract class BaseFragment : Fragment(), BaseView {

    private var mProgressDialog: ProgressDialog? = null

    private var baseActivity: BaseActivity? = null

    protected abstract val layoutResourceId : Int

    override fun showLoading() {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog(requireContext())
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
        activity?.finish()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutResourceId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupIntent()
        setupUI()
        setupAction()
        setupProcess()
        setupObservable()
    }

    override fun setupToolbar(toolbar: Toolbar?, isChild: Boolean, title: String) {
    }

    override fun onAttach(context: Context) {
        Intrinsics.checkParameterIsNotNull(context, "context")
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity: BaseActivity = context
            this.baseActivity = activity
        }
    }

    abstract fun setupIntent()
    abstract fun setupUI()
    abstract fun setupAction()
    abstract fun setupProcess()
    abstract fun setupObservable()

    @TargetApi(23)
    fun hasPermission(permission: String): Boolean {
        return this.baseActivity?.hasPermission(permission) == true
    }

    @TargetApi(23)
    fun requestPermissionsSafely(permissions: Array<String?>?, requestCode: Int) {
        this.baseActivity?.requestPermissionsSafely(permissions, requestCode)
    }
}