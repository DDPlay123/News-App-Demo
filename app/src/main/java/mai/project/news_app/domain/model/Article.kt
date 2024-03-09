package mai.project.news_app.domain.model

data class Article(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
    val source: Source
) {
    companion object {
        val sample: Article
            get() {
                return Article(
                    title = "Why Bitcoin Won’t Reach \$1,000,000",
                    description = "Coindesk’s Andy Baehr says Bitcoin is just a normal, bread and butter investment now.",
                    urlToImage = "https://i.kinja-img.com/image/upload/c_fill,h_675,pg_1,q_80,w_1200/d27f57afb7ac3501d4d69ce6afff190b.jpg",
                    url = "https://gizmodo.com/why-bitcoin-won-t-reach-1-000-000-1851252185",
                    publishedAt = "2024-02-13T18:15:00Z",
                    source = Source(
                        name = "Gizmodo.com",
                        id = null
                    ),
                    author = "Artem Golub and Thomas Germain",
                    content = "Coindesks Andy Baehr says Bitcoin is just a normal, bread and butter investment now."
                )
            }
    }
}