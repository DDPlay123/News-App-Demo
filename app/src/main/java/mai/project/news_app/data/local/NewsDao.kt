package mai.project.news_app.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import mai.project.news_app.domain.model.Article
import mai.project.news_app.util.Constants.ARTICLE_TABLE

@Dao
interface NewsDao {

    @Query("SELECT * FROM $ARTICLE_TABLE ORDER BY publishedAt DESC")
    fun getArticles(): Flow<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article)

    @Delete
    suspend fun delete(article: Article)
}