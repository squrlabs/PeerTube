package com.squrlabs.peertube.ui.mobile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import androidx.ui.tooling.preview.Preview
import com.squrlabs.peertube.ui.mobile.instance.InstanceScreen
import com.squrlabs.peertube.ui.mobile.main.MainScreen
import com.squrlabs.peertube.ui.mobile.splash.SplashScreen
import com.squrlabs.peertube.util.PeerTubeTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MobileActivity : AppCompatActivity() {

    private val viewModel: MobileViewModel by viewModel()
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileScreen("splash")
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        with(viewModel){
            navigate.observe(this@MobileActivity, {
//                navController.navigate(it)
            })
        }
    }

    @Composable
    fun MobileScreen(startDestination: String) {
        navController = rememberNavController()
        PeerTubeTheme {
            NavHost(navController = navController, startDestination = startDestination){
                composable("splash"){ SplashScreen() }
                composable("instances"){ InstanceScreen() }
                composable("main") { MainScreen() }
            }
        }
    }
}