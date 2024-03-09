package mai.project.news_app.presentation.common

import android.content.res.Configuration
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mai.project.news_app.R
import mai.project.news_app.presentation.Dimens
import mai.project.news_app.presentation.Dimens.MediumPadding1
import mai.project.news_app.ui.theme.NewsAppTheme

@Composable
fun Modifier.shimmerEffect(): Modifier {
    val transition = rememberInfiniteTransition(label = "ShimmerTransition")
    val translateAnim by transition.animateFloat(
        initialValue = -1000f,
        targetValue = 2000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1800, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "ShimmerTransition"
    )
    val shimmerColors = listOf(
        colorResource(id = R.color.shimmer).copy(alpha = 0.3f),
        colorResource(id = R.color.shimmer).copy(alpha = 0.7f),
        colorResource(id = R.color.shimmer).copy(alpha = 0.3f)
    )
    val brush = Brush.linearGradient(
        colors = shimmerColors,
        // 主要是避免重新開始時，在座標0開始會不太自然
        start = Offset(translateAnim - 1000f, translateAnim - 1000f),
        end = Offset(translateAnim, translateAnim)
    )
    return then(background(brush = brush))
}

@Composable
fun ArticleCardShimmerEffect(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .size(Dimens.ArticleCardSize)
                .clip(MaterialTheme.shapes.medium)
                .shimmerEffect(),
        )

        Column(
            modifier = Modifier
                .padding(horizontal = Dimens.ExtraSmallPadding)
                .height(Dimens.ArticleCardSize),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .padding(horizontal = MediumPadding1)
                    .shimmerEffect(),
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(fraction = .8f)
                        .height(15.dp)
                        .padding(horizontal = MediumPadding1)
                        .shimmerEffect(),
                )
            }
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ArticleCardShimmerEffectPreview() {
    NewsAppTheme {
        Surface {
            ArticleCardShimmerEffect()
        }
    }
}

