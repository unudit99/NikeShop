package com.arvind.nikeshop.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arvind.nikeshop.ui.theme.background
import com.arvind.nikeshop.ui.theme.grey
import com.arvind.nikeshop.ui.theme.titleTextColor

@Composable
fun TopBarWithLocationIcon(onBackClick: () -> Unit){
    Row( modifier = Modifier
        .fillMaxWidth().padding(top =10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Top) {
        IconButton(onClick = { onBackClick() }) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = ""
            )
        }
        Row(){
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = ""
                )
            }

            Text(
                modifier =Modifier.padding(top=10.dp, end =20.dp),
                text = "Location",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = titleTextColor
            )
        }

        Card(
            shape = RoundedCornerShape(12.dp),
            elevation = 5.dp
        ) {
            Row() {
                IconButton(onClick = { onBackClick() }) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = ""
                    )
                }
                Text(
                    modifier =Modifier.padding(top=10.dp, end =20.dp),
                    text = "See Map",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = titleTextColor
                )
            }


        }


    }

}