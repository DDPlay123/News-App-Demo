package mai.project.news_app.domain.usecases.news

import mai.project.news_app.data.local.NewsDao
import mai.project.news_app.domain.model.Article

class UpsertArticle(
    private val newsDao: NewsDao
) {
    suspend operator fun invoke(article: Article) {
        newsDao.upsert(article)
    }
}