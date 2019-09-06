package com.example.androidtraining_mvvm

import com.google.gson.annotations.SerializedName

data class Contributor(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("login")
    var name: String = "",
    @SerializedName("avatar_url")
    var avatarUrl: String = ""
)