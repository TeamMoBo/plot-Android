package com.project.mobo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_chatting.*

class ChattingActivity : AppCompatActivity() {

    companion object {
        private val TAG = "ClassName"
    }

    private lateinit var rvMainChatting: RecyclerView
    private lateinit var ChattingAdapter: ChattingAdapter

    private val Chattingchatting = Chattingchatting()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatting)

        //Write a message to the database
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference("a")
        myRef.setValue("안녕 반가워!")
        //init()

        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue(String::class.java)
                textView.text = "$value"
                Log.d(TAG, "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })


    }

/*
    private fun init(){
        rvMainChatting = findViewById(R.id.rvMainChatting)

        ChattingAdapter = ChattingAdapter(this)

        rvMainChatting.adapter = ChattingAdapter

        rvMainChatting.layoutManager = LinearLayoutManager(this)

        ChattingAdapter.data= Chattingchatting.getRepoList()

        ChattingAdapter.notifyDataSetChanged()
    }
 */

}