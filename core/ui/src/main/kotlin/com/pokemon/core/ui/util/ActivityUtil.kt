package com.pokemon.core.ui.util

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun getActivity() = LocalContext.current as ComponentActivity