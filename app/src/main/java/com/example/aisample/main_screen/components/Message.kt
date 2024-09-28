package com.example.aisample.main_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aisample.common.chat.ReactionType
import com.example.aisample.common.chat.UserReaction
import com.example.aisample.common.extention.containsPersianCharacters
import com.example.aisample.common.view.SpeechBubbleShape

// Data model for a chat message with sender name
data class Message(
    val text: String,
    val sender: String,
    val isSentByUser: Boolean,
    val isLastByUser: Boolean = false
)

@Preview
@Composable
fun HistoryPreview() {
    ChatScreen(messages = Constant.messages)
}

@Composable
fun ChatScreen(messages: List<Message>) {
    val listState = rememberLazyListState()
    LaunchedEffect(messages.size) {
        // Scroll to the bottom when a new message is added
        listState.animateScrollToItem(messages.size)
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = listState,
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(messages.size) { index ->
            val currentMessage = messages[index]
            val previousMessage = messages.getOrNull(index - 1)
            // Show sender only if it's the first message in a group
            val showSender = previousMessage?.sender != currentMessage.sender
            MessageItem(message = currentMessage, showSender = showSender)
        }
    }
}

@Composable
fun MessageItem(message: Message, showSender: Boolean) {
    val userReaction = UserReaction()
    userReaction.reaction = ReactionType.HAPPY.value
    var showReactionBox by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                showReactionBox = true
            }
            .padding(8.dp),
        contentAlignment = if (message.isSentByUser) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Column(
            Modifier
                .background(
                    color = if (message.isSentByUser) Color.Blue else Color.LightGray,
                    shape = if (message.isLastByUser) SpeechBubbleShape() else MaterialTheme.shapes.medium
                )
                .widthIn(100.dp, 300.dp)
        ) {
            if (showSender && message.isSentByUser.not()) {
                SenderView(
                    message = message.sender,
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(horizontal = 8.dp)
                )
            }
            val align =
                if (message.text.containsPersianCharacters()) Alignment.End else Alignment.Start
            MessageView(
                message = message.text, isSentByUser = message.isSentByUser,
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 16.dp)
                    .align(align)
                    .padding(horizontal = 8.dp)
            )
        }

        ContextMenus(
            allowedReactions = arrayListOf(1, 2, 3, 4, 5, 6),
            userReaction = userReaction,
            showReactionBox = showReactionBox
        ) {
            showReactionBox = false
        }

    }
}

@Composable
fun MessageView(
    message: String,
    isSentByUser: Boolean,
    modifier: Modifier,
) {
    Text(
        text = message,
        color = if (isSentByUser) Color.White else Color.Black,
        modifier = modifier,
        textAlign = TextAlign.Right,// Align sender's name to the right
    )
}

@Composable
fun SenderView(message: String, modifier: Modifier) {
    Text(
        text = message,
        color = Color.Blue,
        modifier = modifier,
        textAlign = TextAlign.End,// Align sender's name to the right
    )
}



