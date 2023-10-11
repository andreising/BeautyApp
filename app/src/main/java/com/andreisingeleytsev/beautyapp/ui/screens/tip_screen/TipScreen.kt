package com.andreisingeleytsev.beautyapp.ui.screens.tip_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import com.andreisingeleytsev.beautyapp.ui.theme.Primary
import com.andreisingeleytsev.beautyapp.ui.utils.CategoryConverter
import com.andreisingeleytsev.beautyapp.ui.utils.Fonts
import javax.inject.Inject

@Composable
fun TipScreen(
    categoryIndex: Int,
    tipIndex: Int,
    navHostController: NavHostController,
    viewModel: TipScreenViewModel = hiltViewModel()
) {

    val category = CategoryConverter.fromIndexToCategory(categoryIndex)
    val tip = category.tips[tipIndex]
    Column(modifier = Modifier.padding(vertical = 40.dp, horizontal = 20.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            IconButton(
                onClick = { navHostController.popBackStack() },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Primary
                )
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }
            IconButton(
                onClick = { viewModel.onEvent(TipScreenEvents.OnFavPressed) }
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    tint = if (viewModel.isFavourite.value) Primary
                    else Primary.copy(alpha = 0.5F)
                )
            }
        }
        Text(
            text = stringResource(id = viewModel.name.value), style = TextStyle(
                color = Color.Black,
                fontSize = 24.sp,
                fontFamily = Fonts.font,
                textAlign = TextAlign.Start
            )
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp), shape = RoundedCornerShape(12.dp)
        ) {
            Image(
                painter = painterResource(id = viewModel.imageId.value),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = stringResource(id = viewModel.title.value), style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                fontFamily = Fonts.font,
                textAlign = TextAlign.Start
            ), modifier = Modifier.fillMaxWidth()
        )
    }
}
