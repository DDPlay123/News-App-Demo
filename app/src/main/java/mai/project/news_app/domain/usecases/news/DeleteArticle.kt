package mai.project.news_app.domain.usecases.news

import mai.project.news_app.domain.model.Article
import mai.project.news_app.domain.repository.NewsRepository

class DeleteArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article) {
        newsRepository.deleteArticle(article)
    }
}