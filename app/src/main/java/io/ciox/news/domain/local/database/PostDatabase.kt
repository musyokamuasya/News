package io.ciox.news.domain.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import io.ciox.news.domain.local.dao.PostDao
import io.ciox.news.domain.local.model.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Post::class], version = 1, exportSchema = false)
public abstract class PostDatabase:RoomDatabase() {
    abstract fun postDao():PostDao
//    Callback class
    private class PostDatabaseCallback(private val scope: CoroutineScope):RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {
                database -> scope.launch {
                    populateDatabase(database.postDao())
                }
            }
        }

    private suspend fun populateDatabase(postDao: PostDao) {
        postDao.deletePosts()
        var post = Post(1, "Burnely is playing Manchester tomorrow", "The players are happy. The weekend is finally here. Lets go play")
        postDao.createPost(post)

        post = Post(2, "Arsenal will definitely lose", "It is another weekend of heart attack as Arsenal fans have to witness another loss")
        postDao.createPost(post)

        post = Post(3, "Arsenal will definitely lose", "It is another weekend of heart attack as Arsenal fans have to witness another loss")
        postDao.createPost(post)

        post = Post(4, "Arsenal will definitely lose", "It is another weekend of heart attack as Arsenal fans have to witness another loss")
        postDao.createPost(post)



    }
}
    companion object{
        private const val DB_NAME = "post_database"
        @Volatile
        private var INSTANCE: PostDatabase? = null

        fun getInstance(context: Context, applicationScope: CoroutineScope):PostDatabase{

            synchronized(this){
                var instance = INSTANCE
                if (instance==null){
                    instance = Room.databaseBuilder(context.applicationContext, PostDatabase::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }

        }

        }
    }

