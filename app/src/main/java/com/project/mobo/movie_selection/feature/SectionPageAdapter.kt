package com.project.mobo.movie_selection.feature

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

public class SectionPageAdapter: FragmentPagerAdapter{
    public var mFragmentList = ArrayList<Fragment>()
    public var mFragmentTitleList = ArrayList<String>()

    public fun addFragment(fragment: Fragment, title: String){
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    public override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList.get(position)
    }

    public constructor(fm: FragmentManager): super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)

    public override fun getItem(position: Int): Fragment {
        return mFragmentList.get(position)
    }

    public override fun getCount(): Int {
        return mFragmentList.size
    }
}