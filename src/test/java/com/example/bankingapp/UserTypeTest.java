package com.example.bankingapp;

import com.example.bankingapp.dao.MySQLUserDaoImpl;
import com.example.bankingapp.dao.UserDao;
import com.example.bankingapp.model.User;
import com.jazasoft.util.Utils;
import org.testng.Assert;
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

    public void testConection() {
        Assert.assertNotNull(ConnectionUtils.getConnection());
    }


}
