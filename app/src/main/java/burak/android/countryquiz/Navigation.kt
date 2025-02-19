package burak.android.countryquiz

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun Navigation(flagViewModel: FlagViewModel = viewModel(),
               capitalViewModel: CapitalViewModel = viewModel(),
               networkViewModel: NetworkViewModel = viewModel(),
               capitalCounterViewModel: CapitalCounterViewModel = viewModel(),
               flagCounterViewModel: FlagCounterViewModel = viewModel())
{

    val isConnected by networkViewModel.isConnected.observeAsState(true)
    if (!isConnected){
        NoInternetScreen()
        return
    }
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "firstscreen") {
        composable(route = "firstscreen"){
            FirstScreen(navController)
        }
        composable(route = "capitalquiz"){
            CapitalQuestion(capitalViewModel, navController, capitalCounterViewModel)
        }
        composable(route = "flagquiz"){
            FlagQuestion(flagViewModel, navController, flagCounterViewModel)
        }
        composable(route = "capitalcongrats"){
            CapitalCongratulationsScreen(navController)
        }
        composable(route = "flagcongrats"){
            FlagCongratulationsScreen(navController)
        }

    }
}