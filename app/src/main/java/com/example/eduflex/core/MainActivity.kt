package com.example.eduflex.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.eduflex.core.ui.navigation.EduFlexNavHost
import com.example.eduflex.core.ui.theme.EduFlexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EduFlexTheme {
                EduFlexNavHost()
            }
        }
    }
}