package com.example.aisample.common.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun LazyColumnWithDualContextMenu() {
    val items = remember { List(20) { "Item $it" } }
    var expandedItemIndex: Int by remember { mutableIntStateOf(-1) } // Track expanded item

    LazyColumn {
        itemsIndexed(items) { index, item ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        // Toggle expansion of Context Menu A for the current item
                        expandedItemIndex = if (expandedItemIndex == index) -1 else index
                    }
            ) {
                Text(text = item)

                // Bind ContextMenuA and ContextMenuB using ConstraintLayout
                AnimatedVisibility(visible = expandedItemIndex == index) {
                    ConstraintLayout(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        val (menuA, menuB) = createRefs()

                        // Context Menu A (Expandable)
                        ContextMenuA(
                            isExpanded = true,
                            modifier = Modifier.constrainAs(menuA) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                width = Dimension.fillToConstraints
                            },
                            onExpandToggle = {
                                expandedItemIndex = if (expandedItemIndex == index) -1 else index
                            }
                        )

                        // Context Menu B (Always visible under Context Menu A)
                        ContextMenuB(
                            modifier = Modifier.constrainAs(menuB) {
                                top.linkTo(menuA.bottom) // ContextMenuB is attached to the bottom of ContextMenuA
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                width = Dimension.fillToConstraints
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ContextMenuA(isExpanded: Boolean, modifier: Modifier = Modifier, onExpandToggle: () -> Unit) {
    Column(modifier = modifier) {
        // Expand/collapse button for Context Menu A
        Button(onClick = onExpandToggle) {
            Text(text = if (isExpanded) "Collapse Menu A" else "Expand Menu A")
        }

        // Only show menu items if expanded
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { onExpandToggle() }
        ) {
            DropdownMenuItem(text = { Text("Action 1") }, onClick = { /* Do something */ })
            DropdownMenuItem(onClick = { /* Do something */ }, text = {
                Text("Action 2")
            })
        }
    }
}

@Composable
fun ContextMenuB(modifier: Modifier = Modifier) {
    // Context Menu B (always visible)
    Column(modifier = modifier) {
        DropdownMenu(
            expanded = true, // Always true for Context Menu B
            onDismissRequest = { /* Menu B can't be dismissed */ }
        ) {
            DropdownMenuItem(text = { Text("Menu B Action 1") }, onClick = { /* Do something */ })
            DropdownMenuItem(onClick = { /* Do something */ }, text = {
                Text("Menu B Action 2")
            })
        }
    }
}