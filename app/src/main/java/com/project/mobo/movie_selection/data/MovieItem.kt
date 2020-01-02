package com.project.mobo.movie_selection.data

data class MovieItem(
    var img_movie: String,
    var name: String,
    var rating_star: Float,
    var isSelected: Boolean,
    var idx: Int
)