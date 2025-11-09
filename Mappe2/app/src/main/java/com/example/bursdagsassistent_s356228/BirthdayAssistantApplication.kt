package com.example.bursdagsassistent_s356228

import android.app.Application
import androidx.room.Room
import com.example.bursdagsassistent_s356228.data.database.AppDatabase
import com.example.bursdagsassistent_s356228.repositories.FriendRepository
import com.example.bursdagsassistent_s356228.repositories.PreferencesRepository

class BirthdayAssistantApplication: Application() {

    lateinit var database: AppDatabase
        private set
    lateinit var friendRepository: FriendRepository
        private set
    lateinit var preferencesRepository: PreferencesRepository
        private set


    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "birthday_assistant_database"
        ).build()
        friendRepository = FriendRepository(friendDao = database.friendDao())
        preferencesRepository = PreferencesRepository(application = this)
    }
}