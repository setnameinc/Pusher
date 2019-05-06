package com.setname.pusher.mvp.room.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject


@Entity(tableName = "messages")
data class MessagesDatabaseModel(
    @PrimaryKey(autoGenerate = true) val time: Long,
    @TypeConverters(JSONObject::class) val main: MessageMainModel,
    @ColumnInfo val posted:Boolean
)

data class MessageMainModel(val message: String)
