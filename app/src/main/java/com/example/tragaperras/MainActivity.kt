package com.example.tragaperras

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.feature_random_number.random_number.presentation.RandomNumberScreen
import com.example.feature_random_number.random_number.presentation.RandomNumberViewModel
import com.example.tragaperras.core.presentation.Topbar
import com.example.tragaperras.core.presentation.TopbarState
import com.example.tragaperras.ui.theme.TragaperrasTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TragaperrasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    Column {
                        Topbar(TopbarState("Random number"))
                        RandomNumberScreen(RandomNumberViewModel())
                    }
                }
            }
        }
    }
}