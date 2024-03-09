package mai.project.news_app.data.local

import androidx.room.TypeConverter
import mai.project.news_app.domain.model.Source

class NewsTypeConvertor {

    @TypeConverter
    fun sourceToString(source: Source): String {
        return source.toString()
    }

    @TypeConverter
    fun stringToSource(string: String): Source {
        val split = string.split(",")
        return Source(split[0], split[1])
    }
}