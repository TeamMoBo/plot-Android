package com.project.mobo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.project.mobo.api.HaveTicketData
import com.project.mobo.api.TicketBuyData
import com.project.mobo.api.UserServiceImpl
import com.project.mobo.api.safeEnqueue
import kotlinx.android.synthetic.main.activity_pay_choice.*

class PayChoiceActivity : AppCompatActivity() {
    lateinit var ticketData: HaveTicketData
    lateinit var buyData : TicketBuyData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_choice)

        var totalPrice = (txtPayChoiceTotalPrice.text.toString()).toInt()
        var ticketNum = txtTicketNum.text.toString().toInt()
        var popcornNum = txtPopcornNum.text.toString().toInt()

        imgPayChoiceSelectProductMinus.setOnClickListener {
            if (ticketNum!! >= 1) {
                ticketNum = ticketNum?.dec()
                totalPrice = totalPrice?.minus(4900)

                txtTicketNum.text = ticketNum.toString()
                txtPayChoiceTotalPrice.text = totalPrice.toString()
            }
        }

        imgPayChoiceSelectProductPlus.setOnClickListener {
            ticketNum = ticketNum?.inc()
            totalPrice = totalPrice?.plus(4900)

            txtTicketNum.text = ticketNum.toString()
            txtPayChoiceTotalPrice.text = totalPrice.toString()
        }

        imgPayChoiceSelectPopcornMinus.setOnClickListener {
            if (popcornNum!! >= 1) {
                popcornNum = popcornNum?.dec()
                totalPrice = totalPrice?.minus(4900)

                txtPopcornNum.text = popcornNum.toString()
                txtPayChoiceTotalPrice.text = totalPrice.toString()
            }
        }

        imgPayChoiceSelectPopcornPlus.setOnClickListener {
            popcornNum = popcornNum?.inc()
            totalPrice = totalPrice?.plus(4900)

            txtPopcornNum.text = popcornNum.toString()
            txtPayChoiceTotalPrice.text = totalPrice.toString()
        }

        val callhaveTicketService = UserServiceImpl.haveTicketService.responseHaveTicket(
            //key = SharedPreferenceController.getUserToken(this)
            key = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZHgiOjQwLCJpYXQiOjE1Nzc5NDkzNDYsImV4cCI6MTU3ODU1NDE0NiwiaXNzIjoibW9ib21hc3RlciJ9.dwKFFXHdDhkb8WW25BSMyig5DFzUlKPQ-WE1lzO4JBc"
        )

        callhaveTicketService.safeEnqueue {
            if (it.isSuccessful) {
                ticketData = it.body()!!.data
                txtPayChoiceTicketNum.text = ticketData.userTicket.toString()
                txtPayChoicePopcornNum.text = ticketData.userPopcorn.toString()
                Toast.makeText(this, "success_receive", Toast.LENGTH_SHORT).show()
            }
        }


        btnPayChoicePay.setOnClickListener {
            val callTicketBuy = UserServiceImpl.ticketBuyService.putTicketBuy(
                //key = SharedPreferenceController.getUserToken(this)6
                key = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZHgiOjQwLCJpYXQiOjE1Nzc5NDkzNDYsImV4cCI6MTU3ODU1NDE0NiwiaXNzIjoibW9ib21hc3RlciJ9.dwKFFXHdDhkb8WW25BSMyig5DFzUlKPQ-WE1lzO4JBc",
                ticketBuyData =  TicketBuyData((txtTicketNum.text.toString()).toInt(), (txtPopcornNum.text.toString()).toInt())
            )

            txtPopcornNum.text = popcornNum.toString()
            txtPayChoiceTotalPrice.text = totalPrice.toString()

            callTicketBuy.safeEnqueue {
                if(it.isSuccessful){
//                    var ticketBuyData : TicketBuyData
//                    ticketBuyData = it.body()!!.data
//                    ticketBuyData.userTicket = 1
//                    ticketBuyData.userPopcorn = 1
////                    ticketBuyData.userTicket = (txtTicketNum.text.toString()).toInt()
////                    ticketBuyData.userPopcorn = (txtPopcornNum.text.toString()).toInt()
                    Toast.makeText(this, "success_buy", Toast.LENGTH_SHORT).show()
                    Log.v("ticket", ticketNum.toString())
                    Log.v("popcorn", popcornNum.toString())
                }
            }
        }
    }
}

