package com.naibeck.room.persistance

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Kevin Gomez on 8/20/2017.
 * Para mayor información leer  {@linktourl https://medium.com/@tonyowen/room-entity-annotations-379150e1ca82 }
 * {@linktourl https://developer.android.com/reference/android/arch/persistence/room/Entity.html }
 */
@Entity(tableName = "users")
data class User(@PrimaryKey @ColumnInfo(name = "userid") val userid: String?,
                @ColumnInfo(name = "username") val username: String? = "")