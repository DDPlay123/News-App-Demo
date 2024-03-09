package mai.project.news_app.domain.usecases.news

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import mai.project.news_app.domain.model.Article
import mai.project.news_app.domain.repository.NewsRepository

class SearchNews(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(
        query: String,
        sources: List<String>
    ): Flow<PagingData<Article>> {
        return newsRepository.searchNews(searchQuery = query, sources = sources)
    }
}