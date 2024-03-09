package mai.project.news_app.presentation.search

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.collectAsLazyPagingItems
import mai.project.news_app.presentation.Dimens.MediumPadding1
import mai.project.news_app.presentation.Dimens.SmallPadding1
import mai.project.news_app.presentation.common.ArticleList
import mai.project.news_app.presentation.common.SearchBar
import mai.project.news_app.presentation.navgraph.Route

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigate: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                top = MediumPadding1
            )
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding1),
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = { event(SearchEvent.SearchNews) },
        )

        Spacer(modifier = Modifier.height(SmallPadding1))

        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticleList(
                modifier = Modifier.padding(horizontal = SmallPadding1),
                articles = articles,
                onItemClick = { navigate(Route.DetailsScreen.route) }
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Full Screen", showBackground = true, showSystemUi = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        state = SearchState(),
        event = {},
        navigate = {}
    )
}

