package com.jitusolution.adv160420074week4.model

import com.google.gson.annotations.SerializedName

data class Student(
//    val id:String?,
//    val name:String?,
//    val dob:String?,
//    val phone:String?,
//    val photoUrl:String?
    var id:String?,
    @SerializedName("student_name")
    var name:String?,
    @SerializedName("birth_of_date")
    var bod:String?,
    var phone:String?,
    @SerializedName("photo_url")
    var photoUrl:String

)