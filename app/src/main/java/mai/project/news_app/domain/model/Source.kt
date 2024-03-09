package mai.project.news_app.domain.model

data class Source(
    val id: String?,
    val name: String
) {
    override fun toString(): String {
        return "$id,$name"
    }
}