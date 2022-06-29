package com.osung.workersample.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.osung.workersample.repository.database.entity.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        UserEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        fun getInstance(context: Context): AppDatabase = Room
            .databaseBuilder(context, AppDatabase::class.java, "sample.db")
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)

                    CoroutineScope(Dispatchers.IO).launch {
                        getInstance(context).userDao().insert(UserEntity(0, "김평호", 25, "010-0000-0000"))
                        getInstance(context).userDao().insert(UserEntity(0, "김혜림", 25, "010-0000-0000"))
                        getInstance(context).userDao().insert(UserEntity(0, "박진", 20, "010-0000-0000"))
                    }
                }
            })
            .build()
    }
}