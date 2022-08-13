package com.a2022sunrinhackathon.data.firebase

data class postDTO(
    var userId : String? = null,
    var imageUrl : String? = null,
    var comment : String? = null,
    var address : String? = null,
    var rating : Float? = null,
    var timeStamp : Long? = null,
    var time : String? = null
)