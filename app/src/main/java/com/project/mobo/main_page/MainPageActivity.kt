package com.project.mobo.main_page

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.mobo.main_page.choice_movie.ChoiceMovieAdapter
import com.project.mobo.main_page.choice_movie.ChoiceMovieRepository
import com.project.mobo.main_page.choice_time.DataVerticalAdapter
import com.project.mobo.main_page.data.MainReserveDate
import com.project.mobo.main_page.choice_time.innerData.ChoiceDateAdapter
import com.project.mobo.main_page.choice_time.innerData.ChoiceDateRepository
import com.project.mobo.main_page.top_three_viewPager.MovieData
import com.project.mobo.main_page.top_three_viewPager.MoviePagerAdapter
import com.project.mobo.movie_selection.display.MovieSelectionActivity
import com.project.mobo.mypage.MyPage_new
import com.project.mobo.time_choice.TimeChoiceActivity
import kotlinx.android.synthetic.main.activity_main_page.*
import kotlinx.android.synthetic.main.fragment_main.*
import android.net.Uri
import android.view.View
import com.project.mobo.MatchingHistory.MatchingHistoryActivity
import com.project.mobo.R
import com.project.mobo.SharedPreferenceController
import com.project.mobo.api.Data
import com.project.mobo.api.UserServiceImpl
import com.project.mobo.api.safeEnqueue
import com.project.mobo.dialog.MatchingDialogActivity
import com.project.mobo.dialog.MatchingSuccessActivity
import com.project.mobo.util.registerEvent
import kotlinx.android.synthetic.main.activity_chatting.*
import java.util.*


class MainPageActivity : AppCompatActivity() {
    private lateinit var rvmovieChoice: RecyclerView
    private lateinit var movieChoiceAdapter: ChoiceMovieAdapter
    private val movieRepository= ChoiceMovieRepository()

    private lateinit var rvdateChoice: RecyclerView
    private lateinit var dateChoiceAdapter: ChoiceDateAdapter
    private val dateRepository= ChoiceDateRepository()

    //private lateinit var timeData: TimeData - 더미데이터
    private lateinit var mainData: Data
    private lateinit var verticalAdapter : DataVerticalAdapter

    private lateinit var createdTime: Date


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        val callMain = UserServiceImpl.MainService.mainResponse(
            //key = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZHgiOjM3LCJpYXQiOjE1Nzc3NTk2MzIsImV4cCI6MTU3ODM2NDQzMiwiaXNzIjoibW9ib21hc3RlciJ9.k30fv2OoezYTrzMJnhaFdM0suMnnoIVfjoGkOBMe-G4"
            key = SharedPreferenceController.getUserToken(this)
        )

        //shared 아직 안 쓰는 중

        //SharedPreferenceController.getUserToken(this)

        callMain.safeEnqueue (onResponse = {
            if(it.isSuccessful){
                mainData=it.body()!!.data
                topThree() // 뷰페이저
                choiceMovie() // 선택한 영화 창
                choiceDate() // 선택한 시간 창
            }

        }, onError = {})

        movePage() //intent

        //dummy()



        //setOnClickListener()
    }

    /*
    private fun dummy(){
        timeData = TimeData(ArrayList(), ArrayList(), ArrayList())
        timeData.reserveDate.add(MainReserveDate("14", arrayListOf<String>("12", "13", "14", "15")))
        timeData.reserveDate.add(MainReserveDate("15", arrayListOf<String>("12", "13")))
        timeData.reserveDate.add(MainReserveDate("16", arrayListOf<String>("12", "13", "14")))
        timeData.reserveDate.add(MainReserveDate("17", arrayListOf<String>("12", "13", "14", "15", "16")))
        timeData.reserveDate.add(MainReserveDate("18", arrayListOf<String>("12", "13", "14")))
        timeData.reserveDate.add(MainReserveDate("19", arrayListOf<String>("12")))
        timeData.reserveDate.add(MainReserveDate("20", arrayListOf<String>("12", "13", "14", "15")))

    }*/


    private fun movePage() {
        //마이페이지로 이동
        btnMyPG.setOnClickListener(){

            val intent1 = Intent(this, MyPage_new::class.java)
            startActivity(intent1)
        }
        //시간수정창으로 이동
        btnTimeChanging.setOnClickListener(){
            val intent2 = Intent(this, TimeChoiceActivity::class.java)
            startActivity(intent2)
        }

        //영화수정창으로 이동
        btnMovieChanging.setOnClickListener(){
            val intent3 = Intent(this, MovieSelectionActivity::class.java)
            startActivity(intent3)
        }

        //영화/시간 선택 시작
        btnMovieChoiceStart.setOnClickListener(){
            val intent4 = Intent(this, MovieSelectionActivity::class.java)
            //startActivity(intent4)
            startActivityForResult(intent4, 2000)
        }

        //이력창으로 이동
        btnMainChating.setOnClickListener(){
            val intent5 = Intent(this, MatchingHistoryActivity::class.java)
            startActivity(intent5)
        }


    }

    private fun topThree() {

        val adapter = MoviePagerAdapter(mainData.randMovie)
        vpMain.setClipToPadding(false)
        vpMain.setPadding(34,0,34,0)

        vpMain.pageMargin=8
        vpMain.adapter = adapter

        //btnMainFirstPlay.setOnClickListener(){
        //    val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/li4jOV5j7SI"))
        //startActivity(i)
        //}
    }

    companion object {
        val movieList = arrayListOf(
            MovieData(
                "https://t1.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/6atO/image/nDpOWQrYaI2MQ514GXPpCOFkJUU",
                "엑시트"
                //"4.5"
                //"1h 40m"i
            ),
            MovieData(
                "https://t1.daumcdn.net/cfile/tistory/236A5E4D52D6B15B03",
                "어바웃 타임"
                //"4"
                //"1h 40m"
            ),
            MovieData(
                "https://cools.co/wp-content/uploads/2019/12/80218028_3472865946120662_576660455397785600_n.jpg",
                "천문"
                //"3.5"
                //"1h 40m"
            )
        )
    }

    private fun choiceMovie(){

        if(mainData.reserveMovie.size==0) {
            tvNoMovie.setVisibility(View.VISIBLE)
            btnMovieNoChanging.setVisibility(View.VISIBLE)
            btnMovieChanging.setVisibility(View.INVISIBLE)
        }
        else if(mainData.reserveMovie.size>0) {
            tvNoMovie.setVisibility(View.INVISIBLE)
            btnMovieNoChanging.setVisibility(View.INVISIBLE)
            btnMovieChanging.setVisibility(View.VISIBLE)

            rvmovieChoice = findViewById(R.id.rvMovieChoice)
            movieChoiceAdapter = ChoiceMovieAdapter(this, mainData.reserveMovie)

            rvmovieChoice.adapter = movieChoiceAdapter

            val llm = LinearLayoutManager(this)
            llm.orientation = LinearLayoutManager.HORIZONTAL
            rvmovieChoice.setLayoutManager(llm)
            rvmovieChoice.setAdapter(movieChoiceAdapter)

            //movieChoiceAdapter.data=movieRepository.getRepoList()


            movieChoiceAdapter.notifyDataSetChanged()
        }
    }


    private fun choiceDate(){

        if(mainData.reserveDate.size==0) {
            tvNoDate.setVisibility(View.VISIBLE)
            btnTimeNoChanging.setVisibility(View.VISIBLE)
            btnTimeChanging.setVisibility(View.INVISIBLE)
        }
        else if(mainData.reserveDate.size>0) {
            tvNoDate.setVisibility(View.INVISIBLE)
            btnTimeNoChanging.setVisibility(View.INVISIBLE)
            btnTimeChanging.setVisibility(View.VISIBLE)
            //지금부터 얘는 큰 거
            rvdateChoice = findViewById(R.id.rvMainDate)

            //TODO : 서버 통신 이후, 반드시 reserveDate만 넘겨줄 것.
            verticalAdapter = DataVerticalAdapter(this, mainData.reserveDate)
//        dateChoiceAdapter= choiceDateAdapter(this)

            rvdateChoice.layoutManager = LinearLayoutManager(this)
            rvdateChoice.adapter = verticalAdapter

//        val llm = LinearLayoutManager(this)
//        llm.orientation = LinearLayoutManager.HORIZONTAL
//        rvdateChoice.setLayoutManager(llm)
//        rvdateChoice.setAdapter(dateChoiceAdapter)
//
//
//        dateChoiceAdapter.data=dateRepository.getRepoList()
//
//        dateChoiceAdapter.notifyDataSetChanged()
        }
    }


    //requestCode 쓸 곳

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 2000){
            val callMain2 = UserServiceImpl.MainService.mainResponse(
                //key = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZHgiOjM3LCJpYXQiOjE1Nzc3NTk2MzIsImV4cCI6MTU3ODM2NDQzMiwiaXNzIjoibW9ib21hc3RlciJ9.k30fv2OoezYTrzMJnhaFdM0suMnnoIVfjoGkOBMe-G4"
                key = SharedPreferenceController.getUserToken(this)
            )
            callMain2.safeEnqueue (onResponse = {
                if(it.isSuccessful){
                    mainData=it.body()!!.data
                    topThree() // 뷰페이저
                    choiceMovie() // 선택한 영화 창
                    choiceDate() // 선택한 시간 창
                }
            }, onError = {})

            ////////
            timer()
        }
    }

    private fun timer(){
        createdTime = Date()

        registerEvent(createdTime, 0.05f) {
            val choice = Intent(this, MatchingSuccessActivity::class.java)
            startActivity(choice)
            //finish()
        }

    }

}
