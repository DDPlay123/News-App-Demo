package mai.project.news_app.presentation.search

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import mai.project.news_app.domain.model.Article

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null,
)