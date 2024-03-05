package com.example.codelabnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.codelabnavigation.ui.theme.CodeLabNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodeLabNavigationTheme {
                setContent {
                    CupCakeApp()
                }
            }
        }
    }
}


