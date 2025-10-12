package com.example.bursdagsassistent_s356228.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bursdagsassistent_s356228.data.model.Friend
import kotlinx.coroutines.flow.Flow


@Dao
interface FriendDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFriend(friend: Friend)

    @Update
    suspend fun updateFriend(friend: Friend)

    @Delete
    suspend fun deleteFriend(friend: Friend)

    @Query(value = "SELECT * FROM friend_table ORDER BY name ASC")
    fun getAllFriends(): Flow<List<Friend>>

    @Query(value = "SELECT * FROM friend_table WHERE id = :id")
    fun getFriendById(id: Int): Flow<Friend?>
}