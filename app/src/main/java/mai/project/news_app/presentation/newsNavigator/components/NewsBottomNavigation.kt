package mai.project.news_app.presentation.newsNavigator.components

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mai.project.news_app.R
import mai.project.news_app.presentation.Dimens.ExtraSmallPadding2
import mai.project.news_app.presentation.Dimens.IconSize
import mai.project.news_app.ui.theme.NewsAppTheme

@Composable
fun NewsBottomNavigation(
    items: List<BottomNavigationItem>,
    selected: Int,
    onSelected: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, item -> 
            NavigationBarItem(
                selected = index == selected,
                onClick = { onSelected(index) },
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            modifier = Modifier.size(IconSize),
                            painter = painterResource(id = item.icon),
                            contentDescription = item.text
                        )

                        Spacer(modifier = Modifier.height(ExtraSmallPadding2))

                        Text(
                            text = item.text,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = colorResource(id = R.color.body),
                    unselectedTextColor = colorResource(id = R.color.body)
                )
            )
        }
    }
}

data class BottomNavigationItem(
    @DrawableRes
    val icon: Int,
    val text: String
)

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun NewsBottomNavigationPreview() {
    NewsAppTheme {
        Surface {
            NewsBottomNavigation(
                items = listOf(
                    BottomNavigationItem(
                        icon = R.drawable.ic_home,
                        text = "Home"
                    ),
                    BottomNavigationItem(
                        icon = R.drawable.ic_search,
                        text = "Search"
                    ),
                    BottomNavigationItem(
                        icon = R.drawable.ic_bookmark,
                        text = "Bookmarks"
                    )
                ),
                selected = 0,
                onSelected = {}
            )
        }
    }
}

