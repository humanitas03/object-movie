package com.example.objectmoviedomain.interfaces.store;

import com.example.objectmoviedomain.screen.DiscountCondition;

public interface DiscountConditionRepository {

    void create(DiscountCondition discountCondition);
    DiscountCondition retrieve(String discountConditionId);

}
