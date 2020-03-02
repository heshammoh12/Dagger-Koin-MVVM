package com.example.daggermvvm.dagger

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.daggermvvm.BuildConfig
import com.example.daggermvvm.MovieViewModel
import com.example.daggermvvm.data.MovieRepository
import androidx.lifecycle.ViewModelProviders
import com.example.daggermvvm.service.MovieServiceApi
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module(includes = [RetrofitModule::class])
class MovieServiceApiModule {
    @Singleton
    @Provides
    fun provideMovieServiceApi(retrofit: Retrofit): MovieServiceApi =
        retrofit.create(MovieServiceApi::class.java)
}

@Module(includes = [MovieServiceApiModule::class])
class MovieRepositoryModule {
    @Singleton
    @Provides
    fun provideMovieRepository(movieServiceApi: MovieServiceApi): MovieRepository =
        MovieRepository(movieServiceApi)
}

@Module(includes = [ContextModule::class, MovieRepositoryModule::class])
class MovieViewModelModule {
    @Singleton
    @Provides
    fun provideMovieRepository(context: Context, movieRepository: MovieRepository): MovieViewModel = ViewModelProviders
        .of(context as AppCompatActivity, MovieViewModel.FACTORY(movieRepository))
        .get(MovieViewModel::class.java)
}

@Module(includes = [ContextModule::class])
class RetrofitModule {
    @Provides
    @Singleton
    fun provideRetrofit(
        httpClient: OkHttpClient,
        gson: GsonConverterFactory,
        callAdapter: RxJava2CallAdapterFactory
    ): Retrofit =
        Retrofit.Builder()
            .client(httpClient)
            .addCallAdapterFactory(callAdapter)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gson)
            .build()

    @Provides
    @Singleton
    fun provideHttpClient(interceptor: Interceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor =
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { Log.d("Logging", it) })
            .apply { level = HttpLoggingInterceptor.Level.BASIC }

    @Provides
    @Singleton
    fun provideRxCallAdapter(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    fun provideGson(): GsonConverterFactory = GsonConverterFactory.create()
}

@Module
class ContextModule(val context: Context) {
    @Provides
    fun provideContext(): Context = context
}