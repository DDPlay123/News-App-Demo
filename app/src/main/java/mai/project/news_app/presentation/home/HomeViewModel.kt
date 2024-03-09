package mai.project.news_app.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import mai.project.news_app.domain.usecases.news.NewsUseCases
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    val news = newsUseCases.getNews(
        sources = listOf("bbc-news", "cnn", "fox-news", "the-verge", "the-wall-street-journal"),
    ).cachedIn(viewModelScope)
}