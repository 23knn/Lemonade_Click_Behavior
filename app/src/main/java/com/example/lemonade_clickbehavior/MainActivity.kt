package com.example.lemonade_clickbehavior

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeApp(modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun LemonadeApp(
    modifier:Modifier = Modifier,
){
    var sequenceState by remember {
        mutableStateOf(1)
    }
    var tapsLeftForSqueezing by remember {
        mutableStateOf(0)
    }

    var selectedImage = when(sequenceState){
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    var topText = when(sequenceState){
        1 -> R.string.tap_tree
        2 -> R.string.squeeze
        3 -> R.string.drink
        else -> R.string.restart
    }
    fun onClickImage(){
        sequenceState++
    }

    Column(modifier = modifier) {
        Text(text = stringResource(id = topText))
        Image(
            painter = painterResource(id = selectedImage),
            contentDescription = stringResource(id = topText),
            modifier = Modifier.clickable(onClick = {sequenceState = ((sequenceState+1)%4)})
        )
    }
}

