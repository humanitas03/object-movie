package com.example.objectmovieinfra.jpa.entity

import com.example.objectmoviedomain.screen.AmountDiscountPolicy
import com.example.objectmoviedomain.screen.DiscountPolicy
import com.example.objectmoviedomain.screen.Money
import com.example.objectmoviedomain.screen.NoneDiscountPolicy
import com.example.objectmoviedomain.screen.PercentDiscountPolicy
import com.example.objectmoviedomain.screen.SequenceCondition
import com.example.objectmovieinfra.jpa.entities.DiscountPolicyJpaEntity
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DiscountPolicyJpaEntityTest {
    val objectMapper: ObjectMapper = jacksonObjectMapper().registerModule(JavaTimeModule())
    @Test
    @DisplayName("toGenericDomaintest")
    fun toGenericDomainTest() {
        val nonePolicyTest = NoneDiscountPolicy()
        val testEntity = DiscountPolicyJpaEntity.from(nonePolicyTest)

        val nonePolicyResult = testEntity.toGenericDomain<NoneDiscountPolicy>(nonePolicyTest::class.java)
        val seqCondition = SequenceCondition(1)
        val amountPolicy = AmountDiscountPolicy(Money.wons(10), seqCondition)
        val testAmountEntity = DiscountPolicyJpaEntity.from(amountPolicy)

        val percentPolicyTest = PercentDiscountPolicy(9.0, seqCondition)
        val testPercentEntity = DiscountPolicyJpaEntity.from(percentPolicyTest)
        println("testPercentEntity : ${objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(testPercentEntity)}")

        val percentPolicyResult = testPercentEntity.toGenericDomain<DiscountPolicy>(percentPolicyTest::class.java)
        val amountPolicyResult = testAmountEntity.toGenericDomain<AmountDiscountPolicy>(amountPolicy::class.java)

        println()
        println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(nonePolicyResult))
        println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(percentPolicyResult))
        println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(amountPolicyResult))
    }
}
