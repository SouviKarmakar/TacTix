package com.souvik.tactix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.souvik.tactix.ui.theme.TacTixTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TacTixTheme {
                val viewModel = viewModel<GameViewModel>()
                GameScreen(viewModel = viewModel)

            }
        }
    }
}


/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TacTixTheme {
        Greeting("Android")
    }
}*/
