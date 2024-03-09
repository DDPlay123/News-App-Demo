package mai.project.news_app.presentation.details

import mai.project.news_app.domain.model.Article

sealed class DetailEvent {

    data class UpsertDeleteArticle(
        val article: Article
    ) : DetailEvent()

    data object RemoveSideEffect : DetailEvent()
}