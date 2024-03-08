package mai.project.news_app.presentation.onboarding

sealed class OnBoardingEvent {

    data object SaveAppEntry : OnBoardingEvent()
}