package com.project.mobo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_pay_choice.*

class PayChoiceActivity : AppCompatActivity() {
//    val total: TextView = findViewById(R.id.txtPayChoiceTotalPrice)
//    val price: String? = total.toString()
//    private var paySum: Int? = price?.toInt()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_choice)

//        var ticketNum: Int = txtTicketNum.text.toString().toInt()
//        var popcornNum: Int = txtPopcornNum.text.toString().toInt()
//
//        imgPayChoiceSelectProductMinus.setOnClickListener {
//            if (ticketNum <= 1) {
//                ticketNum -= 1
//                paySum = paySum?.minus(4900)
//            }
//        }
//
//        imgPayChoiceSelectProductPlus.setOnClickListener {
//            ticketNum += 1
//            paySum = paySum?.plus(4900)
//        }
//
//        imgPayChoiceSelectPopcornMinus.setOnClickListener {
//            if (popcornNum <= 1) {
//                popcornNum -= 1
//                paySum = paySum?.minus(4900)
//            }
//        }
//
//        imgPayChoiceSelectPopcornPlus.setOnClickListener {
//            popcornNum += 1
//            paySum = paySum?.plus(4900)
//        }
    }
}

private fun initUI() {
    //txtPayChoiceTotalPrice.text = pay_sum.toString()


}
