package com.example.bankingapp;

import com.jazasoft.util.Utils;
import org.testng.annotations.Test;

@Test
public class UserTypeTest {

    public void testParse() {
        String manager = "Manager";

        UserType userType = UserType.parse(manager);

        System.out.println(userType);
        System.out.println(userType.getValue());
        System.out.println(UserType.CUSTOMER);
    }

    public void test() {
        System.out.println(Utils.getRandomNumber(10));
    }
}
