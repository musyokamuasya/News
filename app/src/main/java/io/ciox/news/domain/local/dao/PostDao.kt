package io.ciox.news.domain.local.dao

import androidx.room.*
import io.ciox.news.domain.local.model.Post
import kotlinx.coroutines.flow.Flow


@Dao
interface PostDao {
    @Query("SELECT * FROM post_table ")
    fun getAllPosts(): Flow<List<Post>>

    @Query("SELECT * FROM post_table WHERE title LIKE :name")
    fun getPostByTitle (name:String): Flow<List<Post>>

    @Delete
    suspend fun deletePosts()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createPost(vararg post:Post)

    @Update
    suspend fun updatePost(vararg post:Post)

}