package mai.project.news_app.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import mai.project.news_app.domain.model.Article

interface NewsRepository {

    fun getNews(
        sources: List<String>,
    ): Flow<PagingData<Article>>

    fun searchNews(
        sources: List<String>,
        searchQuery: String
    ): Flow<PagingData<Article>>

    suspend fun upsertArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    fun selectArticles(): Flow<List<Article>>

    suspend fun selectArticle(url: String): Article?
}