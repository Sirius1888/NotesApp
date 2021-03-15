package com.example.noteapplication.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Project(
        var id: Long? = null,
        var color: Int? = null,
        var name: String? = null,
        @SerializedName("comment_count")
        var commentCount: Int? = null,
        var shared: Boolean? = null,
        var favorite: Boolean? = null,
        @SerializedName("sync_id")
        var syncId: Int? = null,
        @SerializedName("inbox_project")
        var inboxProject: Boolean? = null
) : Serializable
