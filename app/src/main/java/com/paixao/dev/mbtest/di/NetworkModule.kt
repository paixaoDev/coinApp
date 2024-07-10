package com.paixao.dev.mbtest.di

import com.google.gson.GsonBuilder
import com.paixao.dev.mbtest.BuildConfig
import com.paixao.dev.mbtest.data.service.CoinApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


fun provideHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    return OkHttpClient
        .Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            chain.proceed(
                chain.request()
                    .newBuilder()
                    .addHeader("X-CoinAPI-Key", BuildConfig.API_KEY)
                    .build()
            )
        }
        .addInterceptor(
            interceptor
        )
        .build()
}

fun provideConverterFactory(): GsonConverterFactory {
    val gson = GsonBuilder()
        .setLenient()
        .create()
    return GsonConverterFactory.create(gson)
}


fun provideRetrofit(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory
): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(gsonConverterFactory)
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .build()
}

fun provideService(retrofit: Retrofit): CoinApi =
    retrofit.create(CoinApi::class.java)

val networkModule = module {
    single { provideHttpClient() }
    single { provideConverterFactory() }
    single { provideRetrofit(get(), get()) }
    single { provideService(get()) }
}