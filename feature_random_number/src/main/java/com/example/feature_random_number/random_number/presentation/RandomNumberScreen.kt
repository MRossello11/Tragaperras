package com.example.feature_random_number.random_number.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.feature_random_number.R
import com.example.feature_random_number.random_number.presentation.GuessOutcomeEvent.RandomNumberGuessed
import com.example.feature_random_number.random_number.presentation.GuessOutcomeEvent.RandomNumberHigher
import com.example.feature_random_number.random_number.presentation.GuessOutcomeEvent.RandomNumberLower

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RandomNumberScreen(
    randomNumberViewModel: RandomNumberViewModel
) {

    val uiState by randomNumberViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
        Column(
            modifier = Modifier.padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))
            // number field
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 100.dp)
                    .height(50.dp),
                contentAlignment = Alignment.Center
            ) {
                uiState.randomNumber?.let {
                if (uiState.showNumber) {
                    Text(
                        text = it.toString(),
                        fontSize = 24.sp
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.Black)
                    )
                }
            }
            }


            Spacer(modifier = Modifier.height(20.dp))

            // user interaction part
            Slider(
                value = uiState.guess.toFloat(),
                onValueChange = randomNumberViewModel::changeSelectedNumber,
                valueRange = 0f..100f,
                steps = 100
            )
            Text(text = uiState.guess.toString())

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = when (uiState.guessOutcomeEvent) {
                    RandomNumberHigher  -> stringResource(R.string.random_number_higher)
                    RandomNumberLower   -> stringResource(R.string.random_number_lower)
                    RandomNumberGuessed -> stringResource(R.string.random_number_guessed)
                    else -> "" // don't show anything
                })
            }

            Button(
                onClick = randomNumberViewModel::submitGuess,
                content = {
                    Text(text = stringResource(R.string.send))
                }
            )

            Spacer(modifier = Modifier.height(10.dp))



            Button(
                onClick = randomNumberViewModel::reset,
                content = {
                    Text(text = stringResource(R.string.reset))
                }
            )
        }
    }
}