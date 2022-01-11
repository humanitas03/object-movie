package com.example.objectmovieapplication

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.example"])
class ObjectMovieApplication

fun main(args: Array<String>) {
    runApplication<ObjectMovieApplication>(*args)
}
