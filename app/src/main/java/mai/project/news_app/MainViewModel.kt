package mai.project.news_app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import mai.project.news_app.domain.usecases.appEntry.AppEntryUseCases
import mai.project.news_app.presentation.navgraph.Route
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    /**
     * 是否顯示啟動畫面，當為 `false` 時，代表啟動畫面已經結束
     */
    var splashCondition by mutableStateOf(true)
        private set

    /**
     * 目的地
     */
    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set

    init {
        appEntryUseCases.readAppEntry().onEach { shouldStartFromHomeScreen ->
            // 根據是否應該從首頁開始，設定目的地
            startDestination = if (shouldStartFromHomeScreen) {
                Route.NewsNavigation.route
            } else {
                Route.AppStartNavigation.route
            }
            // 顯示啟動畫面 300 毫秒
            delay(timeMillis = 300L)
            splashCondition = false
        }.launchIn(viewModelScope)
    }
}