package uk.ac.tees.mad.d3649527


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues,
    startDes: String,
    mainViewModel: MainViewModel,
    authViewModel: AuthViewModel
) {

    NavHost(
        navController = navController,

        // set the start destination as home
        startDestination = startDes,

        // Set the padding provided by scaffold
        modifier = Modifier.padding(paddingValues = padding),

        builder = {
            composable(Routes.home.name) {
                Home(mainViewModel)
            }
            composable(Routes.login.name) {
                GetLogin(authViewModel, navController)
            }
            composable(Routes.register.name) {
                GetRegister(navController, authViewModel)
            }
            composable(Routes.forgotPassword.name) {
                ForgotPassword(navController, authViewModel)
            }
        })

}