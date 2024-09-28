package com.example.aisample.main_screen

import androidx.compose.runtime.Composable
import com.example.aisample.main_screen.components.ChatScreen
import com.example.aisample.main_screen.components.Constant

@Composable
fun MainScreen(){
//    ChatScreen(messages = Constant.messages)
    ChatScreen(messages = Constant.messages)
//    LazyColumnWithDualContextMenu()
}