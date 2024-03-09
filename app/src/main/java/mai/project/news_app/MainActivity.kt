package mai.project.news_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import mai.project.news_app.presentation.navgraph.NavGraph
import mai.project.news_app.ui.theme.NewsAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 設置系統 UI 延伸到屏幕邊緣，現遷移到 edgeToEdge API
//        setupSystemUIFitWindow()
        // 先顯示啟動畫面
        installSplashScreen().apply {
            // 保持啟動畫面顯示條件，直到 ViewModel 中的 splashCondition 變為 false
            setKeepOnScreenCondition { viewModel.splashCondition }
        }
        // 啟用 edgeToEdge API，使 UI 延伸到屏幕邊緣
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
                // 用於實現系統狀態欄和導航欄的完全透明，現遷移到 edgeToEdge API
//                SetupSystemBarsColorWithAccompanist()
                // 主畫面
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val startDestination = viewModel.startDestination
                    NavGraph(startDestination = startDestination)
                }
            }
        }
    }

    /**
     * 用於延伸到屏幕邊緣
     *
     * - false: 不需要自動調整佈局，使 UI 延伸到屏幕邊緣
     * - 用於搭配 [SetupSystemBarsColorWithAccompanist] 一起使用
     */
    private fun setupSystemUIFitWindow() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    /**
     * 用於實現系統狀態欄和導航欄的完全透明
     *
     * - 使用 Accompanist Library
     * @see <a href="https://google.github.io/accompanist/systemuicontroller/">Accompanist</a>
     */
    @Composable
    private fun SetupSystemBarsColorWithAccompanist() {
        // 用於實現系統狀態欄和導航欄的完全透明
        val isSystemInDarkTheme = isSystemInDarkTheme()
        val systemController = rememberSystemUiController()
        // 重組時，根據系統主題切換系統狀態欄和導航欄的顏色
        LaunchedEffect(isSystemInDarkTheme, systemController) {
            systemController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = !isSystemInDarkTheme
            )
        }
    }
}
