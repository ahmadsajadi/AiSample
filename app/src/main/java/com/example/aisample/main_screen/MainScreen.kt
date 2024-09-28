package com.example.aisample.main_screen

import androidx.compose.runtime.Composable
import com.example.aisample.common.view.LazyColumnWithDualContextMenu
import com.example.aisample.main_screen.components.Constant
import com.example.aisample.main_screen.components.MessageListView

@Composable
fun MainScreen(){
//    MessageListView(messages = Constant.messages)
    LazyColumnWithDualContextMenu()
}