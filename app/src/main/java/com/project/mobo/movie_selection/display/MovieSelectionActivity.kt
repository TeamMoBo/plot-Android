package com.project.mobo.movie_selection.display

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.project.mobo.R
import com.project.mobo.movie_selection.data.MovieData
import com.project.mobo.movie_selection.data.MovieItem
import com.project.mobo.movie_selection.feature.*
import com.project.mobo.movie_selection.fragment.FragmentOne
import com.project.mobo.movie_selection.fragment.FragmentTwo
import com.project.mobo.time_choice.TimeChoiceActivity
import kotlinx.android.synthetic.main.activity_movie_selection.*

class MovieSelectionActivity : AppCompatActivity() {
//    val movieAdapter1: MovieAdapter = MovieAdapter(R.layout.rv_item_movie1)
//    val movieAdapter2: MovieAdapter = MovieAdapter(R.layout.rv_item_movie2)
//    val movieAdapter3: MovieAdapter = MovieAdapter(R.layout.rv_item_movie3)
//    val movieAdapter4: MovieAdapter = MovieAdapter(R.layout.rv_item_movie4)
    val fragOne = FragmentOne()
    val fragTwo = FragmentTwo()
    val movieData = MovieData()

    val rv_dataList = ArrayList<ArrayList<MovieItem>>()
//        arrayListOf(fragOne.movieAdapter1.data, fragOne.movieAdapter2.data, fragTwo.movieAdapter3.data, fragTwo.movieAdapter4.data)

    var list_selected_current = ArrayList<Int>()

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

        fragOne.movieAdapter1.data = movieData.movieData_1
        fragOne.movieAdapter2.data = movieData.movieData_2
        fragTwo.movieAdapter3.data = movieData.movieData_3
        fragTwo.movieAdapter4.data = movieData.movieData_4

        rv_dataList.add(fragOne.movieAdapter1.data)
        rv_dataList.add(fragOne.movieAdapter2.data)
        rv_dataList.add(fragTwo.movieAdapter3.data)
        rv_dataList.add(fragTwo.movieAdapter4.data)

        val intent = Intent(this, TimeChoiceActivity::class.java)

        nextBtn_movieseletion.setOnClickListener{
            selectedDataCal()
            if (list_selected_current.size == 0){
                Toast.makeText(this, "영화를 하나 이상 선택해주세요", Toast.LENGTH_SHORT).show()
            }
            else {
                intent.putExtra("movieArray",list_selected_current)
                startActivityForResult(intent, 1000)
            }
        }


    }

    fun setupViewPager(viewPager: ViewPager) {
        adapter.addFragment(fragOne, "현재 상영작")
        adapter.addFragment(fragTwo, "개봉 예정작")
        viewPager.setAdapter(adapter)
    }

    fun selectedDataCal(){
        list_selected_current.clear()
        rv_dataList.forEach{
            for(i in 0 until it.count()){
                if (it[i].isSelected){
                    addList(it, it[i].idx)
                }
            }
        }
        Log.d("test", "$list_selected_current")
    }

    fun addList(list: ArrayList<MovieItem>, int: Int){
        val idx: Int = int
        list_selected_current.add(idx)
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1000){
            finish()
        }
    }

}
