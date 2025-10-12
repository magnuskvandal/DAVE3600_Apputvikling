package com.example.bursdagsassistent_s356228.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bursdagsassistent_s356228.data.dao.FriendDao
import com.example.bursdagsassistent_s356228.data.model.Friend

@TypeConverters(value = [Converters::class])
@Database(entities = [Friend::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun friendDao(): FriendDao
}