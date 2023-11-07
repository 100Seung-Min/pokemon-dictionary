package com.pokemon.core.data.di

import android.content.Context
import com.pokemon.core.data.BuildConfig
import com.pokemon.core.remote.api.EvolutionAPI
import com.pokemon.core.remote.api.GenerationAPI
import com.pokemon.core.remote.api.ItemAPI
import com.pokemon.core.remote.api.MoveAPI
import com.pokemon.core.remote.api.PokemonAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideOkHttpclient(
        @ApplicationContext context: Context,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(Cache(context.cacheDir, 20L * 1024 * 1024))
            .build()
    }

    @Provides
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun providePokemonAPI(retrofit: Retrofit): PokemonAPI = retrofit.create(PokemonAPI::class.java)

    @Provides
    fun provideMoveAPI(retrofit: Retrofit): MoveAPI = retrofit.create(MoveAPI::class.java)

    @Provides
    fun provideEvolutionAPI(retrofit: Retrofit): EvolutionAPI =
        retrofit.create(EvolutionAPI::class.java)

    @Provides
    fun provideGenerationAPI(retrofit: Retrofit): GenerationAPI =
        retrofit.create(GenerationAPI::class.java)

    @Provides
    fun provideItemAPI(retrofit: Retrofit): ItemAPI = retrofit.create(ItemAPI::class.java)
}