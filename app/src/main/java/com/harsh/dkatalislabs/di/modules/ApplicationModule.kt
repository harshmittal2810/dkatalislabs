package com.harsh.dkatalislabs.di.modules

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.harsh.dkatalislabs.BuildConfig
import com.harsh.dkatalislabs.CustomApplication
import com.harsh.dkatalislabs.model.persistence.AppDatabase
import com.harsh.dkatalislabs.model.repositories.DefaultUserRepository
import com.harsh.dkatalislabs.model.repositories.UsersRepository
import com.harsh.dkatalislabs.model.services.UserService
import com.harsh.dkatalislabs.util.AppSchedulerProvider
import com.harsh.dkatalislabs.util.ConnectionHelper
import com.harsh.dkatalislabs.util.SchedulerProvider
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val BASE_URL = BuildConfig.URL_BASE
private const val DATABASE_NAME = "dkatalis-labs-db"

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideAppContext(application: CustomApplication): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = (HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectTimeout(30, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(logging)
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .client(provideOkHttp())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideUserRepository(
        retrofit: Retrofit,
        database: AppDatabase
    ): UsersRepository {
        return DefaultUserRepository(
            retrofit.create(UserService::class.java),
            database.userDao()
        )
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideConnectionHelper(context: Context) = ConnectionHelper(context)

    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()
}