package com.example.bankingapp.dao;

import com.example.bankingapp.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
public class InMemoryUserDaoImplTest {

    InMemoryUserDaoImpl inMemoryUserDao;

    @BeforeClass
    public void setUp() {
        inMemoryUserDao = new InMemoryUserDaoImpl();
    }

    public void authenticate() {
        User user = inMemoryUserDao.authenticate("zahid7292", "zahid");
        Assert.assertNotNull(user);

        user = inMemoryUserDao.authenticate("zahid", "zahid");
        Assert.assertNull(user);
    }

    public void save() {
        Assert.assertEquals(inMemoryUserDao.count(), 1L);

        User user = new User("Arvind Kumar", "arvind123", "arvind");
        user = inMemoryUserDao.save(user);

        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
        Assert.assertEquals(inMemoryUserDao.count(), 2L);
    }



    public void test() {

    }
}
