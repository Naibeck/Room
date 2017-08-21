package com.naibeck.room

import android.content.Context
import com.naibeck.room.persistance.UserDao
import com.naibeck.room.persistance.UserDatabase
import com.naibeck.room.ui.ViewModelFactory

/**
 * Created by Kevin Gomez on 8/20/2017.
 */
object Injection {
    fun provideUserDataSource(context: Context): UserDao {
        val database = UserDatabase.getInstance(context)
        return database.userDao()
    }

    fun provideUserViewModel(context: Context): ViewModelFactory {
        val dataSource = provideUserDataSource(context)
        return ViewModelFactory(dataSource)
    }
}