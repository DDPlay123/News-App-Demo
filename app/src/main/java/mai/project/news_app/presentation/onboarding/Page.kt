package mai.project.news_app.presentation.onboarding

import androidx.annotation.DrawableRes
import mai.project.news_app.R

/**
 * 起始介紹頁面的 model
 *
 * @param title 頁面標題
 * @param description 頁面描述
 * @param image 頁面圖片
 */
data class Page(
    val title: String,
    val description: String,
    @DrawableRes
    val image: Int
)

/**
 * 預設的 Onboarding 頁面資料
 */
val pages = listOf(
    Page(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.onboarding3
    )
)
