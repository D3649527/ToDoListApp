package uk.ac.tees.mad.d3649527

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import uk.ac.tees.mad.d3649527.ui.theme.ToDoListAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val mainVM : MainViewModel by viewModels<MainViewModel>()
        val authVM : AuthViewModel by viewModels<AuthViewModel>()
        setContent {
            val navController = rememberNavController()
            ToDoListAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHostContainer(
                        navController = navController,
                        padding = innerPadding,
                        startDes = Routes.login.name,
                        mainViewModel = mainVM,
                        authViewModel = authVM
                    )
                }
            }
        }
    }
}