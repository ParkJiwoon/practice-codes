package com.bcp0109.springwebfluxkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import reactor.blockhound.BlockHound

@SpringBootApplication
class SpringWebfluxKotlinApplication

fun main(args: Array<String>) {
    BlockHound.install()
    runApplication<SpringWebfluxKotlinApplication>(*args)
}
