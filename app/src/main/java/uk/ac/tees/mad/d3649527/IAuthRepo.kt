package uk.ac.tees.mad.d3649527

import android.content.Context
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseUser

interface IAuthRepo{
    suspend fun registerUser(
        email: String,
        password: String,
        navController: NavController
    )
    suspend fun loginUser(
        email: String,
        password: String,
        navController: NavController
    )

    suspend fun forgotPass(
        email: String,
        ctx: Context
    )

    suspend fun logout(
        navController: NavController
    )

    fun getCurrentuser() : FirebaseUser?
    //suspend fun getCurrentuserDetails() : User?
}