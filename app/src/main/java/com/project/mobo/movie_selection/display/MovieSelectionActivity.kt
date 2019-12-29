package com.project.mobo.movie_selection.display

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.project.mobo.R
import com.project.mobo.movie_selection.feature.SectionPageAdapter
import com.project.mobo.movie_selection.fragment.FragmentOne
import com.project.mobo.movie_selection.fragment.FragmentTwo
import kotlinx.android.synthetic.main.activity_movie_selection.*
import kotlinx.android.synthetic.main.activity_my_page_new.*

class MovieSelectionActivity : AppCompatActivity() {

    private lateinit var mViewPager: ViewPager
    val adapter: SectionPageAdapter =
        SectionPageAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_selection)

        mViewPager = findViewById(R.id.container)
        setupViewPager(mViewPager)

        var tabLayout: TabLayout = findViewById(R.id.tabs)
        tabLayout.setupWithViewPager(mViewPager)

        backBtn_movie_selection.setOnClickListener {
            finish()
        }
    }

    public fun setupViewPager(viewPager: ViewPager){
        adapter.addFragment(FragmentOne(), "현재 상영작")
        adapter.addFragment(FragmentTwo(), "개봉 예정작")
        viewPager.setAdapter(adapter)
    }
}