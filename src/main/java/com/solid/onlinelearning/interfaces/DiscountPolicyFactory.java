package com.solid.onlinelearning.interfaces;


public interface DiscountPolicyFactory {
    DiscountPolicy createDiscountPolicy(String type);
}
