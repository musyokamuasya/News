package io.ciox.news.domain.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.ciox.news.domain.local.dao.PostDao
import io.ciox.news.domain.local.model.Post

@Database(entities = [Post::class], version = 1, exportSchema = false)
public abstract class PostDatabase:RoomDatabase() {
    abstract fun postDao():PostDao

    companion object{
        private const val DB_NAME = "post_database"
        @Volatile
        private var INSTANCE: PostDatabase? = null

        fun getInstance(context: Context):PostDatabase{

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
