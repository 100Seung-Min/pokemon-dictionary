package com.pokemon.core.data.remote.util

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.pokemon.core.domain.exception.ConflictDataException
import com.pokemon.core.domain.exception.InvalidTokenException
import com.pokemon.core.domain.exception.NotFoundException
import com.pokemon.core.domain.exception.ServerErrorException
import com.pokemon.core.domain.exception.TooManyRequestException
import com.pokemon.core.domain.exception.UnKnownHttpException
import com.pokemon.core.domain.exception.WrongDataException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

data class Error(
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Int?,
)

suspend inline fun <T> pokemonApiCall(
    crossinline callFunction: suspend () -> T,
): T =
    try {
        withContext(Dispatchers.IO) {
            callFunction()
        }
    } catch (e: HttpException) {
        val error = e.getError() ?: Error("", e.code())
        throw when (error.status) {
            400 -> WrongDataException(error.message)
            401 -> InvalidTokenException(error.message)
            404 -> NotFoundException(error.message)
            409 -> ConflictDataException(error.message)
            429 -> TooManyRequestException(error.message)
            in 500..600 -> ServerErrorException(error.message)
            else -> UnKnownHttpException(error.message)
        }
    }

fun HttpException.getError(): Error? =
    response()?.errorBody()?.let { Gson().fromJson(it.string(), Error::class.java) }