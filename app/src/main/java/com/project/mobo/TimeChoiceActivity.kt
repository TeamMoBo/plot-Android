package com.project.mobo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import khronos.Dates
import khronos.days
import khronos.plus
import kotlinx.android.synthetic.main.activity_time_choice.*
import java.util.*

typealias ChooseDate = Pair<Date, MutableSet<String>>

class TimeChoiceActivity : AppCompatActivity() {
    private val chooseDates = List<ChooseDate>(7) {
        Pair(Dates.today + (2+it).days, mutableSetOf())
    }
    private var currentSelectedDatePosition = 0
        set(value) {
            field = value
            clearTimetable()
            loadTimetableFromMemory()
        }
    private lateinit var btnTimeChoices: List<CheckedTextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_choice)

        initUI()
        initDayUI()
        initChoiceTime()
    }

    private fun initUI() {
        btnTimeChoices = listOf(
            btnTimeChoice10pm, btnTimeChoice11pm, btnTimeChoice12am, btnTimeChoice12pm, btnTimeChoice6pm,
            btnTimeChoice1am, btnTimeChoice2am, btnTimeChoice3pm, btnTimeChoice4pm, btnTimeChoice5pm,
            btnTimeChoice7pm, btnTimeChoice8pm, btnTimeChoice9pm, btnTimeChoice1pm, btnTimeChoice2pm
        )
    }

    private fun initDayUI() {
        txtMonth.text = Dates.today.getMonthOfYear()

        val txtDays = listOf(txtDay1,txtDay2,txtDay3,txtDay4,txtDay5,txtDay6,txtDay7)
        val txtDayNums = listOf(txtDay1_num,txtDay2_num,txtDay3_num,txtDay4_num,txtDay5_num,txtDay6_num,txtDay7_num)

        chooseDates.forEachIndexed { index, (date, _) ->
            txtDays[index].text = date.getDayOfWeek()
            txtDayNums[index].text = date.getDayNum().toString()
        }

        txtDayNums.forEachIndexed { index, view ->
            view.setOnClickListener {
                currentSelectedDatePosition = index
            }
        }
    }

    private fun initChoiceTime() {
        btnTimeChoices.forEach { view ->
            view.setOnClickListener {
                val checkbox = it as CheckedTextView
                checkbox.isChecked = !checkbox.isChecked
                if (checkbox.isChecked) {
                    chooseDates[currentSelectedDatePosition].second.add(checkbox.tag.toString())
                }
                else {
                    chooseDates[currentSelectedDatePosition].second.remove(checkbox.tag.toString())
                }
            }
        }

        btnTimeChoiceGo.setOnClickListener {
            if (isValidTimeChoice()) {
                //선택한 데이터를 서버에 보내줘야함. chooseDates
                SharedPreferenceController.setTimeTable(this@TimeChoiceActivity,
                    chooseDates[currentSelectedDatePosition].first, chooseDates[currentSelectedDatePosition].second)
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                Toast.makeText(this, "시간은 최소한 3개는 골라야돼!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clearTimetable() {
        btnTimeChoices.forEach { it.isChecked = false }
    }

    private fun loadTimetableFromMemory() {
        btnTimeChoices
            .filter { chooseDates[currentSelectedDatePosition].second.contains(it.tag.toString()) }
            .forEach { it.isChecked = true }
    }

    private fun isValidTimeChoice(): Boolean {
        return chooseDates.map { it.second.size }.sum() >= MININUM_CHOSSEN_DATE
    }

    private companion object {
        const val MININUM_CHOSSEN_DATE = 3
    }
}

