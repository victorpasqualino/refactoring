package com.celfocus.training.entity;

import java.util.Calendar;
import java.util.Date;

public class User {
    public String userName;
    public Date birthDay;
    public boolean isAdult;


    public int getUserAge() {
        Calendar currentDayCalendar = Calendar.getInstance();
        Calendar userBirthDayCalendar = Calendar.getInstance();
        userBirthDayCalendar.setTime(this.birthDay);
        boolean userHasHadBirthday = currentDayCalendar.get(Calendar.DAY_OF_YEAR) <= userBirthDayCalendar.get(Calendar.DAY_OF_YEAR);

        return currentDayCalendar.get(Calendar.YEAR) - userBirthDayCalendar.get(Calendar.YEAR) - (userHasHadBirthday ? 1 : 0);
    }
}