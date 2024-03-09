package mai.project.news_app.data.remote

import mai.project.news_app.BuildConfig
import mai.project.news_app.data.remote.dto.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY,
        @Query("sources") sources: String,
        @Query("page") page: Int,
    ): NewsResponse
}