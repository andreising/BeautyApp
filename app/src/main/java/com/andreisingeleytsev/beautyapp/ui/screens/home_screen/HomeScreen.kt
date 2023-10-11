package com.andreisingeleytsev.beautyapp.ui.screens.home_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.navigation.NavHostController
import com.andreisingeleytsev.beautyapp.R
import com.andreisingeleytsev.beautyapp.ui.theme.Primary
import com.andreisingeleytsev.beautyapp.ui.utils.CategoryConverter
import com.andreisingeleytsev.beautyapp.ui.utils.Fonts
import com.andreisingeleytsev.beautyapp.ui.utils.Routes

@Composable
fun HomeScreen(navHostController: NavHostController) {
    Column(modifier = Modifier
        .padding(horizontal = 22.dp, vertical = 48.dp)
        .fillMaxSize()) {
        Text(
            text = stringResource(R.string.categories), style = TextStyle(
                color = Color.Black, fontSize = 18.sp, fontFamily = Fonts.font
            )
        )
        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
            items(6) {
                if (it != 5) CategoryItem(index = it) {
                    navHostController.navigate(Routes.CATEGORY_SCREEN + "/$it")
                    Log.d("check", Routes.CATEGORY_SCREEN + "/$it")
                }
                else Box(modifier = Modifier.fillMaxSize().height(200.dp).clickable {
                    navHostController.navigate(Routes.CATEGORY_SCREEN + "/$it")
                }, contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        modifier = Modifier.size(80.dp),
                        tint = Primary
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryItem(index: Int, onClick: () -> Unit) {
    val category = CategoryConverter.fromIndexToCategory(index)
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Primary
        ), shape = RoundedCornerShape(12.dp), modifier = Modifier
            .padding(10.dp)
            .clickable {
                onClick.invoke()
            }
    ) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(130.dp), shape = RoundedCornerShape(12.dp)
        ) {
            Image(
                painter = painterResource(id = category.imageRes),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = stringResource(id = category.stringRes), style = TextStyle(
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = Fonts.font,
                textAlign = TextAlign.Center
            ), modifier = Modifier.fillMaxWidth()
        )
    }
}