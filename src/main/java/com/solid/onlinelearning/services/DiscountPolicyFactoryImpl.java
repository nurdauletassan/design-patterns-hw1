package com.solid.onlinelearning.services;

import com.solid.onlinelearning.interfaces.DiscountPolicy;
import com.solid.onlinelearning.interfaces.DiscountPolicyFactory;

public class DiscountPolicyFactoryImpl implements DiscountPolicyFactory {

    @Override
    public DiscountPolicy createDiscountPolicy(String type) {
        if (type.equals("Student")) {
            return new StudentDiscountPolicy();
        } else if (type.equals("BlackFriday")) {
            return new BlackFridayDiscountPolicy();
        }
        throw new IllegalArgumentException("Unknown discount policy type");
    }
}
