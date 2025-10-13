package com.solid.onlinelearning.services;

import com.solid.onlinelearning.interfaces.DiscountPolicy;


public class BlackFridayDiscountPolicy implements DiscountPolicy {

    @Override
    public double applyDiscount(double price) {
        return price * 0.75; // 25% скидка
    }
}
