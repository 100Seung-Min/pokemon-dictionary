package com.pokemon.feature.pokemon.detail

import android.media.MediaPlayer
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.pokemon.core.design_system.PokemonTheme
import com.pokemon.core.design_system.attribute.PokemonIcon
import com.pokemon.core.design_system.attribute.PokemonIconList
import com.pokemon.core.design_system.component.PokemonBackground
import com.pokemon.core.design_system.component.PokemonText
import com.pokemon.core.design_system.component.RemoveOverScrollLazyColumn
import com.pokemon.core.design_system.component.RemoveOverScrollLazyRow
import com.pokemon.core.domain.entity.DetailMoveEntity
import com.pokemon.core.ui.component.AttributeItem
import com.pokemon.core.ui.component.InfoItem
import com.pokemon.core.ui.component.MoveItem
import com.pokemon.core.ui.component.descriptionPager
import com.pokemon.core.ui.util.pokemonClickable
import com.pokemon.core.ui.util.toPokemonType
import com.pokemon.core.design_system.R

@Composable
fun DetailScreen(
    id: Int,
    navigatePokemonDetail: (Int) -> Unit,
    detailViewModel: DetailViewModel = hiltViewModel(),
) {
    val container = detailViewModel.container
    val state = container.stateFlow.collectAsState().value
    var moveDescriptionVisible by remember { mutableStateOf(false) }
    var selectedMove: DetailMoveEntity? by remember { mutableStateOf(null) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        detailViewModel.getPokemonInfo(pokemonId = id) {
            isLoading = false
        }
    }

    PokemonBackground(isLoading = isLoading) {
        RemoveOverScrollLazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
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
                        .padding(top = 10.dp, bottom = 20.dp)
                ) {
                    PokemonText(
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .align(Alignment.TopEnd),
                        text = "#${"%04d".format(state.id)}"
                    )
                    AsyncImage(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .pokemonClickable {
                                MediaPlayer().apply {
                                    setDataSource("https://play.pokemonshowdown.com/audio/cries/${state.englishName}.ogg")
                                    prepare()
                                    start()
                                }
                            },
                        model = state.profileUrl, contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                PokemonText(
                    modifier = Modifier.fillMaxWidth(),
                    text = state.name,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(12.dp))
                RemoveOverScrollLazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    items(state.typeList) {
                        AttributeItem(typeString = it)
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    InfoItem(title = stringResource(id = R.string.genus), content = state.genus)
                    Spacer(modifier = Modifier.weight(1F))
                    InfoItem(
                        title = stringResource(id = R.string.weight),
                        content = "${state.weight / 10F}KG"
                    )
                    Spacer(modifier = Modifier.weight(1F))
                    InfoItem(
                        title = stringResource(id = R.string.height),
                        content = "${state.height / 10F}M"
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                PokemonText(
                    modifier = Modifier.padding(horizontal = 15.dp), text = stringResource(
                        id = R.string.abilities
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                RemoveOverScrollLazyRow(
                    contentPadding = PaddingValues(horizontal = 15.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(state.moveList) {
                        MoveItem(it) {
                            moveDescriptionVisible = selectedMove != it || !moveDescriptionVisible
                            selectedMove = it
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                AnimatedVisibility(
                    visible = moveDescriptionVisible,
                    enter = expandVertically(),
                    exit = shrinkVertically()
                ) {
                    selectedMove?.let {
                        descriptionPager(
                            descriptionList = it.flavorList,
                            backgroundColor = it.type.toPokemonType().typeColor
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                PokemonText(
                    modifier = Modifier.padding(horizontal = 15.dp), text = stringResource(
                        id = R.string.description
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                descriptionPager(descriptionList = state.flavorList)
                Spacer(modifier = Modifier.height(16.dp))
                PokemonText(
                    modifier = Modifier.padding(horizontal = 15.dp), text = stringResource(
                        id = R.string.evolution
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            items(state.evolutionList) {
                RemoveOverScrollLazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    items(it) {
                        AsyncImage(
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                                .size(100.dp)
                                .background(Color.LightGray, CircleShape)
                                .padding(5.dp)
                                .pokemonClickable {
                                    if (it.id != state.id) {
                                        navigatePokemonDetail(it.id)
                                    }
                                },
                            model = it.profileUrl,
                            contentDescription = null
                        )
                    }
                }
                if (it != state.evolutionList.last()) {
                    PokemonIcon(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        icon = PokemonIconList.NextEvolution
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}