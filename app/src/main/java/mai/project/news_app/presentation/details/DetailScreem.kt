package mai.project.news_app.presentation.details

import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import mai.project.news_app.R
import mai.project.news_app.domain.model.Article
import mai.project.news_app.presentation.Dimens.ArticleImageHeight
import mai.project.news_app.presentation.Dimens.MediumPadding1
import mai.project.news_app.presentation.Dimens.SmallPadding1
import mai.project.news_app.presentation.details.components.DetailsTopBar
import mai.project.news_app.ui.theme.NewsAppTheme

@Composable
fun DetailsScreen(
    article: Article,
    event: (DetailEvent) -> Unit,
    navigateUp: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopBar(
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW).apply {
                    data = android.net.Uri.parse(article.url)
                    if (resolveActivity(context.packageManager) != null) {
                        context.startActivity(this)
                    }
                }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, article.url)
                    if (resolveActivity(context.packageManager) != null) {
                        context.startActivity(this)
                    }
                }
            },
            onBookmarkClick = { event(DetailEvent.SaveArticle) },
            onBackClick = navigateUp
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(
                start = MediumPadding1,
                end = MediumPadding1,
                top = MediumPadding1
            )
        ) {
            item {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ArticleImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    model = ImageRequest.Builder(context)
                        .data(article.urlToImage)
                        .build(),
                     contentDescription = "Article Image"
                )

                Spacer(modifier = Modifier.height(SmallPadding1))

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(id = R.color.text_title)
                )

                Spacer(modifier = Modifier.height(SmallPadding1))

                Text(
                    text = article.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(id = R.color.body)
                )
            }
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Full Screen", showBackground = true, showSystemUi = true)
@Composable
fun DetailsScreenPreview() {
    NewsAppTheme {
        Surface {
            DetailsScreen(
                article = Article.sample,
                event = {},
                navigateUp = {}
            )
        }
    }
}
