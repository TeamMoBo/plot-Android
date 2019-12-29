package com.project.mobo.chat

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.project.mobo.R
import com.project.mobo.common.FirebaseChatConstant
import com.project.mobo.common.addChildEventListener
import kotlinx.android.synthetic.main.activity_chatting.*

class ChattingActivity : AppCompatActivity() {
    //데이터베이스에 쓰기(데이터베이스의 인스턴스를 검색하고 쓰려는 위치를 참조)
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference
    private lateinit var chatRoomId: String
    private lateinit var chatRoom: DatabaseReference
    private lateinit var chatUsers: DatabaseReference

    private var trueMap = HashMap<String, Boolean>()
    private var falseMap = HashMap<String, Boolean>()

    private lateinit var lm: LinearLayoutManager
    private lateinit var adapter: ChatRoomAdapter


    //TODO: 실제로는 로그인 시점에 받아온 firebase database uid 를 받아서 세팅해야함
    private val uid: String = "jihye"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatting)

        //chatRoomId = intent.getStringExtra(CHATROOM_ID) ?: run {
        //    finish()
        //    return
        //}
        chatRoomId = "something"
        chatRoom = databaseReference
            .child(FirebaseChatConstant.CHATROOMS)
            .child(chatRoomId)
            .child(FirebaseChatConstant.COMMENTS)

        chatUsers = databaseReference
            .child(FirebaseChatConstant.CHATROOMS)
            .child(chatRoomId)
            .child("users")

        trueMap[uid] = true
        falseMap[uid] = false
        chatUsers.push().setValue(trueMap)

        //초기화
        init()
        //전송 버튼 클릭시 메세지를 파이어베이스 Realtime DataBase 에 쓰는 코드
        setupSendButton()
        //메시지를 보여주기 위한 리스트 어댑터 생성 및 세팅

        //데이터 읽어오는 것
        setupChatRoomUpdated()
    }

    private fun init() {
        lm = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        adapter = ChatRoomAdapter()
        rvChatList.layoutManager = lm
        rvChatList.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        chatUsers.push().setValue(falseMap)
    }

    override fun onPause() {
        super.onPause()
        chatUsers.push().setValue(falseMap)
    }

    override fun onResume() {
        super.onResume()
        chatUsers.push().setValue(trueMap)
    }


    private fun setupSendButton() {
        btnSend.setOnClickListener {
            val msg = edtMessage.text.toString()
            edtMessage.setText("")
            if (msg.isBlank()) return@setOnClickListener

            chatRoom
                .child(generateCommentId())
                .setValue(
                    ChatData(
                        message = msg,
                        timestamp = getCurrentTime(),
                        uid = uid
                    )
                )
        }
    }

    private fun setupChatRoomUpdated() {
        chatRoom
            .addChildEventListener(
                onChildAdded = { snap, _ ->
                    adapter.addItem(snap.getValue(ChatData::class.java)!!)
//                    snap.getValue(ChatData::class.java)?.let {
//                        Log.d("siba", "firebase data siba : $it")
//                    } ?: Log.e("siba", "firebase go to hell fuck")
                }
            )
    }

    companion object {
        const val CHATROOM_ID = "chat_room_id"
    }

    private fun generateCommentId(): String {
        return "${uid}-${getCurrentTime()}"
    }

    private fun getCurrentTime(): String {
        return System.currentTimeMillis().toString()
    }
}


