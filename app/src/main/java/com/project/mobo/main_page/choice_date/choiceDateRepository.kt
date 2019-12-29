package com.project.mobo.main_page.choice_date

import com.project.mobo.main_page.choice_movie.choiceMovieItem

class choiceDateRepository {
    fun getRepoList(): List<choiceDateItem> {
        return listOf(
            choiceDateItem(time = "12"),
            choiceDateItem(time = "13"),
            choiceDateItem(time = "14"),
            choiceDateItem(time = "15"),
            choiceDateItem(time = "16"),
            choiceDateItem(time = "17")
        )
    }
}