package mai.project.news_app.domain.usecases.appEntry

import kotlinx.coroutines.flow.Flow
import mai.project.news_app.domain.manager.LocalUserManager

class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {

    operator fun invoke(): Flow<Boolean> {
        return localUserManager.readAppEntry()
    }
}