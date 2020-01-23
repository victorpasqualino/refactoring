package com.celfocus.training.util;

import com.celfocus.training.entity.User;

public class Discounts {

    private static final int OLD_AGE = 80;
    private static final double SENIOR_DISCOUNT = 0.1;
    private static final double ADULT_DISCOUNT = 0.2;
    private static final double CHILD_DISCOUNT = 0.0;

    public static double calculateDiscount(User user) {
        boolean userIsAChild = !user.isAdult;
        if (userIsAChild) return CHILD_DISCOUNT;

        int userAge = user.getUserAge();
        boolean userIsOld = userAge >= OLD_AGE;

        if (userIsOld) {
            return SENIOR_DISCOUNT;
        }
        return ADULT_DISCOUNT;
    }
}
