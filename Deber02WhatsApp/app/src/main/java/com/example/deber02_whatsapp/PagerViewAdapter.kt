package com.example.deber02_whatsapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


class PagerViewAdapter(fm: FragmentManager?, var mNoOfTabs: Int) :
    FragmentStatePagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                var tab1: FragmentChats
                return tab1
            }
            1 -> {
                Tab2()
            }
            2 -> {
                Tab3()
            }
            else ->
                null
        }
    }

    override fun getCount(): Int {
        return mNoOfTabs
    }
}
