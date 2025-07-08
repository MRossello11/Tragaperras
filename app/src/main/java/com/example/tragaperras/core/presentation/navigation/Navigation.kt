package com.example.tragaperras.core.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.core.presentation.Topbar
import com.example.core.presentation.TopbarState
import com.example.feature_credit.presentation.AddCreditScreen
import com.example.feature_credit.presentation.CreditViewModel
import com.example.feature_random_number.random_number.presentation.RandomNumberScreen
import com.example.feature_random_number.random_number.presentation.RandomNumberViewModel
import com.example.tragaperras.core.commons.Constants

enum class Destination(
    val route: String,
    val contentDescription: String
) {
    RANDOM_NUMBER_GAME(Constants.Routes.RANDOM_NUMBER_ROUTE, "Game"),
    CREDIT(Constants.Routes.CREDIT_ROUTE, "Credit"),
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Navigation(

) {
    val navController = rememberNavController()
    val startDestination = Destination.RANDOM_NUMBER_GAME
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Topbar(TopbarState(getDestinationLabel(Destination.entries[selectedDestination])))
        },
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                Destination.entries.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = selectedDestination == index,
                        onClick = {
                            navController.navigate(route = destination.route)
                            selectedDestination = index
                        },
                        icon = {
                            Icon(
                                getDestinationIcon(destination),
                                contentDescription = destination.contentDescription
                            )
                        },
                        label = { Text(getDestinationLabel(destination)) }
                    )
                }
            }
        }
    ) { contentPadding ->
        AppNavHost(navController, startDestination)
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Destination
) {
    NavHost(
        navController,
        startDestination = startDestination.route
    ) {
        Destination.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    Destination.RANDOM_NUMBER_GAME -> {
                        val randomNumberViewModel = viewModel<RandomNumberViewModel>()
                        RandomNumberScreen(randomNumberViewModel)
                    }
                    Destination.CREDIT -> {
                        val creditViewModel = hiltViewModel<CreditViewModel>()
                        AddCreditScreen(creditViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun getDestinationLabel(destination: Destination): String {
    return when (destination) {
        Destination.RANDOM_NUMBER_GAME -> stringResource(com.example.core.R.string.random_number_label)
        Destination.CREDIT -> stringResource(com.example.core.R.string.credit_label)
    }
}

@Composable
fun getDestinationIcon(destination: Destination): Painter {
     return when (destination) {
        Destination.RANDOM_NUMBER_GAME -> painterResource(com.example.core.R.drawable.casino)
        Destination.CREDIT -> painterResource(com.example.core.R.drawable.money)
    }
}