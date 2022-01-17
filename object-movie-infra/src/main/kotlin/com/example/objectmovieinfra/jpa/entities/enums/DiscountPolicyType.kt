package com.example.objectmovieinfra.jpa.entities.enums

enum class DiscountPolicyType(val value: String) {
    PERCENT("정률할인"),
    AMOUNT("정액할인"),
    NONE("비할인")
}
