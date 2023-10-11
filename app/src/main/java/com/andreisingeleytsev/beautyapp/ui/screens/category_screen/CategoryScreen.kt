package com.andreisingeleytsev.beautyapp.ui.screens.category_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.navigation.NavHostController
import com.andreisingeleytsev.beautyapp.ui.theme.Primary
import com.andreisingeleytsev.beautyapp.ui.utils.CategoryConverter
import com.andreisingeleytsev.beautyapp.ui.utils.Fonts
import com.andreisingeleytsev.beautyapp.ui.utils.Routes
import com.andreisingeleytsev.beautyapp.ui.utils.Tip
import com.andreisingeleytsev.danceclasses.ui.utils.UIEvents

@Composable
fun CategoryScreen(
    navHostController: NavHostController,
    categoryScreenViewModel: CategoryScreenViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true){
        categoryScreenViewModel.uiEvent.collect{
            when(it) {
                is UIEvents.Navigate -> {
                    navHostController.navigate(it.route)
                }
            }
        }
    }

    Column(modifier = Modifier.padding(vertical = 40.dp, horizontal = 20.dp)) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
            Text(
                text = stringResource(id = categoryScreenViewModel.title.value), style = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontFamily = Fonts.font,
                    textAlign = TextAlign.Center
                ), modifier = Modifier.fillMaxWidth()
            )
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
        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(categoryScreenViewModel.list.value) { tip ->
                TipItem(tip = tip) {
                    categoryScreenViewModel.onEvent(CategoryScreenEvent.OnClick(tip))
                }
            }
        }
    }
}

@Composable
fun TipItem(tip: Tip, onClick: () -> Unit) {
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
                painter = painterResource(id = tip.imageRes),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = stringResource(id = tip.nameStringRes), style = TextStyle(
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = Fonts.font,
                textAlign = TextAlign.Center
            ), modifier = Modifier.fillMaxWidth()
        )
    }
}