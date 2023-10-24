package com.pokemon.feature.pokemon.detail

import android.media.MediaPlayer
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.pokemon.core.design_system.PokemonTheme
import com.pokemon.core.domain.entity.DetailMoveEntity
import com.pokemon.core.ui.util.toPokemonType

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailScreen(
    navController: NavController,
    id: Int,
    detailViewModel: DetailViewModel = hiltViewModel(),
) {
    val container = detailViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow
    var moveDescriptionVisible by remember { mutableStateOf(false) }
    var selectedMove: DetailMoveEntity? by remember { mutableStateOf(null) }

    LaunchedEffect(Unit) {
        detailViewModel.getPokemonDetail(pokemonId = id)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    state.typeList
                        .getOrNull(0)
                        ?.toPokemonType()?.typeColor
                        ?: PokemonTheme.colors.main,
                    RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
                )
        ) {
            Text(
                modifier = Modifier.align(Alignment.TopEnd),
                text = "#${"%04d".format(state.id)}"
            )
            AsyncImage(
                modifier = Modifier
                    .align(Alignment.Center)
                    .clickable {
                        MediaPlayer().apply {
                            setDataSource("https://play.pokemonshowdown.com/audio/cries/${state.englishName}.ogg")
                            prepare()
                            start()
                        }
                    },
                model = state.profileUrl, contentDescription = null
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = state.name,
            textAlign = TextAlign.Center
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            items(state.typeList) {
                val type = it.toPokemonType()
                Text(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .background(type.typeColor, RoundedCornerShape(10.dp))
                        .padding(horizontal = 45.dp, vertical = 5.dp),
                    text = stringResource(id = type.typeId)
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            AttributeText(title = "분류", content = state.genus)
            Spacer(modifier = Modifier.weight(1F))
            AttributeText(title = "체중", content = "${state.weight / 10F}KG")
            Spacer(modifier = Modifier.weight(1F))
            AttributeText(title = "신장", content = "${state.height / 10F}M")
        }
        Text(text = "사용 기술")
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(state.moveList) {
                Text(
                    modifier = Modifier
                        .clickable {
                            if (selectedMove == null || selectedMove != it) {
                                moveDescriptionVisible = true
                                selectedMove = it
                            } else {
                                moveDescriptionVisible = false
                                selectedMove = null
                            }
                        }
                        .background(
                            it.type.toPokemonType().typeColor,
                            RoundedCornerShape(10.dp)
                        )
                        .padding(horizontal = 7.dp, vertical = 5.dp), text = it.name
                )
            }
        }
        AnimatedVisibility(visible = moveDescriptionVisible) {
            selectedMove?.let {
                val moveDescriptionPagerState = rememberPagerState {
                    it.flavorList.size
                }
                HorizontalPager(state = moveDescriptionPagerState) { index ->
                    Text(text = it.flavorList[index])
                }
            }
        }
        Text(text = "상세 설명")
        val descriptionPagerState = rememberPagerState {
            state.flavorList.size
        }
        HorizontalPager(state = descriptionPagerState) {
            Text(text = state.flavorList[it])
        }
    }
}

@Composable
fun AttributeText(
    title: String,
    content: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title)
        Text(text = content)
    }
}