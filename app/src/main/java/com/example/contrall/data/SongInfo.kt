package com.example.contrall.data

import com.google.gson.annotations.SerializedName


data class PlaylistResult (
    @SerializedName("result" ) var result : List<SongInfo>? = listOf()
)

data class SongResult (
    @SerializedName("result" ) var result : SongInfo? = SongInfo()
)

data class SongInfo (
    @SerializedName("title")  var title    : String? = null,
    @SerializedName("artist")  var artist  : String? = null,
    @SerializedName("album")  var album  : String?   = null,
    @SerializedName("duration") var duration : String? = null,
    @SerializedName("progress"  ) var progress  : String?   = null
)

//data class PlaylistSong (
//    @SerializedName("title")  var song    : String? = null,
//    @SerializedName("artist")  var artist  : String? = null,
//    @SerializedName("album")  var album  : String?   = null,
//    @SerializedName("duration") var duration : String? = null,
//)