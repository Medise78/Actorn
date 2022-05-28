package com.mahdi.actorn.ui.movie_detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahdi.actorn.R

@Composable
fun MovieDetailAppBar(
    navigateUp : () -> Unit ,
    title : String ,
) {
    Column() {
        Spacer(modifier = Modifier.height(30.dp))

        Box(
            Modifier
                .fillMaxWidth()
                .height(40.dp)
        ) {
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = "",
                        modifier = Modifier.padding( 5.dp, bottom = 5.dp).align(Alignment.Center)
                    )
                }
            }
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
               Icon(modifier = Modifier.padding( 5.dp),painter = painterResource(id = R.drawable.ic_star), contentDescription ="" )
            }
        }
    }
}