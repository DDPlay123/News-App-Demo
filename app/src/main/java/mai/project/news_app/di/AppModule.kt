package mai.project.news_app.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mai.project.news_app.data.manager.LocalUserManagerImpl
import mai.project.news_app.data.remote.NewsApi
import mai.project.news_app.data.repository.NewsRepositoryImpl
import mai.project.news_app.domain.manager.LocalUserManager
import mai.project.news_app.domain.repository.NewsRepository
import mai.project.news_app.domain.usecases.appEntry.AppEntryUseCases
import mai.project.news_app.domain.usecases.appEntry.ReadAppEntry
import mai.project.news_app.domain.usecases.appEntry.SaveAppEntry
import mai.project.news_app.domain.usecases.news.GetNews
import mai.project.news_app.domain.usecases.news.NewsUseCases
import mai.project.news_app.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        @ApplicationContext
        context: Context
    ): LocalUserManager = LocalUserManagerImpl(context)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository = NewsRepositoryImpl(newsApi)

    @Singleton
    @Provides
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ) = NewsUseCases(
        getNews = GetNews(newsRepository)
    )
}