package uk.ac.tees.mad.d3649527

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun BackButton(onClick: ()-> Unit = {}, @SuppressLint("ModifierParameter") modifier: Modifier = Modifier){
    IconButton(onClick = onClick, modifier = modifier) {
        Image(painter = painterResource(id = R.drawable.back),
            contentDescription = "back")
    }
}