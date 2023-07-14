package com.example.marvelheroes.di

import android.content.Context
import com.example.marvelheroes.common.Constants
import com.example.marvelheroes.data.repository.MarvelApiRepository
import com.example.marvelheroes.network.api.MarvelAPI
import com.example.marvelheroes.network.interceptors.ErrorInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context: Context, gson: Gson): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(ErrorInterceptor(context))
            .addInterceptor(loggingInterceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl(Constants.MarvelApiConstants.MARVEL_API_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideMarvelAPI(retrofit: Retrofit): MarvelAPI =
        retrofit.create(MarvelAPI::class.java)

    @Provides
    @Singleton
    fun provideUsersRepository(apiService: MarvelAPI): MarvelApiRepository =
        MarvelApiRepository(apiService)

}