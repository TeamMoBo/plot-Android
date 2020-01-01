package com.project.mobo.main_page.choice_time.innerData

class ChoiceDateRepository {
    fun getRepoList(): List<ChoiceDateItem> {
        return listOf(
            ChoiceDateItem(time = "12"),
            ChoiceDateItem(time = "13"),
            ChoiceDateItem(time = "14"),
            ChoiceDateItem(time = "15"),
            ChoiceDateItem(time = "16"),
            ChoiceDateItem(time = "17")
        )
    }
}