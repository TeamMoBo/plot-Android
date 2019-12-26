package com.project.mobo.main_page

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.mobo.Main.Choice_Movie.choiceMovieAdapter
import com.project.mobo.Main.Choice_Movie.choiceMovieRepository
import com.project.mobo.MainActivity
import com.project.mobo.R
import com.project.mobo.mypage.MyPage_new
import kotlinx.android.synthetic.main.activity_main_page.*

class MainPageActivity : AppCompatActivity() {
    private lateinit var rvmovieChoice: RecyclerView
    private lateinit var movieChoiceAdapter: choiceMovieAdapter
    private val movieRepository= choiceMovieRepository()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        moveMyPage()
        choiceMovie()
    }

    private fun moveMyPage() {
        btnMyPG.setOnClickListener(){

            val intent = Intent(this, MyPage_new::class.java)
            startActivity(intent)
        }
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
}
