package com.project.mobo.movie_selection.display

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.project.mobo.R
import com.project.mobo.api.UserServiceImpl
import com.project.mobo.api.safeEnqueue
import com.project.mobo.movie_selection.data.MovieData
import com.project.mobo.movie_selection.data.MovieItem
import com.project.mobo.movie_selection.feature.*
import com.project.mobo.movie_selection.fragment.FragmentOne
import com.project.mobo.movie_selection.fragment.FragmentTwo
import com.project.mobo.time_choice.TimeChoiceActivity
import kotlinx.android.synthetic.main.activity_movie_selection.*

class MovieSelectionActivity : AppCompatActivity() {
    private val fragOne = FragmentOne()
    private val fragTwo = FragmentTwo()

    val rv_dataList = ArrayList<ArrayList<MovieItem>>()

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


//        fragOne.movieAdapter2.data.add(
//            MovieItem(
//                img_movie = "https://moboservers3.s3.ap-northeast-2.amazonaws.com/1577911252022.png",
//                name = "타짜 2",
//                rating_star = 5f,
//                isSelected = false,
//                idx = 0
//            )
//        )

        //더미
//        fragOne.movieAdapter1.data = arrayListOf(movieData.movieData_1[0], movieData.movieData_1[1])
//        for (i in 2 until movieData.movieData_1.size){
//            fragOne.movieAdapter2.data.add(movieData.movieData_1[i])
//        }
//        fragTwo.movieAdapter3.data = arrayListOf(movieData.movieData_2[0], movieData.movieData_2[1])
//        for(i in 2 until movieData.movieData_2.size)
//        {
//            fragTwo.movieAdapter4.data.add(movieData.movieData_2[i])
//        }

        val callMovieList = UserServiceImpl.movieList.movieTopResopnse("0")
        val callMovieList2 = UserServiceImpl.movieList.movieTopResopnse("1")

        callMovieList.safeEnqueue {
            if (it.isSuccessful) {
                Log.d("test", "서버통신")
                if (it.body()!!.data.movieReleaseStatus == 0) {
                    val myData = it.body()!!.data.movieData
                    var addListOne1 = arrayListOf<MovieItem>()
                    var addListOne2 = arrayListOf<MovieItem>()
                    for (i in 0 until 2) {
                        addListOne1.add(
                            MovieItem(
                                img_movie = myData[i].movieImg,
                                name = myData[i].movieName,
                                rating_star = myData[i].movieScore,
                                isSelected = false,
                                idx = myData[i].movieIdx
                            )
                        )
                    }
                    for (i in 2 until myData.size) {
                        addListOne2.add(
                            MovieItem(
                                img_movie = myData[i].movieImg,
                                name = myData[i].movieName,
                                rating_star = myData[i].movieScore,
                                isSelected = false,
                                idx = myData[i].movieIdx
                            )
                        )
                    }
                    fragOne.movieAdapter1.data = addListOne1
                    fragOne.movieAdapter2.data = addListOne2
                    Log.d("test", fragOne.movieAdapter2.data[0].img_movie)
                    fragOne.movieAdapter1.notifyDataSetChanged()
                    fragOne.movieAdapter2.notifyDataSetChanged()
                    rv_dataList.add(fragOne.movieAdapter1.data)
                    rv_dataList.add(fragOne.movieAdapter2.data)
                }
            }
        }



        callMovieList2.safeEnqueue {
            if (it.isSuccessful) {
                Log.d("test", "서버통신")
                val myData = it.body()!!.data.movieData
                var addListTwo1 = arrayListOf<MovieItem>()
                var addListTwo2 = arrayListOf<MovieItem>()
                for (i in 0 until 2) {
                    addListTwo1.add(
                        MovieItem(
                            img_movie = myData[i].movieImg,
                            name = myData[i].movieName,
                            rating_star = myData[i].movieScore,
                            isSelected = false,
                            idx = myData[i].movieIdx
                        )
                    )
                }
                for (i in 2 until myData.size) {
                    addListTwo2.add(
                        MovieItem(
                            img_movie = myData[i].movieImg,
                            name = myData[i].movieName,
                            rating_star = myData[i].movieScore,
                            isSelected = false,
                            idx = myData[i].movieIdx
                        )
                    )
                }
                fragTwo.movieAdapter3.data = addListTwo1
                fragTwo.movieAdapter4.data = addListTwo2
                fragTwo.movieAdapter3.notifyDataSetChanged()
                fragTwo.movieAdapter4.notifyDataSetChanged()
                rv_dataList.add(fragTwo.movieAdapter3.data)
                rv_dataList.add(fragTwo.movieAdapter4.data)
            }
        }


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
        Log.d("test", rv_dataList[0][1].isSelected.toString())
        rv_dataList.forEach{
            for(i in 0 until it.count()){
                if (it[i].isSelected){
                    addList(it[i].idx)
                }
            }
        }
        Log.d("test", "$list_selected_current")
    }

    fun addList(int: Int){
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
