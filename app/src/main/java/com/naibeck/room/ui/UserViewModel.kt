package com.naibeck.room.ui

import android.arch.lifecycle.ViewModel
import com.naibeck.room.persistance.User
import com.naibeck.room.persistance.UserDao
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.functions.Action
import io.reactivex.internal.operators.completable.CompletableFromAction

/**
 * Created by Kevin Gomez on 8/20/2017.
 * Puedes leer mas acerca del ViewModel en {@linktourl https://developer.android.com/topic/libraries/architecture/viewmodel.html }
 */
class UserViewModel(private val dataSource: UserDao): ViewModel() {

    /**
     * Will bring an username from a userid. For demo purposes the userid is mocked.
     * @return a [Flowable]containing the username.
     */
    fun userName(): Flowable<String> = dataSource.getUserById(USER_ID)
            .map { user -> user.username }


    /**
     * Will update a user if is existing.
     *
     * @return a [Completable] that completes once user is updated
     */
    fun updateUser(userName: String): Completable {
        return CompletableFromAction(Action {
            val user = User(USER_ID, userName)
            dataSource.insertUser(user)
        })
    }
    companion object {
        const val USER_ID = "1"
    }
}