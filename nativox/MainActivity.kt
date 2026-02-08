package com.example.nativox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
// --- THE FIX IS HERE: UPDATED IMPORTS ---
import com.example.nativox.ui.* import com.example.nativox.ui.theme.NativoxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // If this line is red, check ui/theme/Theme.kt.
            // It might still be named 'SentenceDrillsTheme'.
            // If so, just change this word to match that file.
            NativoxTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {

        composable("home") {
            HomeScreen(
                onLanguageSelected = { language ->
                    navController.navigate("drills/$language/1")
                },
                onLibraryClick = {
                    navController.navigate("library")
                }
            )
        }

        composable("drills/{language}/{level}") { backStackEntry ->
            val language = backStackEntry.arguments?.getString("language") ?: "English"
            val levelString = backStackEntry.arguments?.getString("level") ?: "1"
            val level = levelString.toIntOrNull() ?: 1

            DrillSelectionScreen(
                language = language,
                level = level,
                onBackClick = { navController.popBackStack() },
                onDrillClick = { drillId, voiceId ->
                    navController.navigate("themes/$language/$drillId/$voiceId")
                }
            )
        }

        composable("themes/{language}/{drillId}/{voiceId}") { backStackEntry ->
            val language = backStackEntry.arguments?.getString("language") ?: "English"
            val drillId = backStackEntry.arguments?.getString("drillId") ?: "swap"
            val voiceId = backStackEntry.arguments?.getString("voiceId") ?: "female"

            ThemeSelectionScreen(
                language = language,
                drillId = drillId,
                onBackClick = { navController.popBackStack() },
                onThemeClick = { themeId ->
                    navController.navigate("practice/$language/$drillId/$themeId/$voiceId")
                }
            )
        }

        composable("practice/{language}/{drillId}/{themeId}/{voiceId}") { backStackEntry ->
            val language = backStackEntry.arguments?.getString("language") ?: "English"
            val drillId = backStackEntry.arguments?.getString("drillId") ?: "ladder"
            val themeId = backStackEntry.arguments?.getString("themeId") ?: "date"
            val voiceId = backStackEntry.arguments?.getString("voiceId") ?: "female"

            PracticeScreen(
                language = language,
                drillId = drillId,
                themeId = themeId,
                voiceId = voiceId,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("library") {
            LibraryScreen(onBackClick = { navController.popBackStack() })
        }
    }
}