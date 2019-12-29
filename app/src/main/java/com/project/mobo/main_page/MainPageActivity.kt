package com.project.mobo.main_page

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.mobo.main_page.choice_date.choiceDateAdapter
import com.project.mobo.main_page.choice_date.choiceDateRepository
import com.project.mobo.main_page.choice_movie.choiceMovieAdapter
import com.project.mobo.main_page.choice_movie.choiceMovieRepository
import com.project.mobo.main_page.top_three_viewPager.MovieData
import com.project.mobo.main_page.top_three_viewPager.MoviePagerAdapter

import com.project.mobo.mypage.MyPage_new
import kotlinx.android.synthetic.main.activity_main_page.*
import com.project.mobo.R


class MainPageActivity : AppCompatActivity() {
    private lateinit var rvmovieChoice: RecyclerView
    private lateinit var movieChoiceAdapter: choiceMovieAdapter
    private val movieRepository= choiceMovieRepository()

    private lateinit var rvdateChoice: RecyclerView
    private lateinit var dateChoiceAdapter: choiceDateAdapter
    private val dateRepository= choiceDateRepository()

    private lateinit var rvdateChoice2: RecyclerView
    private lateinit var dateChoiceAdapter2: choiceDateAdapter
    private val dateRepository2= choiceDateRepository()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        movePage() //intent

        topThree() // 뷰페이저
        choiceMovie() // 선택한 영화 창
        choiceDate() // 선택한 날짜 창
    }

    private fun movePage() {
        //마이페이지로 이동
        btnMyPG.setOnClickListener(){

            val intent1 = Intent(this, MyPage_new::class.java)
            startActivity(intent1)
        }
        //시간수정창으로 이동
        btnTimeChanging.setOnClickListener(){
            //val intent2 = Intent(this, TimeChoiceActivity::class.java)
            //startActivity(intent2)
        }

        //영화수정창으로 이동
        btnMovieChanging.setOnClickListener(){
            //val intent3 = Intent(this, MovieSelectActivity::class.java)
            //startActivity(intent3)
        }

        //영화/시간 선택 시작
        btnMovieChoiceStart.setOnClickListener(){
            //val intent4 = Intent(this, MovieSelectActivity::class.java)
            //startActivity(intent4)
        }
    }

    private fun topThree() {

        val adapter = MoviePagerAdapter(movieList)
        vpMain.setClipToPadding(false)
        vpMain.setPadding(34,0,34,0)

        vpMain.pageMargin=8
        vpMain.adapter = adapter

    }

    companion object {
        val movieList = arrayListOf(
            MovieData(
                "https://t1.daumcdn.net/cfile/tistory/236A5E4D52D6B15B03",
                "aboutTime"
                //"4.5",
                //"1h 40m"
            ),
            MovieData(
                "https://t1.daumcdn.net/cfile/tistory/236A5E4D52D6B15B03",
                "aboutTime"
                //"4.5",
                //"1h 40m"
            ),
            MovieData(
                "https://t1.daumcdn.net/cfile/tistory/236A5E4D52D6B15B03",
                "aboutTime"
                //"4.5",
                //"1h 40m"
            )
        )
    }

    private fun choiceMovie(){
        rvmovieChoice=findViewById(R.id.rvMovieChoice)
        movieChoiceAdapter= choiceMovieAdapter(this)

        rvmovieChoice.adapter=movieChoiceAdapter

        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.HORIZONTAL
        rvmovieChoice.setLayoutManager(llm)
        rvmovieChoice.setAdapter(movieChoiceAdapter)

        movieChoiceAdapter.data=movieRepository.getRepoList()


        movieChoiceAdapter.notifyDataSetChanged()
    }


    private fun choiceDate(){
        rvdateChoice=findViewById(R.id.rvMainThirdDate1)
        rvdateChoice2=findViewById(R.id.rvMainThirdDate2)
        dateChoiceAdapter= choiceDateAdapter(this)
        dateChoiceAdapter2=choiceDateAdapter(this)

        rvdateChoice.adapter=dateChoiceAdapter
        rvdateChoice2.adapter=dateChoiceAdapter2

        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.HORIZONTAL
        rvdateChoice.setLayoutManager(llm)
        rvdateChoice.setAdapter(dateChoiceAdapter)

        val llm2=LinearLayoutManager(this)
        llm2.orientation=LinearLayoutManager.HORIZONTAL
        rvdateChoice2.setLayoutManager(llm2)
        rvdateChoice2.setAdapter(dateChoiceAdapter2)

        dateChoiceAdapter.data=dateRepository.getRepoList()
        dateChoiceAdapter2.data=dateRepository2.getRepoList()

        dateChoiceAdapter.notifyDataSetChanged()
        dateChoiceAdapter2.notifyDataSetChanged()

    }

}
