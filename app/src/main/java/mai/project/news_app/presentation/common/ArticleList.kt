package mai.project.news_app.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.flowOf
import mai.project.news_app.domain.model.Article
import mai.project.news_app.presentation.Dimens.ExtraSmallPadding2
import mai.project.news_app.presentation.Dimens.MediumPadding1

@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onItemClick: (Article) -> Unit
) {
    val handelPagingResult = handlePagingResult(articles = articles)
    if (handelPagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding2),
        ) {
            items(count = articles.itemCount) { index ->
                articles[index]?.let { article ->
                    ArticleCard(
                        article = article,
                        onClick = { onItemClick(article) }
                    )
                }
            }
        }
    }
}

@Composable
private fun handlePagingResult(
    articles: LazyPagingItems<Article>,
): Boolean {
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen(error = error)
            false
        }

        else -> true
    }
}

@Composable
private fun ShimmerEffect() {
    Column(
        verticalArrangement = Arrangement.spacedBy(MediumPadding1)
    ) {
        repeat(10) {
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = MediumPadding1)
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ArticleListPreview() {
    val mockArticles = listOf(Article.sample, Article.sample, Article.sample)
    ArticleList(
        articles = flowOf(PagingData.from(mockArticles)).collectAsLazyPagingItems(),
        onItemClick = {}
    )
}
