package com.naibeck.room.persistance

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Created by Kevin Gomez on 8/20/2017.
 * Para mayor información leer {@linktourl https://developer.android.com/reference/android/arch/persistence/room/Database.html }
 */
@Database(entities = arrayOf(User::class), version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase =
                INSTANCE ?: synchronized(UserDatabase::class) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context): UserDatabase =
                Room.databaseBuilder(context, UserDatabase::class.java, "User.db")
                        .build()

    }
}