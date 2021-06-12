package com.dicoding.jetpacksubmission.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class BasePagerAdapter (private val fragmentList: List<BaseFragment>, fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm ,lifecycle) {
    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]
}