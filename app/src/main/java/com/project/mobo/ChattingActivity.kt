package com.project.mobo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_chatting.*

class ChattingActivity : AppCompatActivity() {

    //데이터베이스에 쓰기(데이터베이스의 인스턴스를 검색하고 쓰려는 위치를 참조)
    private var databaseReference: DatabaseReference? = null

//    companion object {
//        private val TAG = "ClassName"
//    }

    private lateinit var rvMainChatting: RecyclerView
    private lateinit var ChattingAdapter: ChattingAdapter

    private val Chattingchatting = Chattingchatting()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatting)

        initFirebase()
        setupSendButton()


/*
        //Write a message to the database-데이터베이스에 쓰기(데이터베이스의 인스턴스를 검색하고 쓰려는 위치를 참조)
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference("a") //만든 인스턴스에
        myRef.setValue("안녕 반가워!")
        //init()

        // Read from the database-데이터베이스에서 읽기(만든 참조에 ValueEventListener를 추가해야 함)
        myRef.addValueEventListener(object : ValueEventListener {
            //이 클래스의 onDataChange() 메서드는 리스너가 연결될 때 한 번 트리거된 후 하위 항목을 포함한 데이터가 변경될 때마다 다시 트리거
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue(String::class.java)
                text.text = "$value"
                Log.d(TAG, "Value is: $value")
            }

            //실패시 호출되는 메소드
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
*/


    }
    /**
     * Setup firebase
     */
    private fun initFirebase() {
        //init firebase
        FirebaseApp.initializeApp(applicationContext)

        FirebaseDatabase.getInstance().setLogLevel(Logger.Level.DEBUG)

        //get reference to our db
        databaseReference = FirebaseDatabase.getInstance().reference
    }

    /**
     * 메세지 전송 버튼을 누르면 메세지를 전송하는 것
     */
    private fun setupSendButton() {
        btnSend.setOnClickListener {
            if (!edtMessage.text.toString().isEmpty()) {
                sendData()
            } else {
                Toast.makeText(this, "Please enter a message", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * 메세지를 파베에 보내는 것
     */
    private fun sendData() {
        databaseReference?.child("ChattingUrl")
            ?.child(java.lang.String.valueOf(System.currentTimeMillis()))
            ?.setValue(Message(edtMessage.text.toString()))

//        databaseReference?.child("ChattingUrl")
//            ?.child(java.lang.String.valueOf(System.currentTimeMillis()))
//            ?.setValue(Message(edtMessage.text.toString()))

        //clear the text
        edtMessage.setText("")
    }

    inner class Message {
        constructor() //empty for firebase

        constructor(messageText: String) {
            text = messageText
        }

        var text: String? = null
        var timestamp: Long = System.currentTimeMillis()
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