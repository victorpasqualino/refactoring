package com.celfocus.training;

import com.celfocus.training.entity.User;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Calendar;
import java.util.Date;

public class AppTest extends TestCase {

    public AppTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testUserIsOld() {
        Calendar cal = Calendar.getInstance();
        cal.set(1940, Calendar.JANUARY, 1);
        Date date = new Date();
        date.setTime(cal.getTimeInMillis());
        User user = new User();
        user.birthDay = date;
        assertTrue( user.getUserAge() >= 80 );
    }

    public void testUserIsNotOld() {
        Calendar cal = Calendar.getInstance();
        cal.set(1940, Calendar.DECEMBER, 1);
        Date date = new Date();
        date.setTime(cal.getTimeInMillis());
        User user = new User();
        user.birthDay = date;
        assertTrue( user.getUserAge() < 80 );
    }

}
