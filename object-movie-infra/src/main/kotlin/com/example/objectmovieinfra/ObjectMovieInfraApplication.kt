package com.example.objectmovieinfra

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EntityScan(basePackages = ["com.example"])
@EnableJpaRepositories(
    basePackages = ["com.example"]
)
class ObjectMovieInfraApplication

fun main(args: Array<String>) {
    runApplication<ObjectMovieInfraApplication>(*args)
}
