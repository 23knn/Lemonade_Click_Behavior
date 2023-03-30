package com.example.lemonade_clickbehavior

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeApp(modifier = Modifier.fillMaxSize())
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
        mutableStateOf((2..4).random())
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
        if(sequenceState == 2){
            println(tapsLeftForSqueezing)
            if(tapsLeftForSqueezing > 0){
                tapsLeftForSqueezing--
            }else{
                tapsLeftForSqueezing = (2..4).random()
                sequenceState++
            }
        }else{
            sequenceState++
            sequenceState %= 4
        }
    }

    Column(modifier = modifier, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(id = topText), fontSize = 20.sp)
        Image(
            painter = painterResource(id = selectedImage),
            contentDescription = stringResource(id = topText),
            modifier = Modifier.clickable(onClick = {onClickImage()})
        )
    }
}

