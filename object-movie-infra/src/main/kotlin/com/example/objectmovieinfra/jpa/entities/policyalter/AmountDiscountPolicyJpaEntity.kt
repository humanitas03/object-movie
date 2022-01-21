package com.example.objectmovieinfra.jpa.entities.policyalter

import com.fasterxml.jackson.annotation.JsonFormat
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("AMOUNT")
class AmountDiscountPolicyJpaEntity(
    @Column(nullable = true, columnDefinition = "BIGINT(15)")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    var amount: BigDecimal?,
) : DisCountPolicyJpaIEntity()
