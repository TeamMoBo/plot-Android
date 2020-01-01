package com.project.mobo.movie_selection.data

data class MovieItem(
    var img_movie: Int,
    var name: String,
    var rating_star: Int,
    var isSelected: Boolean,
    var idx: Int
)