package com.bcp0109.springwebfluxkotlin.domain

data class Member(
    var id: Long? = null,
    val name: String,
    val age: Int
) {
    constructor(name: String, age: Int) : this(null, name, age)
}
