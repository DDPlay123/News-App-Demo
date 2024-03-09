package mai.project.news_app.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import mai.project.news_app.presentation.bookmark.BookmarkScreen
import mai.project.news_app.presentation.bookmark.BookmarkViewModel
import mai.project.news_app.presentation.home.HomeScreen
import mai.project.news_app.presentation.home.HomeViewModel
import mai.project.news_app.presentation.onboarding.OnBoardingScreen
import mai.project.news_app.presentation.onboarding.OnBoardingViewModel
import mai.project.news_app.presentation.search.SearchScreen
import mai.project.news_app.presentation.search.SearchViewModel

@Composable
fun NavGraph(
    startDestination: String,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(
                route = Route.OnBoardingScreen.route
            ) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(viewModel::onEvent)
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {
            composable(
                route = Route.NewsNavigatorScreen.route
            ) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                BookmarkScreen(
                    state = viewModel.state.value,
                    navigate = { route -> navController.navigate(route) }
                )
//                val viewModel: SearchViewModel = hiltViewModel()
//                SearchScreen(
//                    state = viewModel.state.value,
//                    event = viewModel::onEvent,
//                    navigate = { route -> navController.navigate(route) }
//                )
//                val viewModel: HomeViewModel = hiltViewModel()
//                val artists = viewModel.news.collectAsLazyPagingItems()
//                HomeScreen(
//                    articles = artists,
//                    navigate = { route -> navController.navigate(route) }
//                )
            }
        }
    }
}