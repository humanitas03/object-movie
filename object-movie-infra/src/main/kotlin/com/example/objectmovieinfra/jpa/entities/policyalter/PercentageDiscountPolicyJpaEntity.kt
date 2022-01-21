package com.example.objectmovieinfra.jpa.entities.policyalter

import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("PERCENT")
class PercentageDiscountPolicyJpaEntity(
    @Column(columnDefinition = "DECIMAL(11,4)", nullable = true)
    var percent: BigDecimal?,
) : DisCountPolicyJpaIEntity()
