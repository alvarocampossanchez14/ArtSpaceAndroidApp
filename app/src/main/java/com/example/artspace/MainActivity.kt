package com.example.artspace

import android.graphics.Paint.Align
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {

    var imageResult by remember {
        mutableStateOf(1)
    }


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when(imageResult)  {
            1 -> {
                ArtSpaceImageWithText(
                    name = R.string.art_1,
                    description = R.string.art_1_description,
                    image = R.drawable.art_1,
                    nextImage = { imageResult = 2},
                    previousImage = {imageResult = 3}
                )
            }

            2 -> {
                ArtSpaceImageWithText(
                    name = R.string.art_2,
                    description = R.string.art_2_description,
                    image = R.drawable.art_2,
                    nextImage = { imageResult = 3},
                    previousImage = { imageResult = 1}
                )
            }

            3 -> {
                ArtSpaceImageWithText(
                    name = R.string.art_3,
                    description = R.string.art_3_description,
                    image = R.drawable.art_3_,
                    nextImage = { imageResult = 1},
                    previousImage = { imageResult = 2}
                )
            }
        }
    }
}


@Composable
fun ArtSpaceImageWithText(
    name: Int,
    description: Int,
    image: Int,
    nextImage: () -> Unit,
    previousImage: () -> Unit,
    modifier: Modifier = Modifier) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(painter = painterResource(image), contentDescription = null)
        Spacer(modifier = Modifier.height(150.dp))

       Column() {
           Text(text = stringResource(name), fontSize = 20.sp, fontWeight = FontWeight.Bold)
           Text(text = stringResource(description))
       }

        Spacer(modifier = Modifier.height(50.dp))


        Row() {
            Button(onClick =  nextImage ) {
                Text(text = "Previous")
            }   
            Spacer(modifier = Modifier.width(50.dp))
            Button(onClick =  previousImage ) {
                Text(text = "Next")
            }
        }
        }
    }






@Preview
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}