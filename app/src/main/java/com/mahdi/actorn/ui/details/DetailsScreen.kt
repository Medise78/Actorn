package com.mahdi.actorn.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.statusBarsHeight
import com.mahdi.actorn.R
import com.mahdi.actorn.model.ActorDetail
import com.mahdi.actorn.model.Movie
import com.mahdi.actorn.ui.component.CategoryTitle
import com.mahdi.actorn.ui.component.LoadNetworkImage
import com.mahdi.actorn.ui.component.ShowProgressIndicator
import com.mahdi.actorn.utils.*
import timber.log.Timber

@Composable
fun DetailsScreen(
          selectedMovie : (Int) -> Unit ,
          navigateUp : () -> Unit ,
          viewModel : DetailsViewModel ,
)
{
     val uiState = viewModel.uiState
     val actorProfile = "${uiState.actorData?.profileUrl}"
     
     Surface(color = MaterialTheme.colors.background) {

          Box {
               ActorBackgroundWithGradiantForeground(imageUrl = actorProfile)
               Column {
                    ContentDetail(navigateUp = navigateUp ,
                                  uiState = uiState ,
                                  selectedMovie = selectedMovie)
               }
               ShowProgressIndicator(isLoadingData = uiState.isFetchingDetail)
          }
     }
}

@Composable
private fun ActorBackgroundWithGradiantForeground(
          imageUrl : String ,
          modifier : Modifier = Modifier ,
)
{
     Box {
          LoadNetworkImage(imageUrl = imageUrl ,
                           contentDescription = stringResource(id = R.string.cd_actor_banner) ,
                           modifier = Modifier
                                 .fillMaxSize()
                                 .alpha(0.5f) ,
                           shape = RectangleShape)
          Box(modifier = modifier
                .fillMaxSize()
                .verticalGradientScrim(color = MaterialTheme.colors.primary.copy(0.5f) ,
                                       startYPercentage = 0f ,
                                       endYPercentage = 1f))
     }
}

@Composable
private fun ContentDetail(
          navigateUp : () -> Unit ,
          uiState : DetailsViewState ,
          selectedMovie : (Int) -> Unit ,
)
{
     val actorData = uiState.actorData
     Spacer(modifier = Modifier
           .fillMaxWidth()
           .statusBarsHeight())
     DetailAppBar(navigateUp = navigateUp , title = "${actorData?.actorName}")
     Spacer(modifier = Modifier.padding(top = 20.dp))
     ActorRoundProfile("${actorData?.profileUrl}")
     Spacer(modifier = Modifier.padding(vertical = 10.dp))
     LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp)) {
          item {
               ActorInfoHeader(actorData = actorData)
          }
          item {
               ActorCastMovie(cast = uiState.castList , selectedMovie = selectedMovie)
          }
          item {
               ActorBioGraphi(bioGraphi = actorData?.bioGraphi)
          }
     }
}

@Composable
private fun ActorRoundProfile(
          profileUrl : String ,
)
{
     Box(modifier = Modifier.fillMaxWidth() , contentAlignment = Alignment.Center) {
          LoadNetworkImage(imageUrl = profileUrl ,
                           contentDescription = stringResource(id = R.string.cd_actor_image) ,
                           modifier = Modifier
                                 .size(120.dp)
                                 .border(width = 5.dp ,
                                         color = MaterialTheme.colors.surface ,
                                         shape = CircleShape) ,
                           shape = CircleShape)
     }
}

@Composable
private fun ActorInfoHeader(
          actorData : ActorDetail? ,
)
{
     LazyRow(modifier = Modifier.fillMaxWidth() , horizontalArrangement = Arrangement.Center) {
          item {
               AgeInfo(actorAge = actorData?.dateOfBirth)
          }
          item {
               PopularityInfo(popularity = actorData?.popularity)
          }
          item {
               CountryInfo(placeOfBirth = actorData?.placeOfBirth)
          }
     }
}

@Composable
private fun ActorCastMovie(
          cast : List<Movie> ,
          selectedMovie : (Int) -> Unit ,
)
{
     Row(verticalAlignment = Alignment.CenterVertically) {
          Image(painter = painterResource(id = R.drawable.ic_movies_cast) ,
                contentDescription = stringResource(
                          id = R.string.cd_cast_icon) ,
                colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onSurface) ,
                alpha = 0.5f ,
                modifier = Modifier
                      .padding(start = 10.dp)
                      .size(40.dp))
          CategoryTitle(title = stringResource(id = R.string.cast_movie_title))
     }
     LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp) ,
             modifier = Modifier.padding(20.dp)) {
          items(cast) { movie ->
               LoadNetworkImage(imageUrl = movie.posterPathUrl ,
                                contentDescription = stringResource(id = R.string.cd_movie_poster) ,
                                modifier = Modifier
                                      .size(100.dp , 150.dp)
                                      .clickable {
                                            Timber.e("id is${movie.movieId}")
                                            selectedMovie(movie.movieId)
                                      } ,
                                shape = MaterialTheme.shapes.medium)
          }
     }
}

@Composable
private fun ActorBioGraphi(
          bioGraphi : String? ,
)
{
     Column(modifier = Modifier
           .verticalGradientScrim(color = MaterialTheme.colors.surface.copy(
                 0.75f) , startYPercentage = 0f , endYPercentage = 1f)
           .padding(bottom = 60.dp , top = 20.dp , start = 20.dp , end = 20.dp)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
               Image(painter = painterResource(id = R.drawable.ic_biography) ,
                     contentDescription = stringResource(
                               id = R.string.cast_biography_title) ,
                     colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onSurface) ,
                     alpha = 0.5f , modifier = Modifier.size(40.dp))
               CategoryTitle(title = stringResource(id = R.string.cast_biography_title))
          }
          Spacer(modifier = Modifier.padding(vertical = 10.dp))
          if (bioGraphi != null)
          {
               Text(text = bioGraphi ,
                    style = TextStyle(lineHeight = 20.sp ,
                                      color = MaterialTheme.colors.onSurface ,
                                      fontWeight = FontWeight.SemiBold ,
                                      fontSize = 20.sp ,
                                      textAlign = TextAlign.Justify))
          }
     }
}

@Composable
private fun AgeInfo(
          actorAge : String? ,
)
{
     Column(horizontalAlignment = Alignment.CenterHorizontally ,
            modifier = Modifier.padding(20.dp)) {
          Box {
               Text(text = "${calculateAge(actorAge)}" ,
                    style = MaterialTheme.typography.h6 ,
                    color = MaterialTheme.colors.onSurface ,
                    modifier = Modifier.align(Alignment.Center))
          }
          ActorInfoHeaderSubtitle(subtitle = stringResource(id = R.string.actor_age_subtitle))
     }
}

@Composable
private fun CountryInfo(
          placeOfBirth : String? ,
)
{
     Column(modifier = Modifier.padding(20.dp) ,
            horizontalAlignment = Alignment.CenterHorizontally) {
          Box {
                Column {
                      Image(painter = painterResource(id = R.drawable.ic_globe) ,
                            contentDescription = stringResource(
                                  id = R.string.cd_place_of_birth_icon) ,
                            colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface) )
                      Text(text = placeOfBirth?:"")

                }

          }
     }
}

@Composable
private fun PopularityInfo(
          popularity : Double? ,
)
{
     Column(modifier = Modifier.padding(20.dp) ,
            horizontalAlignment = Alignment.CenterHorizontally) {
          Box {
               Text(text = getPopularity(popularity) ,
                    style = MaterialTheme.typography.h6 ,
                    color = MaterialTheme.colors.onSurface ,
                    modifier = Modifier
                          .padding(vertical = 10.dp , horizontal = 10.dp)
                          .align(Alignment.Center))
          }
          ActorInfoHeaderSubtitle(subtitle = stringResource(id = R.string.actor_popularity_subtitle))
     }
}

@Composable
private fun ActorInfoHeaderSubtitle(
          subtitle : String ,
)
{
     Text(text = subtitle ,
          style = MaterialTheme.typography.subtitle2 ,
          modifier = Modifier.padding(vertical = 10.dp) ,
          color = MaterialTheme.colors.onBackground)
}

