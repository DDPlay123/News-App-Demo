package mai.project.news_app.domain.usecases.news

import mai.project.news_app.domain.repository.NewsRepository

class SelectArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(url: String) = newsRepository.selectArticle(url)
}