package mai.project.news_app.domain.usecases.news

import mai.project.news_app.data.local.NewsDao

class SelectArticles(
    private val newsDao: NewsDao
) {

    operator fun invoke() = newsDao.getArticles()
}