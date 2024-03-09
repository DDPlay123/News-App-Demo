package mai.project.news_app.presentation.newsNavigator

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import mai.project.news_app.R
import mai.project.news_app.domain.model.Article
import mai.project.news_app.presentation.bookmark.BookmarkScreen
import mai.project.news_app.presentation.bookmark.BookmarkViewModel
import mai.project.news_app.presentation.details.DetailEvent
import mai.project.news_app.presentation.details.DetailViewModel
import mai.project.news_app.presentation.details.DetailsScreen
import mai.project.news_app.presentation.home.HomeScreen
import mai.project.news_app.presentation.home.HomeViewModel
import mai.project.news_app.presentation.navgraph.Route
import mai.project.news_app.presentation.newsNavigator.components.BottomNavigationItem
import mai.project.news_app.presentation.newsNavigator.components.NewsBottomNavigation
import mai.project.news_app.presentation.search.SearchScreen
import mai.project.news_app.presentation.search.SearchViewModel

@Composable
fun NewsNavigator() {
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmarks")
        )
    }

    val navController = rememberNavController()
    val backstackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }

    selectedItem = remember(backstackState) {
        when (backstackState?.destination?.route) {
            Route.HomeScreen.route -> 0
            Route.SearchScreen.route -> 1
            Route.BookmarksScreen.route -> 2
            else -> 0
        }
    }

    val isBottomNavVisible = remember(key1 = backstackState) {
        backstackState?.destination?.route == Route.HomeScreen.route ||
                backstackState?.destination?.route == Route.SearchScreen.route ||
                backstackState?.destination?.route == Route.BookmarksScreen.route
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            if (isBottomNavVisible) {
                NewsBottomNavigation(
                    items = bottomNavigationItems,
                    selected = selectedItem,
                    onSelected = { index ->
                        navigateToTab(
                            navController, when (index) {
                                0 -> Route.HomeScreen.route
                                1 -> Route.SearchScreen.route
                                2 -> Route.BookmarksScreen.route
                                else -> Route.HomeScreen.route
                            }
                        )
                    }
                )
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            modifier = Modifier
                .padding(bottom = bottomPadding),
            navController = navController,
            startDestination = Route.HomeScreen.route,
        ) {
            composable(
                route = Route.HomeScreen.route
            ) {
                val viewModel: HomeViewModel = hiltViewModel()
                val artists = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articles = artists,
                    navigateSearch = { navigateToTab(navController, Route.SearchScreen.route) },
                    navigateDetails = { article -> navigateToDetails(navController, article) }
                )
            }

            composable(
                route = Route.SearchScreen.route
            ) {
                val viewModel: SearchViewModel = hiltViewModel()
                SearchScreen(
                    state = viewModel.state.value,
                    event = viewModel::onEvent,
                    navigateDetails = { article -> navigateToDetails(navController, article) }
                )
            }

            composable(
                route = Route.BookmarksScreen.route
            ) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                BookmarkScreen(
                    state = viewModel.state.value,
                    navigateDetails = { article -> navigateToDetails(navController, article) }
                )
            }

            composable(
                route = Route.DetailsScreen.route
            ) {
                val viewModel: DetailViewModel = hiltViewModel()
                if (viewModel.sideEffect != null) {
                    Toast.makeText(LocalContext.current, viewModel.sideEffect, Toast.LENGTH_SHORT).show()
                    viewModel.onEvent(DetailEvent.RemoveSideEffect)
                }
                val article = navController.previousBackStackEntry?.savedStateHandle?.get<Article>("article")
                article?.let { _ ->
                    DetailsScreen(
                        article = article,
                        event = viewModel::onEvent,
                        navigateUp = { navController.navigateUp() }
                    )
                }
            }
        }
    }
}

private fun navigateToTab(
    navController: NavController,
    route: String
) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
        }
        restoreState = true
        launchSingleTop = true
    }
}

private fun navigateToDetails(
    navController: NavController,
    article: Article
) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(route = Route.DetailsScreen.route)
}
