package com.naibeck.room.persistance

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable

/**
 * Created by Kevin Gomez on 8/20/2017.
 * Para mayor información leer {@linktourl https://developer.android.com/reference/android/arch/persistence/room/Dao.html }
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM Users where userid = :id")
    fun getUserById(id: String): Flowable<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("DELETE FROM Users")
    fun deleteAllUsers()
}