package burak.android.countryquiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay

@Composable
fun FlagQuestion(
    viewModel: FlagViewModel = viewModel(),
    navController: NavController,
    flagCounterViewModel: FlagCounterViewModel
) {
    val question = viewModel.currentQuestion.value
    val options = viewModel.options.value
    val correctCounter by flagCounterViewModel.flagCounter
    var selectedOption by remember { mutableStateOf<String?>(null) }
    var isCorrectAnswer by remember { mutableStateOf<Boolean?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    val totalQuestions = viewModel.getFilteredCountries().size

    if (correctCounter == totalQuestions){
        viewModel.resetAtTheEnd()
        flagCounterViewModel.flagCorrectCounterReset()
        navController.navigate("flagcongrats")
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.backgroundimage),
            contentDescription = "Background Image",
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop
        )

        Column(modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            question?.let {
                Card(modifier = Modifier.padding(32.dp).height(160.dp)) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        Text(
                            text = "Which country's flag is this?",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                            AsyncImage(
                                model = it.flags.png,
                                contentDescription = "Flag",
                                modifier = Modifier.size(120.dp),
                                alignment = Alignment.Center
                            )
                        }
                    }
                }

                options.forEach { option ->
                    val isCorrect = option == question.name.common
                    val cardBackgroundColor = when {
                        selectedOption == option && isCorrectAnswer == true -> Color.Green
                        selectedOption == option && isCorrectAnswer == false -> Color.Red
                        else -> Color.LightGray
                    }

                    Card(modifier = Modifier.padding(8.dp).fillMaxWidth().height(48.dp).clickable {
                                if (selectedOption == null) {
                                    selectedOption = option
                                    isCorrectAnswer = isCorrect
                                }
                            },
                        colors = CardDefaults.cardColors(containerColor = cardBackgroundColor)
                    ) {
                        Text(
                            text = option,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxSize().wrapContentHeight(Alignment.CenterVertically)
                        )
                    }
                }

                LaunchedEffect(selectedOption) {
                    if (selectedOption != null) {
                        delay(100)
                        if (isCorrectAnswer == true) {
                            flagCounterViewModel.flagCorrectCounterIncrement()
                            viewModel.generateNewQuestion()
                        } else if (isCorrectAnswer == false) {
                            showDialog = true
                        }
                        selectedOption = null
                        isCorrectAnswer = null
                    }
                }
            }

            Text(
                text = "Correct : $correctCounter/$totalQuestions",
                fontFamily = FontFamily.Cursive,
                fontSize = 48.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp),
                style = TextStyle(shadow = Shadow(color = Color.White.copy(alpha = 0.8f), offset = Offset(2f, 2f), blurRadius = 6f))
            )
        }
    }
    if (showDialog){
        AlertDialog(
            onDismissRequest = {},
            title = { Text(text = "Wrong Answer") },
            text = { Text(text = "Do you want to try again?") },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.resetQuestions()
                    flagCounterViewModel.flagCorrectCounterReset()
                    showDialog = false
                }) {
                    Text("Try again")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    navController.navigate("firstscreen"){
                        popUpTo(navController.graph.startDestinationId){inclusive = true}
                    }
                    viewModel.resetQuestions()
                    flagCounterViewModel.flagCorrectCounterReset()
                    showDialog = false
                }) {
                    Text("Go home page")
                }
            }
        )
    }
}


