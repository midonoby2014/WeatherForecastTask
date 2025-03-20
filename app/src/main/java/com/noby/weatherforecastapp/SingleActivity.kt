package com.noby.weatherforecastapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import com.noby.weatherforecastapp.ui.theme.WeatherForcastTaskTheme
import com.noby.home.presentation.WeatherScreen
import com.noby.navigation.AppNavigation
import com.noby.navigation.Navigator
import com.noby.navigation.graph.DetailScreens
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject



@AndroidEntryPoint
class SingleActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherForcastTaskTheme {
                AppNavigation(
                    navigator = navigator,
                    homeScreen = {
                        WeatherScreen()
                    },
                    detailScreen = {
                        Box {  }
                    },
                )
            }
        }
    }
}
