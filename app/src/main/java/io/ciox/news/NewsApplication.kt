package io.ciox.news

import android.app.Application
import io.ciox.news.domain.PostRepository
import io.ciox.news.domain.local.database.PostDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NewsApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { PostDatabase.getInstance(this, applicationScope) }
    val repository by lazy { PostRepository(database.postDao()) }
}