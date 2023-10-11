package com.andreisingeleytsev.beautyapp.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.andreisingeleytsev.beautyapp.ui.navigation.MainNavigationGraph
import com.andreisingeleytsev.beautyapp.ui.theme.MainColor
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize(), color = MainColor) {
                if (!viewModel.isLoading.value){
                    if (viewModel.startDestination.value != null) MainNavigationGraph(
                        viewModel.startDestination.value!!
                    )
                }
            }
        }
    }
}
