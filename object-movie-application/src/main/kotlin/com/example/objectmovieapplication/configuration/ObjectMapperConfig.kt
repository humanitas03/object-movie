package com.example.objectmovieapplication.configuration

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Configuration
class ObjectMapperConfig {

    @Bean
    fun objectMapper(): ObjectMapper {
        return jacksonObjectMapper()
            .registerModule(
                JavaTimeModule()
                    .addSerializer(
                        LocalDate::class.java,
                        LocalDateSerializer(
                            DateTimeFormatter.ISO_DATE
                        ),
                    )
                    .addSerializer(
                        LocalDateTime::class.java,
                        LocalDateTimeSerializer(
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
                        )
                    )
                    .addSerializer(
                        Duration::class.java,
                        CustomDurationSerializer()
                    )
            )
            .registerModule(Jdk8Module())
            .registerKotlinModule()
    }
}

class CustomDurationSerializer : JsonSerializer<Duration>() {
    override fun serialize(
        duration: Duration,
        generator: JsonGenerator,
        provider: SerializerProvider?
    ) {
        generator.writeNumber(duration.toMinutes())
    }
}
