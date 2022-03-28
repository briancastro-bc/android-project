package com.bc.notes_application.interfaces

data class Student (
    var identification: String,
    var name: String,
    var age: Int? = null,
    var phoneNumber: String,
    var address: String,
    var subject: String,
    var grades: List<String>
)