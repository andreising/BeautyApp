package com.andreisingeleytsev.beautyapp.ui.screens.onboard_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.andreisingeleytsev.beautyapp.R
import com.andreisingeleytsev.beautyapp.ui.theme.Primary
import com.andreisingeleytsev.beautyapp.ui.utils.Fonts
import com.andreisingeleytsev.beautyapp.ui.utils.Routes

@Composable
fun OnBoardScreen(
    navController: NavHostController,
    viewModel: OnBoardScreenViewModel = hiltViewModel()
) {
    val screenIndex = remember {
        mutableStateOf(0)
    }
    when (screenIndex.value) {
        0 -> OnboardItem(background = R.drawable.ob_bg_1, stringRes = R.string.ob_title_1) {
            screenIndex.value++
        }

        1 -> OnboardItem(background = R.drawable.ob_bg_2, stringRes = R.string.ob_title_2) {
            screenIndex.value++
        }

        2 -> OnboardItem(background = R.drawable.ob_bg_3, stringRes = R.string.ob_title_3) {
            viewModel.saveOnBoardingState()
            navController.popBackStack()
            navController.navigate(Routes.HOME_SCREEN)
        }
    }
}

@Composable
fun OnboardItem(background: Int, stringRes: Int, onClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Image(
            painter = painterResource(id = background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.padding(start = 40.dp, end = 40.dp, bottom = 80.dp)) {
            Text(text = stringResource(id = stringRes), style = TextStyle(
                fontFamily = Fonts.font, color = Primary, fontSize = 20.sp, textAlign = TextAlign.Center
            ))
            Spacer(modifier = Modifier.height(40.dp))
            Button(
                onClick = { onClick.invoke() }, colors = ButtonDefaults.buttonColors(
                    containerColor = Primary
                ), shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.next), style = TextStyle(
                        fontFamily = Fonts.font, color = Color.White, fontSize = 18.sp
                    )
                )
            }
        }

    }
}