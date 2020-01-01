package com.project.mobo.time_choice

import khronos.toDate
import khronos.toString
import java.util.*
private val englishMonths = listOf(
    "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
)

private val englishDays = listOf(
    "NONE","Sun", "Mon", "Tue", "Wed", "Thus", "Fri", "Sat"
)

fun Date.getMonthOfYear(): String {
    val calendar = Calendar.getInstance().apply {
        time = this@getMonthOfYear
    }
    return englishMonths[calendar.get(Calendar.MONTH)]
}

fun Date.getDayOfWeek(): String {
    val calendar = Calendar.getInstance().apply {
        time = this@getDayOfWeek
    }
    return englishDays[calendar.get(Calendar.DAY_OF_WEEK)]
}

fun Date.getDayNum(): Int {
    val calendar = Calendar.getInstance().apply {
        time = this@getDayNum
    }
    return calendar.get(Calendar.DATE)
}

private const val FLOT_DATE_FORMAT = "yyyy-MM-dd"

fun String.toFlotDate(): Date {
    return this.toDate(FLOT_DATE_FORMAT)
}

fun Date.toPlotString(): String {
    return this.toString(FLOT_DATE_FORMAT)
}