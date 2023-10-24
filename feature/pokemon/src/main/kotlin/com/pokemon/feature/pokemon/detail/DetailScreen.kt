package com.pokemon.feature.pokemon.detail

import android.media.MediaPlayer
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.pokemon.core.design_system.PokemonTheme
import com.pokemon.core.design_system.attribute.PokemonIcon
import com.pokemon.core.design_system.attribute.PokemonIconList
import com.pokemon.core.domain.entity.DetailMoveEntity
import com.pokemon.core.navigation.pokemon.PokemonDeepLinkKey
import com.pokemon.core.navigation.pokemon.PokemonNavigationItem
import com.pokemon.core.ui.component.AttributeItem
import com.pokemon.core.ui.component.InfoItem
import com.pokemon.core.ui.component.MoveItem
import com.pokemon.core.ui.component.descriptionPager
import com.pokemon.core.ui.util.toPokemonType

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

    LazyColumn(
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
                Text(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .align(Alignment.TopEnd),
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
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = state.name,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(12.dp))
            LazyRow(
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
                InfoItem(title = "분류", content = state.genus)
                Spacer(modifier = Modifier.weight(1F))
                InfoItem(title = "체중", content = "${state.weight / 10F}KG")
                Spacer(modifier = Modifier.weight(1F))
                InfoItem(title = "신장", content = "${state.height / 10F}M")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(modifier = Modifier.padding(horizontal = 15.dp), text = "사용 기술")
            Spacer(modifier = Modifier.height(4.dp))
            LazyRow(
                contentPadding = PaddingValues(horizontal = 15.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(state.moveList) {
                    MoveItem(it) {
                        if (selectedMove == null || selectedMove != it) {
                            moveDescriptionVisible = true
                            selectedMove = it
                        } else {
                            moveDescriptionVisible = false
                            selectedMove = null
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            AnimatedVisibility(visible = moveDescriptionVisible) {
                selectedMove?.let {
                    descriptionPager(
                        descriptionList = it.flavorList,
                        backgroundColor = it.type.toPokemonType().typeColor
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(modifier = Modifier.padding(horizontal = 15.dp), text = "상세 설명")
            Spacer(modifier = Modifier.height(4.dp))
            descriptionPager(descriptionList = state.flavorList)
            Spacer(modifier = Modifier.height(16.dp))
            Text(modifier = Modifier.padding(horizontal = 15.dp), text = "진화도")
            Spacer(modifier = Modifier.height(4.dp))
        }
        items(state.evolutionList) {
            LazyRow(
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
                            .clickable {
                                navController.navigate(PokemonNavigationItem.Detail.route + PokemonDeepLinkKey.ID + it.id)
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