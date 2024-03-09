package mai.project.news_app.data.remote.dto

import mai.project.news_app.domain.model.Article

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)