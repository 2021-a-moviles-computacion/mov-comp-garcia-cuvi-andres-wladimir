package com.example.deber02_whatsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.deber02_whatsapp.databinding.ActivityMainBinding
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)
    }

    private fun setUpWithViewPager ( viewPager: ViewPager){
        var adapter: MainActivity.FragmentPagerAdapter

      //  viewPager.adapter = adapter
    }

    abstract class FragmentPagerAdapter : PagerAdapter() {

        var mFragmentList = ArrayList<Fragment>()
        var mFragmentTitleList = ArrayList<String>()
        override fun getItemPosition(`object`: Any): Int {

            return super.getItemPosition(`object`)
        }

        override fun getCount(): Int {
            TODO("Not yet implemented")
        }
    }

}