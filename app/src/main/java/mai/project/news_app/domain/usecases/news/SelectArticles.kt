package mai.project.news_app.domain.usecases.news

import mai.project.news_app.domain.repository.NewsRepository

class SelectArticles(
    private val newsRepository: NewsRepository
) {

    operator fun invoke() = newsRepository.selectArticles()
}