package com.project.mobo.movie_selection.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.project.mobo.R
import com.project.mobo.movie_selection.data.MovieItem
import com.project.mobo.movie_selection.feature.MovieAdapter

/**
 * A simple [Fragment] subclass.
 */
class FragmentTwo : Fragment() {
    private lateinit var rv_movie: RecyclerView
    private lateinit var rv_movie2: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var movieAdapter2: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_fragment_one, container, false)
        //rv1
        rv_movie = v.findViewById(R.id.rv_movie)
        rv_movie.setHasFixedSize(true)

        movieAdapter = MovieAdapter()

        rv_movie.adapter = movieAdapter

        rv_movie.layoutManager = GridLayoutManager(activity,2)

        movieAdapter.data = listOf(
            MovieItem(
                img_movie = R.drawable.a,
                name = "타짜 2",
                rating_star = R.drawable.img_star_5
            ),
            MovieItem(
                img_movie = R.drawable.b,
                name = "신세계",
                rating_star = R.drawable.img_star_4
            )
        )

        movieAdapter.isTop = true

        movieAdapter.notifyDataSetChanged()

        //rv2
        rv_movie2 = v.findViewById(R.id.rv_movie2)
        rv_movie2.setHasFixedSize(true)

        movieAdapter2 = MovieAdapter()

        rv_movie2.adapter = movieAdapter2

        rv_movie2.layoutManager = GridLayoutManager(activity,4)

        movieAdapter2.data = listOf(
            MovieItem(
                img_movie = R.drawable.ae9656be06b91fd2d661a7091934cb3e,
                name = "안녕 나의 소녀",
                rating_star = R.drawable.img_star_3
            ),
            MovieItem(
                img_movie = R.drawable.m,
                name = "외주의 신",
                rating_star = R.drawable.img_star_4
            ),
            MovieItem(
                img_movie = R.drawable.df88cf9a3f4e7f39da97901dbbc39af,
                name = "극한 직업",
                rating_star = R.drawable.img_star_5
            ),
            MovieItem(
                img_movie = R.drawable.dec29208cda7bd05deca3cdc91f88a0fb871ee54c5ffb40a797909e3427f701_v1,
                name = "마녀",
                rating_star = R.drawable.img_star_3
            ),
            MovieItem(
                img_movie = R.drawable.ae9656be06b91fd2d661a7091934cb3e,
                name = "타짜 2",
                rating_star = R.drawable.img_star_5

            ),
            MovieItem(
                img_movie = R.drawable.a14cbb522349487544b0191aeb9cc156,
                name = "타짜 2",
                rating_star = R.drawable.img_star_4
            ),
            MovieItem(
                img_movie = R.drawable.ae9656be06b91fd2d661a7091934cb3e,
                name = "Plot",
                rating_star = R.drawable.img_star_5
            ),
            MovieItem(
                img_movie = R.drawable.m,
                name = "SOPT",
                rating_star = R.drawable.img_star_5
            )
        )

        movieAdapter2.notifyDataSetChanged()

        // Inflate the layout for this fragment
        return v
    }


}
