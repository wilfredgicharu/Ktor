package com.androiddevs.data.collections

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
//user dataclass
data class User(
    val email: String,
    val password: String,
    @BsonId
    val id : String = ObjectId().toString()

)