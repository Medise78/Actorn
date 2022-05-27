package com.mahdi.actorn.ui.movie_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahdi.actorn.R
import com.mahdi.actorn.model.MovieDetail
import com.mahdi.actorn.ui.component.LoadNetworkImage
import com.mahdi.actorn.utils.verticalGradientScrim


@Composable
fun MovieDetailScreen(
    selectedMovie: (Int) -> Unit,
    viewModel: MovieDetailViewModel,
    navigateUp: () -> Unit,
) {
    val uiState = viewModel.uiState


        Spacer(modifier = Modifier.height(15.dp))
        Scaffold(
            topBar = {
                MovieDetailAppBar(
                    navigateUp = navigateUp,
                    title = "${uiState.movieData?.movieTitle}"
                )
            }
        ) {


            MovieDetailsContent(uiState.movieData, viewModel)




            ActorBackgroundWithGradiantForeground(imageUrl = uiState.movieData?.poster)
            //  columnListNews(viewState = uiState)
        }

}

@Composable
fun Genres(viewModel: MovieDetailViewModel) {
    val uiState = viewModel.uiState
    Column() {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
            Icon(
                painter = painterResource(id = R.drawable.ic_genres),
                contentDescription = "",
                modifier = Modifier.padding(start = 20.dp, end = 20.dp),

            )
        }
        uiState.movieData?.let { data ->
            data.genres.forEach { genres ->
                TextAll(
                    text = "Genres : $genres ",
                    fontWeight = FontWeight.W500,
                    color = MaterialTheme.colors.onSurface
                )
            }
        }
    }
}

@Composable
fun ProductionCompanies(viewModel: MovieDetailViewModel) {
    val uiState = viewModel.uiState
    Column() {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
            Icon(
                painter = painterResource(id = R.drawable.ic_corporate),
                contentDescription = "",
                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
            )
        }
        uiState.movieData?.let { data ->
            data.productionCompanies.forEach { productionCompanies ->
                TextAll(
                    text = "ProductionCompanies : $productionCompanies",
                    fontWeight = FontWeight.W400,
                    color = MaterialTheme.colors.onSurface
                )
            }
        }
    }
}

@Composable
fun MovieDetailsContent(movieData: MovieDetail?, viewModel: MovieDetailViewModel) {
    LazyColumn {

        item { MovieBanner(movieData?.banner) }
        // item { popularity(popularity = movieData?.popularity) }
        // item { voteAverage(average = movieData?.voteAverage) }
        // item { originalLanguage(language = movieData?.originalLanguage) }
        item { Spacer(modifier = Modifier.height(10.dp)) }
        item { Genres(viewModel = viewModel) }
        item { divider() }


        item { Spacer(modifier = Modifier.height(15.dp)) }
        item { Tagline(tag = movieData?.tagline) }
        item { divider() }
        item { Spacer(modifier = Modifier.height(15.dp)) }
        item { Overview(overview = movieData?.overview) }
        item { divider() }
        item { Spacer(modifier = Modifier.height(15.dp)) }
        item { PopularAverageLanguage(movieData) }

        item { Spacer(modifier = Modifier.height(15.dp)) }
        item { ReleaseData(Date = movieData?.releaseData) }
        item { divider() }
        item { Spacer(modifier = Modifier.height(15.dp)) }
        item { RunTime(runTime = movieData?.runtime) }
        // item { MoviePosterDetail(posterUrl = movieData?.poster) }
        item { divider() }
        item { Spacer(modifier = Modifier.height(15.dp)) }
        item { ProductionCompanies(viewModel = viewModel) }
        item { divider() }
        item { Spacer(modifier = Modifier.height(15.dp)) }
        item { MovieBudget(budget = movieData?.budget) }
        item { divider() }
        item { Spacer(modifier = Modifier.height(15.dp)) }
        item { Revenue(revenue = movieData?.revenue) }
        item { divider() }
        item { Spacer(modifier = Modifier.height(15.dp)) }
        item { Status(status = movieData?.status) }
        item { divider() }
        item { Spacer(modifier = Modifier.height(10.dp)) }
        item { Spacer(modifier = Modifier.height(30.dp)) }
    }
}

@Composable
fun PopularAverageLanguage(movieData: MovieDetail?) {
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box() {
                Popularity(popularity = movieData?.popularity)
            }

            Box() {
                VoteAverage(average = movieData?.voteAverage)
            }

            Box() {
                OriginalLanguage(language = movieData?.originalLanguage)

            }

        }
    }
}

@Composable
fun VoteAverage(average: Double?) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {


        icons(painter = painterResource(id = R.drawable.ic_vote))

        TextPopularAverageLanguage(
            text = "Voteaverage \n $average",
            fontWeight = FontWeight.W400,

        )
        dividerPopularAverageLanguage()
    }
}

@Composable
fun Tagline(tag: String?) {
    Column() {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
            Icon(
                painter = painterResource(id = R.drawable.ic_pending),
                contentDescription = "",
                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
            )
        }
        TextAll(
            text = "Film Tagline : $tag",
            fontWeight = FontWeight.W400,
            color = MaterialTheme.colors.onSurface
        )

    }
}

@Composable
fun Status(status: String?) {
    Column() {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
            Icon(
                painter = painterResource(id = R.drawable.ic_replace),
                contentDescription = "",
                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
            )
        }
        TextAll(
            text = "status : $status",
            fontWeight = FontWeight.W400,
            color = MaterialTheme.colors.onSurface
        )
    }
}

@Composable
fun RunTime(runTime: Int?) {
    Column() {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
            Icon(
                painter = painterResource(id = R.drawable.ic_time),
                contentDescription = "",
                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
            )
        }
        TextAll(
            text = "Running Time : $runTime",
            fontWeight = FontWeight.W400,
            color = MaterialTheme.colors.onSurface
        )
    }
}

@Composable
fun Revenue(revenue: Long?) {
    Column() {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
            Icon(
                painter = painterResource(id = R.drawable.ic_money),
                contentDescription = "",
                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
            )
        }
        TextAll(
            text = "Movie Income : $revenue",
            fontWeight = FontWeight.W400,
            color = MaterialTheme.colors.onSurface
        )
    }
}

@Composable
fun ReleaseData(
    Date: String?
) {
    Column() {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_date_range_24),
                contentDescription = "",
                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
            )
        }
        TextAll(
            text = "releaseDate : $Date",
            fontWeight = FontWeight.W400,
            color = MaterialTheme.colors.onSurface
        )
    }
}

@Composable
private fun ActorBackgroundWithGradiantForeground(
    imageUrl: String?,
    modifier: Modifier = Modifier,
) {
    Box {
        LoadNetworkImage(
            imageUrl = imageUrl,
            contentDescription = stringResource(id = R.string.cd_actor_banner),
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.1f),
            shape = RectangleShape
        )
        Box(
            modifier = modifier
                .fillMaxSize()
                .verticalGradientScrim(
                    color = MaterialTheme.colors.primary.copy(0.5f),
                    startYPercentage = 0f,
                    endYPercentage = 1f
                )
        )
    }
}

@Composable
private fun MoviePosterDetail(
    posterUrl: String?,
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        LoadNetworkImage(
            imageUrl = "$posterUrl",
            contentDescription = stringResource(id = R.string.cd_actor_image),
            shape = RectangleShape,
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(300.dp)
        )
    }
}

@Composable
fun OriginalLanguage(
    language: String?
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        icons(painter =  painterResource(id = R.drawable.ic_flags))
        TextPopularAverageLanguage(
            text = "LanguageFilm \n $language",
            fontWeight = FontWeight.W400,

        )


        dividerPopularAverageLanguage()
    }
}

@Composable
fun Overview(
    overview: String?
) {
    Column() {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
            Icon(
                painter = painterResource(id = R.drawable.ic_connect_without_contact),
                contentDescription = "",
                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
            )
        }
        TextAll(
            text = "OverView : $overview",
            fontWeight = FontWeight.W400,
            color = MaterialTheme.colors.onSurface
        )
    }

}


@Composable
fun Popularity(
    popularity: Double?
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {


        icons(painter = painterResource(id = R.drawable.ic_star_black))

        TextPopularAverageLanguage(
            text = "Grade Of Fame \n $popularity",
            fontWeight = FontWeight.W400,

        )

        dividerPopularAverageLanguage()
    }


}


@Composable
fun MovieBudget(
    budget: String?
) {
    Column() {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
            Icon(
                painter = painterResource(id = R.drawable.ic_money),
                contentDescription = "",
                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
            )
        }
        TextAll(
            text = "Movie Production Budget: $budget",
            fontWeight = FontWeight.W400,
            color = MaterialTheme.colors.onSurface
        )

    }

}


@Composable
private fun MovieBanner(
    bannerUrl: String?,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 0.dp, bottom = 10.dp, start = 20.dp, end = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(backgroundColor = MaterialTheme.colors.onSurface, shape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp, bottomEnd = 30.dp)) {
            LoadNetworkImage(
                imageUrl = "$bannerUrl",
                contentDescription = stringResource(id = R.string.cd_actor_image),
                shape = RectangleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(300.dp)
            )
        }


    }
}

@Composable
fun TextAll(
    text: String,
    fontWeight: FontWeight,
    color: Color

) {

    Text(
        text = text,
        style = TextStyle(
            color = color,
            fontWeight = fontWeight,
            fontSize = 17.sp,
            textAlign = TextAlign.Justify
        ),

        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
    )
}



@Composable
fun TextPopularAverageLanguage(
    text: String,
    fontWeight: FontWeight,


) {
    Box(contentAlignment = Alignment.Center) {
        Text(
            text = text,
            style = TextStyle(
                color = Color.Black,
                fontFamily = FontFamily.SansSerif,
                fontWeight = fontWeight,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            ),

            modifier = Modifier.padding(5.dp)
        )

    }

}



@Composable
fun divider(

) {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
            .height(1.dp)
            .alpha(0.7f), color = Color.Gray
    )
}

@Composable
fun dividerPopularAverageLanguage() {
    Divider(
        modifier = Modifier
            .width(100.dp)
            .padding(start = 5.dp, end = 5.dp)
            .height(1.5.dp)
            .alpha(1f), color = Color.Black
    )
}

@Composable
fun icons(painter: Painter) {
    Image(painter = painter , contentDescription = "", colorFilter =  ColorFilter.tint(color = Color.Black))

}