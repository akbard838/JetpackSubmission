package com.dicoding.jetpacksubmission.base

import androidx.appcompat.widget.Toolbar

interface BaseView {

    fun setupToolbar(toolbar: Toolbar?, isChild : Boolean, title: String)

    fun showLoading()

    fun hideLoading()

    fun finishActivity()

}