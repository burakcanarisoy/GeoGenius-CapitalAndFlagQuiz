package burak.android.countryquiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController

@Composable
fun FlagDifficultyOptions(navController: NavController, flagViewModel: FlagViewModel){
    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.backgroundimage),
            contentDescription = "Background Image",
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Choose the difficulty level;",
                    fontFamily = FontFamily.Cursive,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top= 16.dp, bottom = 24.dp),
                    style = TextStyle(shadow = Shadow(color = Color.White.copy(alpha = 0.8f), offset = Offset(2f, 2f), blurRadius = 6f))
                )
            }
            ElevatedButton(
                onClick = {
                    flagViewModel.setDifficulty("easy")
                    navController.navigate("flagquiz")},
                modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        painter = painterResource(R.drawable.easylevel),
                        contentDescription = "Easy Level Icon",
                        modifier = Modifier.align(Alignment.CenterStart).size(24.dp),
                        tint = Color.Unspecified // Use icon's default color
                    )
                    Text(
                        text = "Easy",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            ElevatedButton(onClick = {
                flagViewModel.setDifficulty("medium")
                navController.navigate("flagquiz")
            },
                modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Box(modifier = Modifier.fillMaxWidth()){
                    Icon(
                        painter = painterResource(R.drawable.mediumlevel),
                        contentDescription = "Medium Level Icon",
                        modifier = Modifier.align(Alignment.CenterStart).size(24.dp),
                        tint = Color.Unspecified
                    )
                    Text(
                        text = "Medium",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            ElevatedButton(onClick = {
                flagViewModel.setDifficulty("hard")
                navController.navigate("flagquiz")
            },
                modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Box(modifier = Modifier.fillMaxWidth()){
                    Icon(
                        painter = painterResource(R.drawable.hardlevel),
                        contentDescription = "Hard Level Icon",
                        modifier = Modifier.align(Alignment.CenterStart).size(24.dp),
                        tint = Color.Unspecified
                    )
                    Text(
                        text = "Hard",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            ElevatedButton(onClick = {
                flagViewModel.setDifficulty("all")
                navController.navigate("flagquiz")
            },
                modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Box(modifier = Modifier.fillMaxWidth()){
                    Icon(
                        painter = painterResource(R.drawable.allcountries),
                        contentDescription = "All Countries Icon",
                        modifier = Modifier.align(Alignment.CenterStart).size(24.dp),
                        tint = Color.Unspecified

                    )
                    Text(
                        text = "All Countries",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }

    }
}