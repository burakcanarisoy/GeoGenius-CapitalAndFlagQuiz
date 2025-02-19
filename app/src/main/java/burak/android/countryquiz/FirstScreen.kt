package burak.android.countryquiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
fun FirstScreen(navController: NavController) {
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
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Text(
                text = "Welcome to GeoGenius! Let's test your geography knowledge.",
                fontFamily = FontFamily.Cursive,
                fontSize = 56.sp,
                lineHeight = 48.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top= 16.dp, bottom = 24.dp),
                style = TextStyle(shadow = Shadow(color = Color.White.copy(alpha = 0.8f), offset = Offset(2f, 2f), blurRadius = 6f))
            )

            Card(
                colors = CardDefaults.cardColors(containerColor = Color.Cyan),
                modifier = Modifier.fillMaxWidth(1f).aspectRatio(1.75f).heightIn(max = 160.dp).clickable {
                    navController.navigate("capitalquiz")
                }
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.capitalcardbackground),
                        contentDescription = "Capital Quiz Card Background",
                        modifier = Modifier.matchParentSize(),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = "Capital Quiz",
                        textAlign = TextAlign.Center,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(shadow = Shadow(color = Color.White, offset = Offset(4f, 4f), blurRadius = 4f))
                    )
                }
            }

            Card(
                colors = CardDefaults.cardColors(containerColor = Color.Magenta),
                modifier = Modifier.fillMaxWidth(1f).aspectRatio(1.75f).clickable {
                        navController.navigate("flagquiz")
                    }
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.flagscardbackground),
                        contentDescription = "Flag Quiz Card Background Image",
                        modifier = Modifier.matchParentSize(),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = "Flag Quiz",
                        textAlign = TextAlign.Center,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(shadow = Shadow(color = Color.White, offset = Offset(4f, 4f), blurRadius = 4f))
                    )
                }
            }
        }

    }

}