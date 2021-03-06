package com.project.mobo.chat

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.project.mobo.dialog.MatchingDialogActivity
import com.project.mobo.R
import com.project.mobo.SharedPreferenceController
import com.project.mobo.common.FirebaseChatConstant
import com.project.mobo.common.addChildEventListener
import com.project.mobo.util.registerEvent
import kotlinx.android.synthetic.main.activity_chatting.*
import java.util.*
import kotlin.collections.HashMap

class ChattingActivity : AppCompatActivity() {
    //데이터베이스에 쓰기(데이터베이스의 인스턴스를 검색하고 쓰려는 위치를 참조)
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference
    private lateinit var createdTime: Date

    private lateinit var mchildEventListener: ChildEventListener
    private lateinit var chatRoomId: String
    private lateinit var chatRoom: DatabaseReference
    private lateinit var chatUsers: DatabaseReference

    private var trueMap = HashMap<String, Boolean>()
    private var falseMap = HashMap<String, Boolean>()

    private lateinit var lm: LinearLayoutManager
    private lateinit var adapter: ChatRoomAdapter


    //TODO: 실제로는 로그인 시점에 받아온 firebase database uid 를 받아서 세팅해야함
    //private val uid: String = "gihyun"
    private lateinit var uid: String
    private lateinit var opponentName: String
    private lateinit var opponentImg: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatting)

        uid = intent.getStringExtra("uid")
        chatRoomId = intent.getStringExtra("address")
        opponentName = intent.getStringExtra("opponentName")
        opponentImg = intent.getStringExtra("opponentImg")

        SharedPreferenceController.setUserName(this, opponentName)

        txtName.text = opponentName
        Glide.with(this).load(opponentImg).into(imgProfile)

        //TODO: 채팅방이 만들어진 시간을 기록해야함.
        //chatRoomId = intent.getStringExtra(CHATROOM_ID) ?: run {
        //    finish()
        //    return
        //}
        //chatRoomId = "something"
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
//        chatUsers.push().setValue(trueMap)

        //초기화
        //메시지를 보여주기 위한 리스트 어댑터 생성 및 세팅
        init()

        //전송 버튼 클릭시 메세지를 파이어베이스 Realtime DataBase 에 쓰는 코드
        setupSendButton()

        //데이터 읽어오는 것
        setupChatRoomUpdated()
        rvChatList.scrollToPosition(rvChatList.adapter!!.itemCount - 1) // 첫 접속시 리싸이클러뷰가 상단에 올라가기 때문.

        //타이머 기능
        timer()

        //파이어베이스에서 서버가 넘겨준 user의 이름, 사진 값 불러오기
        //load()
    }

    //TODO: 특정 시간이 지났는지 파악
    //TODO: 일정 시간마다, 화면을 갱신해줘얗 함
    //TODO: 특정 시간 ~ 특정 시간 사이의 시간을, 받아올 수 있어야함. (분 단위) v
    //TODO: 특정 시간으로 부터, 특정 분이 지났을 때, 이벤트를 등록 할 수 있으면 v
    private fun timer() {
//        val timer= Timer("schdule", true)
//
//        timer.schedule(10000, 60000){
//            //if(delay)
//            txtQuiz.text = "이걸로 바뀌지롱ㅎ"
//            imgProcess.setImageDrawable(getDrawable(R.drawable.processbar_second))
//        }

        //액티비티가 만들어진 시간
        createdTime = Date()
//        registerEvent(createdTime, 0.5f){
//            txtQuiz.text=uid+"에 대한 첫인상은 어때?"
//        }

//        registerEvent(createdTime, 0.1f) {
//            //Toast.makeText(this, "1단계", Toast.LENGTH_SHORT).show()
//            txtQuiz.text = "우리 영화 보기 몇 분전에 만날까?"
//            //imgProcess.setImageDrawable(getDrawable(R.drawable.processbar_second))
//        }


        registerEvent(createdTime, 0.1f) {
            //Toast.makeText(this, "3단계", Toast.LENGTH_SHORT).show()
            txtQuiz.text = "서로 인사해!"
            //imgProcess.setImageDrawable(getDrawable(R.drawable.processbar_complete))
        }

        registerEvent(createdTime, 0.2f) {
            //Toast.makeText(this, "2단계", Toast.LENGTH_SHORT).show()
            //txtQuiz.text = uid + "이 영화는 왜 보고 싶어?"
            imgProcess.setImageDrawable(getDrawable(R.drawable.processbar_second))
        }

        registerEvent(createdTime, 0.3f) {
            imgProcess.setImageDrawable(getDrawable(R.drawable.processbar_third))
        }

        registerEvent(createdTime, 0.4f) {
            //            val dialogView = layoutInflater.inflate(R.layout.popup_matching_choice, null)
//            val dialog = AlertDialog.Builder(this)
//                .setView(dialogView)
            imgProcess.setImageDrawable(getDrawable(R.drawable.processbar_complete))
            txtQuiz.text = "영화 끝나고 뭐 먹을래?"
        }

        registerEvent(createdTime, 0.5f) {
            val choice = Intent(this, MatchingDialogActivity::class.java)
            startActivityForResult(choice, 3000)
            //finish()
        }

    }

    private fun init() {
        lm = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        adapter = ChatRoomAdapter(uid)
        rvChatList.layoutManager = lm
        rvChatList.adapter = adapter

//        val now = Calendar.getInstance().time
//        val pattern = SimpleDateFormat("yyyy년 MM월 dd일 HH:mm")
//        val nowDate = pattern.format(now)

        rvChatList.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            rvChatList.scrollToPosition(rvChatList.adapter!!.itemCount - 1)
        } // Keyboard가 레이아웃 가리는 부분을 Recyclerview의 스크롤 위치를 조정시킴

        rvChatList.scrollToPosition(rvChatList.adapter!!.itemCount - 1) // 첫 접속시 리싸이클러뷰가 상단에 올라가기 때문.

    }

    override fun onDestroy() {
        super.onDestroy()
        //     chatUsers.push().setValue(falseMap)
    }

    override fun onPause() {
        super.onPause()
        //       chatUsers.push().setValue(falseMap)
    }

    override fun onResume() {
        super.onResume()
//        chatUsers.push().setValue(trueMap)
    }


    private fun setupSendButton() {
        btnSend.setOnClickListener {
            val msg = edtMessage.text.toString()
            edtMessage.setText("")
            if (msg.isBlank()) return@setOnClickListener


            //val now=System.currentTimeMillis()

            chatRoom
                .child(generateCommentId())
                .setValue(
                    ChatData(
                        message = msg,
                        timestamp = getCurrentTime(),
                        uid = uid
                    )
                )
            rvChatList.scrollToPosition(rvChatList.adapter!!.itemCount - 1) // 아이템을 추가시켰으니 다시 스크롤 조
        }
    }

    private fun setupChatRoomUpdated() {
        chatRoom
            .addChildEventListener(
                onChildAdded = { snap, _ ->
                    adapter.addItem(snap.getValue(ChatData::class.java)!!)
                    rvChatList.scrollToPosition(rvChatList.adapter!!.itemCount - 1)
//                    snap.getValue(ChatData::class.java)?.let {
//                        Log.d("siba", "firebase data siba : $it")
//                    } ?: Log.e("siba", "firebase go to hell fuck")
                },
                onChildChanged = { snap, _ ->
                    adapter.addItem(snap.getValue(ChatData::class.java)!!)
                    rvChatList.scrollToPosition(rvChatList.adapter!!.itemCount - 1)
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
        //return "${uid}-${getCurrentTime()}"
        return "${getCurrentTime()}"
    }

    private fun getCurrentTime(): String {
        return System.currentTimeMillis().toString()
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 3000){
            finish()
        }
    }
}