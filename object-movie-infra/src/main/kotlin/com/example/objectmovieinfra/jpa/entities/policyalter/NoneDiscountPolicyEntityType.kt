package com.example.objectmovieinfra.jpa.entities.policyalter

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("NONE")
class NoneDiscountPolicyEntityType() : DisCountPolicyJpaIEntity()
