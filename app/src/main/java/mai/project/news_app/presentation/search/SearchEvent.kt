package mai.project.news_app.presentation.search

sealed class SearchEvent {

    data class UpdateSearchQuery(
        val query: String
    ) : SearchEvent()

    data object SearchNews : SearchEvent()
}