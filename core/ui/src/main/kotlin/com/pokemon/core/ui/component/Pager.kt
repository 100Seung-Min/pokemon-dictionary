package com.pokemon.core.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun descriptionPager(
    backgroundColor: Color = Color.LightGray,
    descriptionList: List<String>,
) {
    val descriptionPagerState = rememberPagerState {
        descriptionList.size
    }
    Column(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .background(backgroundColor, RoundedCornerShape(10.dp))
            .padding(horizontal = 5.dp, vertical = 7.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(state = descriptionPagerState) {
            Text(text = descriptionList[it])
        }
        Spacer(modifier = Modifier.height(6.dp))
        if (descriptionList.size != 1) {
            LazyRow {
                items(descriptionList.size) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 3.dp)
                            .size(5.dp)
                            .background(
                                if (it == descriptionPagerState.currentPage) Color.Black else Color.Gray,
                                CircleShape
                            )
                    )
                }
            }
        }
    }
}