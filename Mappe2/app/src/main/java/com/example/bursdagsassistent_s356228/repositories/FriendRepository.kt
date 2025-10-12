package com.example.bursdagsassistent_s356228.repositories

import com.example.bursdagsassistent_s356228.data.dao.FriendDao
import com.example.bursdagsassistent_s356228.data.model.Friend
import kotlinx.coroutines.flow.Flow

class FriendRepository(private val friendDao: FriendDao) {
    val allFriends: Flow<List<Friend>> = friendDao.getAllFriends()

    suspend fun insertFriend(friend: Friend){
        friendDao.insertFriend(friend)
    }

    suspend fun updateFriend(friend: Friend){
        friendDao.updateFriend(friend)
    }

    suspend fun deleteFriend(friend: Friend){
        friendDao.deleteFriend(friend)
    }

    fun getFriendById(id: Int) : Flow<Friend?> {
        return friendDao.getFriendById(id)
    }
}