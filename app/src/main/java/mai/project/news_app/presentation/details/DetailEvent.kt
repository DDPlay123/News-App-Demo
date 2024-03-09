package mai.project.news_app.presentation.details

sealed class DetailEvent {

    data object SaveArticle : DetailEvent()
}