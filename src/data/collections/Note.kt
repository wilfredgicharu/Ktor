package com.androiddevs.data.collections

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
//note dataclass
data class Note(
    val title: String,
    val content: String,
    val date: Long,
    val owners: List<String>,
    val color: String,
    @BsonId
    val id: String = ObjectId().toString()

)