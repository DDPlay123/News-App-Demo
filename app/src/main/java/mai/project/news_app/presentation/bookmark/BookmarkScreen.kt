package mai.project.news_app.presentation.bookmark

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import mai.project.news_app.R
import mai.project.news_app.domain.model.Article
import mai.project.news_app.presentation.Dimens
import mai.project.news_app.presentation.Dimens.MediumPadding1
import mai.project.news_app.presentation.Dimens.SmallPadding1
import mai.project.news_app.presentation.common.ArticleList
import mai.project.news_app.presentation.navgraph.Route
import mai.project.news_app.ui.theme.NewsAppTheme

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigate: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = MediumPadding1, start = MediumPadding1, end = MediumPadding1)
    ) {
        Text(
            text = "Bookmark",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.text_title)
        )

        Spacer(modifier = Modifier.height(SmallPadding1))

        ArticleList(
            articles = state.articles,
            onItemClick = { navigate(Route.DetailsScreen.route) }
        )
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Full Screen", showBackground = true, showSystemUi = true)
@Composable
fun BookmarkScreenPreview() {
    NewsAppTheme {
        Surface {
            BookmarkScreen(
                state = BookmarkState().copy(
                    articles = listOf(Article.sample, Article.sample, Article.sample)
                ),
                navigate = {}
            )
        }
    }
}
