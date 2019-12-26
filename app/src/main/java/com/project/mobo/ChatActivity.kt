package com.project.mobo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.sendbird.android.*
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {

    //채널(그룹 채널)->1:n, n:m
    private var channel: GroupChannel? = null
    //어댑터
    private val adapter = Adapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)


        //채널Url을 받아오는데 이것이 null이면 run을 실행하는 코드
        val channelUrl = intent.getStringExtra("channelUrl") ?: run {
            Toast.makeText(this, "채널 url 을 대령하거라.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        //채널을 초기화 하는 코드
        initChannel(channelUrl)
        //채널 리스트(채팅방): 초기화 하는 코드
        //initChatList()
        //채팅방 메세지를 초기화 하는 코드
        initSendMessage()
        //채널에서 이벤트 핸들러를 통해 사용자의 반응을 통해
        //SendBird 서버에서 채널에서 발생하는 특정 이벤트에 대한 정보를 수신하고 검색하려면을 호출하여 고유 한 사용자 정의 ID로 채널 이벤트 핸들러를 등록
        initChannelEventHandler()

    }

    //채팅 Url 에러확인 -> 이상없으면 channel에 이전 메세지 기록을 보여줌.
    private fun initChannel(url: String) {
        GroupChannel.getChannel(url) { channel, e ->
            if (e != null) {
                Log.e(MoBoApplication.LOG_HEAD, "getChannel 에러 $e")
                Toast.makeText(this, "때끼 채널이 이상하다 이말이야.", Toast.LENGTH_SHORT).show()
                finish()
            }
            Log.d(MoBoApplication.LOG_HEAD, "getChannel $channel")
            this.channel = channel
            loadPreviousMessages()
        }
    }

    //이전 대화목록을 보여주는 코드
    private fun loadPreviousMessages() {
        channel?.let { channel ->
            channel.createPreviousMessageListQuery()
                .load { list, e ->
                    if (e != null) {
                        Log.e(MoBoApplication.LOG_HEAD, "createPreviousMessageListQuery 에러 $e")
                    }
                    Log.d(MoBoApplication.LOG_HEAD, "createPreviousMessageListQuery $list")
                    // 다양한 메시지를 처리하지 못합니다. 주의하세요. 매우 제한적인 사용법입니다.
                    adapter.setMessagesWithNotify(list.map { it as UserMessage })
                }
        }
    }

    //메시지 버튼
    private fun initSendMessage() {
        sendChatButton.setOnClickListener {
            // groupChannel 이 null 이라는 것은, 오류이다.
            channel?.let { channel ->
                val sendMessage = sendChatMessageEditText.text.toString()

                // 만약 보내는 메시지가 없을 경우 메시지를 안보낼 거임
                if (sendMessage.isBlank()) return@setOnClickListener

                Log.d(MoBoApplication.LOG_HEAD, "sendUserMessage 전송 시도 $sendMessage")
                channel.sendUserMessage(UserMessageParams().apply {
                    setMessage(sendMessage)
                }) { message, e ->
                    if (e != null) {
                        Log.e(MoBoApplication.LOG_HEAD, "sendUserMessage 에러 $e")
                    }
                    Log.d(MoBoApplication.LOG_HEAD, "sendUserMessage $message")
                    adapter.addMessageWithNotify(message as UserMessage)
                    // 새로운 채팅이 추가되면 맨 아래로 내려주자.
                    chatList.scrollToPosition(adapter.itemCount - 1)
                }
            }
        }
    }

    //채널 이벤트 핸들러
    private fun initChannelEventHandler() {
        SendBird.addChannelHandler(
            SendbirdConstant.CHAT_CHANNEL_EVENT_HANDLER_ID,
            object : SendBird.ChannelHandler() {
                override fun onMessageReceived(channel: BaseChannel?, message: BaseMessage?) {
                    when (message) {
                        is UserMessage -> {
                            Log.d(MoBoApplication.LOG_HEAD, "받은 메시지 : [${channel}] $message")
                            adapter.addMessageWithNotify(message)
                            // 새로운 채팅이 추가되면 맨 아래로 내려주자.
                            chatList.scrollToPosition(adapter.itemCount - 1)
                        }
                        else -> {
                            Log.e(
                                MoBoApplication.LOG_HEAD,
                                "다루지 않은 메시지... 뭐지? [${channel}] $message"
                            )
                        }
                    }
                }
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        // UI 가 꺼지면 더이상 메시지를 받을 필요가 없다.
        SendBird.removeChannelHandler(SendbirdConstant.CHAT_CHANNEL_EVENT_HANDLER_ID)
    }

    //
    //region RecyclerView

    private inner class Adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        private var messages: List<UserMessage> = listOf()

        fun setMessagesWithNotify(messages: List<UserMessage>) {
            this.messages = messages
            notifyDataSetChanged()
        }

        fun addMessageWithNotify(message: UserMessage) {
            this.messages += message
            notifyItemChanged(this.messages.size - 1)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return when (viewType) {
                MY_CHAT -> {
                    val view = layoutInflater.inflate(R.layout.rv_mychat_item, parent, false)
                    MyChatViewHolder(view)
                }
                else -> {
                    val view = layoutInflater.inflate(R.layout.rv_yourchat_item, parent, false)
                    OtherChatViewHolder(view)
                }
            }
        }

        override fun getItemCount(): Int {
            return messages.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val message = messages[position]
            when (holder) {
                is MyChatViewHolder -> {
                    holder.onBind(message)
                }
                is OtherChatViewHolder -> {
                    holder.onBind(message)
                }
            }
        }

        override fun getItemViewType(position: Int): Int {
            val message = messages[position]

            return if (isMe(message.sender)) MY_CHAT else OTHER_CHAT
        }

        private fun isMe(sender: Sender): Boolean {
            Log.d(MoBoApplication.LOG_HEAD, "${FakeUserRepo.userId}  ${sender.userId}")
            return FakeUserRepo.userId == sender.userId
        }
    }

    private inner class MyChatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val myChatText: TextView = view.findViewById(R.id.txtMychat)
        private val chatNickNameText: TextView = view.findViewById(R.id.txtMyname)

        fun onBind(message: UserMessage) {
            myChatText.text = message.message
            chatNickNameText.text = message.sender.nickname
        }
    }

    private inner class OtherChatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val otherChatText: TextView = view.findViewById(R.id.txtYourchat)
        private val chatNickNameText: TextView = view.findViewById(R.id.txtYourname)

        fun onBind(message: UserMessage) {
            otherChatText.text = message.message
            chatNickNameText.text = message.sender.nickname
        }
    }

    private companion object {
        const val MY_CHAT = 0
        const val OTHER_CHAT = 1
    }

    //endregion
}
