package com.naibeck.room.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.naibeck.room.persistance.UserDao

/**
 * Created by Kevin Gomez on 8/20/2017.
 */
class ViewModelFactory(private val dataSource: UserDao): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Can't handle this ViewModel")
    }
}