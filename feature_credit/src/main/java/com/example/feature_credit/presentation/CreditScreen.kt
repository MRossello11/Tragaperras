package com.example.feature_credit.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddCreditScreen(
    creditViewModel: CreditViewModel
) {
    val uiState by creditViewModel.uiState.collectAsStateWithLifecycle()

    val creditOptions = listOf(1, 5, 10, 20, 50, 100)

    val lifecycleState by LocalLifecycleOwner.current.lifecycle.currentStateFlow.collectAsState()

    LaunchedEffect(lifecycleState) {
        when (lifecycleState) {
            Lifecycle.State.RESUMED -> {
                creditViewModel.retrieveCredit()
            }
            else -> { /* other lifecycle states are not used */ }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Current Credit: ${uiState.currentCredit}", style = MaterialTheme.typography.labelLarge)
        Text("Credit to Add: ${uiState.addedCredit}", style = MaterialTheme.typography.labelLarge)

        Text("Add Credit", style = MaterialTheme.typography.labelMedium)

        // Six buttons for predefined values
        FlowRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            creditOptions.forEach { value ->
                Button(
                    onClick = {
                        creditViewModel.onUpdateAddedCredit(value)
                    },
                    shape = RoundedCornerShape(12.dp),
                    content = {
                        Text(text = "$value")
                    }
                )
            }
        }

        // Optional: Finalize button
        Button(
            onClick = creditViewModel::onUpdateCredit,
            enabled = uiState.addedCredit > 0,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add to Balance")
        }

        // Optional: Reset button
        OutlinedButton(
            onClick = creditViewModel::onReset,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reset")
        }
    }
}