package mai.project.news_app.presentation.navgraph

sealed class Route(
    val route: String
) {
    // -- App 導航 --
    /**
     * App 起始導航
     */
    data object AppStartNavigation : Route("app_start_navigation")

    /**
     * 引導畫面
     */
    data object OnBoardingScreen : Route("onboarding_screen")

    // -- 新聞導航 --
    /**
     * 新聞導航
     */
    data object NewsNavigation : Route("news_navigation")

    /**
     * 新聞導航畫面
     *
     * - 這個畫面是用來導航到其他畫面的
     */
    data object NewsNavigatorScreen : Route("news_navigator_screen")

    /**
     * 主畫面
     */
    data object HomeScreen : Route("home_screen")

    /**
     * 搜尋畫面
     */
    data object SearchScreen : Route("search_screen")

    /**
     * 書籤畫面
     */
    data object BookmarksScreen : Route("bookmarks_screen")

    /**
     * 詳細畫面
     */
    data object DetailsScreen : Route("details_screen")
}