package mai.project.news_app.presentation.onboarding

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import mai.project.news_app.presentation.Dimens.MediumPadding2
import mai.project.news_app.presentation.Dimens.PageIndicatorWidth
import mai.project.news_app.presentation.common.NewsButton
import mai.project.news_app.presentation.common.NewsTextButton
import mai.project.news_app.presentation.onboarding.components.OnBoardingPage
import mai.project.news_app.presentation.onboarding.components.PageIndicator
import mai.project.news_app.ui.theme.NewsAppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    event: (OnBoardingEvent) -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // 記住當前頁面的狀態
        val pageState = rememberPagerState(initialPage = 0) { pages.size }
        val buttonState by remember {
            // 確保只有發生變化時才會重新計算
            derivedStateOf {
                when (pageState.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Get Started")
                    else -> listOf("", "")
                }
            }
        }

        HorizontalPager(state = pageState) { index ->
            OnBoardingPage(
                page = pages[index]
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding2)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PageIndicator(
                modifier = Modifier.width(PageIndicatorWidth),
                pageSize = pages.size,
                selectedPage = pageState.currentPage
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                val scope = rememberCoroutineScope()

                if (buttonState[0].isNotEmpty()) {
                    NewsTextButton(
                        text = buttonState[0]
                    ) {
                        scope.launch {
                            pageState.animateScrollToPage(pageState.currentPage - 1)
                        }
                    }
                }

                NewsButton(
                    text = buttonState[1]
                ) {
                    scope.launch {
                        if (pageState.currentPage == pages.size - 1) {
                            event.invoke(OnBoardingEvent.SaveAppEntry)
                        } else {
                            pageState.animateScrollToPage(pageState.currentPage + 1)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(.5f))
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Full Screen", showBackground = true, showSystemUi = true)
@Composable
fun OnBoardingScreenPreview() {
    NewsAppTheme {
        Surface { OnBoardingScreen() }
    }
}
