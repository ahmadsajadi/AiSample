package com.example.aisample.main_screen.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.aisample.common.chat.ReactionMapper
import com.example.aisample.common.chat.UserReaction

@Composable
fun ReactionsView(allowedReactions : List<Int>,
                  userReaction: UserReaction? = null,
                  onReaction: (String) -> Unit = {},
                  onDismissRequest: () -> Unit,){
    val direction = if(allowedReactions.size > 3 ) LayoutDirection.Ltr else LayoutDirection.Rtl
    val scrollState = rememberScrollState()
    val itemWidth = 30.dp  // Width of each item
    val itemPadding = 4.dp  // Padding around each item
    val itemCount = 30  // Number of items

    val totalContentWidth = itemCount * (itemWidth.value + itemPadding.value * 2)
    val showMore = remember {
        mutableStateOf(allowedReactions.size > 5)
    }
    LaunchedEffect(scrollState.value) {
        if (scrollState.value == scrollState.maxValue){
            showMore.value = false
        } else {
            showMore.value = true
        }

    }
    CompositionLocalProvider(LocalLayoutDirection provides direction) {
        Row(
            Modifier.horizontalScroll(scrollState)
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            allowedReactions.forEach {
                val reaction = ReactionMapper.reactionToCode(ReactionMapper.idToType(it))
                ReactionItemView(isUserReaction(userReaction?.reaction, reaction),reaction,){
                    onReaction(reaction)
                    onDismissRequest()
                }

            }
        }

    }
}

@Composable
fun ReactionItemView(isSelected : Boolean,reaction : String,onClick: () -> Unit){
    val  default= Modifier.clickable { onClick() }
    val  selectedReactionModifier = Modifier
        .size(35.dp)
        .clickable { onClick() }
        .background(
            color = Color.Yellow,
            shape = RoundedCornerShape(8.dp)
        )

    Box(modifier = if (isSelected)selectedReactionModifier  else default , contentAlignment = Alignment.Center){
       ReactionText(text = reaction )
    }

}

@Composable
fun ReactionText(
    modifier: Modifier = Modifier, text: String = "Undefined",
    color: Color = Color.Black,
) {

    Text(
        text,
        fontStyle = FontStyle.Normal,
        textAlign = TextAlign.Center,
        color = color,
        fontWeight = FontWeight.Normal,
        fontSize = TextUnit(17f, TextUnitType.Sp),
        modifier = modifier
    )
}

fun isUserReaction(reaction: Int?, reactionCode: String): Boolean {
    return reaction != null && reaction == ReactionMapper.codeToReaction(reactionCode).value
}