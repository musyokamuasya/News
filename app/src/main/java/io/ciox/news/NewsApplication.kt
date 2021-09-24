package io.ciox.news

import android.app.Application
import io.ciox.news.domain.PostRepository
import io.ciox.news.domain.local.database.PostDatabase

class NewsApplication: Application() {
//    TODO check the correct instantiation
    val database by lazy { PostDatabase.getInstance(this) }
    val repository by lazy { PostRepository(database.postDao()) }
}