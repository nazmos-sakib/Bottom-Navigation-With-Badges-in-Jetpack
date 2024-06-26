package com.example.bottom_navigation_jetpack

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomMenuContent(
    val title: String,
    val route: String,
    val iconId: ImageVector,
    val badgeCount: Int = 0
)
