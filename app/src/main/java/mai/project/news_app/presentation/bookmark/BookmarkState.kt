package mai.project.news_app.presentation.bookmark

import mai.project.news_app.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList(),
)
