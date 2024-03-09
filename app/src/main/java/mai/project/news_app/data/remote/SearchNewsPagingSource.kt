package mai.project.news_app.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import mai.project.news_app.domain.model.Article

class SearchNewsPagingSource(
    private val newsApi: NewsApi,
    private val sources: String,
    private val searchQuery: String
): PagingSource<Int, Article>() {

    private var totalNewsCount: Int = 0

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val response = newsApi.searchNews(page = page, sources = sources, searchQuery = searchQuery)
            totalNewsCount = response.articles.size
            val articles = response.articles.distinctBy { it.title } // 刪除重複項
            LoadResult.Page(
                data = articles,
                prevKey = null,
                nextKey = if (totalNewsCount == response.totalResults) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}