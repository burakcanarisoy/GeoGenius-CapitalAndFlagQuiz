package burak.android.countryquiz

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import burak.android.countryquiz.ui.theme.CountryQuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContent {
            CountryQuizTheme {
                val networkViewModel: NetworkViewModel = viewModel()
                val isConnected by networkViewModel.isConnected.observeAsState(true)
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (isConnected){
                        Navigation()
                    }else{
                        NoInternetScreen()
                    }
                }
            }
        }
    }
}

