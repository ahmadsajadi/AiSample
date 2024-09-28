package com.example.aisample.main_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.aisample.common.chat.ReactionType
import com.example.aisample.common.chat.UserReaction

@Composable
fun ContextMenus(
    allowedReactions: List<Int>,
    userReaction: UserReaction,
    showReactionBox: Boolean,
    onDismiss: () -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    val reactionsOffset = DpOffset(x = 0.dp, y = 70.dp)
    val offset =  DpOffset(x = screenWidth/3, y =(-20).dp)

    Column {
//        MessageOptionContextMenu(
//            showReactionBox = showReactionBox,
//            offset = reactionsOffset,
//            onDismiss = onDismiss
//        )

        ReactionBoxContextMenu(
            allowedReactions = allowedReactions,
            userReaction = userReaction,
            showReactionBox = showReactionBox,
            offset = offset,
            onDismiss = onDismiss
        )
    }

}

@Composable
fun ReactionBoxContextMenu(
    allowedReactions: List<Int>,
    userReaction: UserReaction,
    showReactionBox: Boolean,
    offset: DpOffset,
    onDismiss: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    MaterialTheme(
        shapes = MaterialTheme.shapes.copy(extraSmall = RoundedCornerShape(16.dp)),
        colorScheme = MaterialTheme.colorScheme.copy(
            surface = Color.White,
            surfaceTint = Color.White
        )
    )
    {
        Surface(
            tonalElevation = 8.dp,
            color = Color.Blue,
            contentColor = Color.White,
            shape = RoundedCornerShape(20.dp)
        ) {
            DropdownMenu(
                modifier = Modifier
                    .width(200.dp)
                    .background(color = Color.Transparent),
                expanded = showReactionBox,
                offset = offset,
                onDismissRequest = onDismiss,
            ) {
                Row {
                    val reactions = allowedReactions.toMutableList()
                    reactions.removeLast()
                    ReactionsView(
                        allowedReactions = reactions,
                        userReaction = null,
                        onReaction = {
                            expanded = true
                        },
                        onDismissRequest = { }
                    )
                    Icon(
                        if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = "expanded",
                        Modifier.clickable {
                            expanded = expanded.not()
                        })
                }

                AnimatedVisibility(visible = expanded) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ReactionsView(
                            allowedReactions = allowedReactions,
                            userReaction = null,
                            onReaction = {},
                            onDismissRequest = { }
                        )
                        ReactionsView(
                            allowedReactions = allowedReactions,
                            userReaction = null,
                            onReaction = {},
                            onDismissRequest = { }
                        )
                    }
                }
            }
        }
    }

}

@Composable
fun MessageOptionContextMenu(showReactionBox: Boolean, offset: DpOffset, onDismiss: () -> Unit) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    MaterialTheme(
        shapes = MaterialTheme.shapes.copy(extraSmall = RoundedCornerShape(16.dp)),
        colorScheme = MaterialTheme.colorScheme.copy(
            surface = Color.White,
            surfaceTint = Color.White
        )
    )
    {
        Surface(
            tonalElevation = 8.dp,
            color = Color.Blue,
            contentColor = Color.White,
            shape = RoundedCornerShape(20.dp)
        ) {
            DropdownMenu(
                modifier = Modifier
                    .width(200.dp)
                    .background(color = Color.Transparent),
                expanded = showReactionBox,
                offset = offset,
                onDismissRequest = onDismiss,
            ) {
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .background(Color.Blue)
                )
            }
        }
    }
}

@Composable
fun ContextMenu() {
    var expanded by remember { mutableStateOf(true) }

    // Anchor for the menu (could be any component)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .clickable { expanded = true }
    ) {
        BasicText(text = "Tap to Open Context Menu")

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Option 1", color = Color.Blue) },
                onClick = {
                    expanded = false
                    // Handle Option 1 click
                }
            )
            DropdownMenuItem(
                text = { Text("Option 2", color = Color.Blue) },
                onClick = {
                    expanded = false
                    // Handle Option 2 click
                }
            )
            DropdownMenuItem(
                text = { Text("Option 3", color = Color.Blue) },
                {
                    expanded = false
                    // Handle Option 3 click
                }
            )
        }
    }
}

@Preview
@Composable
fun ReactionBoxPreview() {
    val userReaction = UserReaction()
    userReaction.reaction = ReactionType.HAPPY.value
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            ContextMenu()
//            ReactionBox(
//                allowedReactions = arrayListOf(1, 2, 3, 4, 5, 6),
//                userReaction = userReaction
//            )
        }
    }

}