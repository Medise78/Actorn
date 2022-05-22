package com.mahdi.actorn.ui.component

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mahdi.actorn.R
import com.mahdi.actorn.utils.ApiKey
import com.mahdi.actorn.utils.NetworkManger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
private fun LaunchSnackBar(
          scaffoldState : ScaffoldState ,
          snackBarMessage : String ,
          scope : CoroutineScope = rememberCoroutineScope() ,
) {
     LaunchedEffect(scope) {
          scope.launch {
               scaffoldState.snackbarHostState.showSnackbar(
                         message = snackBarMessage ,
                         duration = SnackbarDuration.Indefinite
               )
          }
     }
}


@Composable
fun IfOfflineShowSnackBar(
          scaffoldState : ScaffoldState ,
          context : Context = LocalContext.current ,
) {
     val isOnline = NetworkManger(context).checkForActivityNetwork()
     if (! isOnline) {
          LaunchSnackBar(
                    scaffoldState ,
                    context.getString(R.string.offline_snackBar_message)
          )
     }
}


@Composable
fun ApiKeyMissingShowSnackBar(
          scaffoldState : ScaffoldState ,
          context : Context = LocalContext.current ,
) {
     if (ApiKey.API_KEY.isEmpty()) {
          LaunchSnackBar(
                    scaffoldState ,
                    context.getString(R.string.missing_api_key_snackBar_message)
          )
     }
}

@Composable
fun AppDivider(
          verticalPadding : Dp ,
) {
     Divider(
               color = MaterialTheme.colors.onBackground.copy(alpha = 0.1f) ,
               thickness = 1.dp ,
               startIndent = 0.dp ,
               modifier = Modifier.padding(vertical = verticalPadding)
     )
}

@Composable
fun CategoryTitle(
          title : String ,
) {
     Text(
               text = title ,
               style = MaterialTheme.typography.h6 ,
               color = MaterialTheme.colors.onBackground ,
               modifier = Modifier
                         .padding(start = 20.dp)
                         .alpha(0.5f)
     )
}