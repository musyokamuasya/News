package io.ciox.news.domain

import androidx.annotation.WorkerThread
import io.ciox.news.domain.local.dao.PostDao
import io.ciox.news.domain.local.model.Post
import kotlinx.coroutines.flow.Flow

class PostRepository(private val postDao: PostDao) {
    val allPosts: Flow<List<Post>> = postDao.getAllPosts()
    val postByTitle:Flow<List<Post>> = postDao.getPostByTitle(String())

    @WorkerThread
    suspend fun insert(post:Post){
        postDao.createPost(post)
    }

}