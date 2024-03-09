package mai.project.news_app.presentation.home

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.flowOf
import mai.project.news_app.R
import mai.project.news_app.domain.model.Article
import mai.project.news_app.presentation.Dimens.MediumPadding1
import mai.project.news_app.presentation.Dimens.SmallPadding1
import mai.project.news_app.presentation.common.ArticleList
import mai.project.news_app.presentation.common.SearchBar
import mai.project.news_app.ui.theme.NewsAppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigateSearch: () -> Unit,
    navigateDetails: (Article) -> Unit,
) {
    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = "\uD83d\uDFE5") { it.title }
            } else {
                ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = SmallPadding1)
            .statusBarsPadding()
    ) {
        Image(
            modifier = Modifier
                .width(150.dp)
                .padding(horizontal = MediumPadding1),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "News App Logo",
        )

        Spacer(modifier = Modifier.height(SmallPadding1))

        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding1),
            text = "",
            readOnly = true,
            onValueChange = {},
            onClick = { navigateSearch() },
            onSearch = {},
        )

        Spacer(modifier = Modifier.height(SmallPadding1))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MediumPadding1)
                // 跑馬燈效果
                .basicMarquee(),
            text = titles,
            fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder)
        )

        Spacer(modifier = Modifier.height(SmallPadding1))

        ArticleList(
            modifier = Modifier.padding(horizontal = SmallPadding1),
            articles = articles,
        ) { article ->
            navigateDetails(article)
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Full Screen", showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    NewsAppTheme {
        Surface {
            val mockArticles = listOf(Article.sample)
            HomeScreen(
                articles = flowOf(PagingData.from(mockArticles)).collectAsLazyPagingItems(),
                navigateSearch = {},
                navigateDetails = { }
            )
        }
    }
}
