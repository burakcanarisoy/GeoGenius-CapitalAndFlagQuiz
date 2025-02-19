package burak.android.countryquiz

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun FlagCongratulationsScreen(navController: NavController){
    BackHandler {
        navController.popBackStack(route = "firstscreen", inclusive = false)
    }
    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.backgroundimage),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(modifier = Modifier.fillMaxWidth().padding(24.dp)) {
                Text(
                    text = "Perfect! You're a true expert in country flags!\n\uD83C\uDF0D\uD83C\uDFC6",
                    fontFamily = FontFamily.Cursive,
                    fontSize = 56.sp,
                    lineHeight = 64.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top= 16.dp, bottom = 24.dp),
                    style = TextStyle(shadow = Shadow(color = Color.White, offset = Offset(2f, 2f), blurRadius = 4f))
                )
            }
            ElevatedButton(
                onClick = {navController.navigate("firstscreen"){
                    popUpTo(navController.graph.startDestinationId){inclusive = true}
                } },
                shape = RectangleShape,
                colors = ButtonColors(
                    containerColor = colorResource(id = R.color.lightpink),
                    contentColor = Color.Black,
                    disabledContainerColor = colorResource(id = R.color.lightpink),
                    disabledContentColor = Color.Black)
            ) {
                Text("Go Home Page")
            }
        }
    }
}