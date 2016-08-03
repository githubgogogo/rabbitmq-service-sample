package com.jeremy.sample.dao;

import com.jeremy.sample.domain.entity.AccountEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
//import static org.mockito.Matchers.eq;

/**
 * Created by Jeremy Yang on 27/07/2016.
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-applicationContext.xml")
public class AccountDaoImplTest
{

    @Autowired
    AccountDao accountDao;

    @Before
    public void setup()
    {

    }

    @After
    public void tearDown()
    {
        accountDao = null;
    }

    @Test
    public void testGetAllAccount()
    {
        List<AccountEntity> accountEntityList = accountDao.getAllAccounts();
        assertNotNull(accountEntityList);
        assertEquals(1, accountEntityList.size());
    }

    @Test
    public void testGetAccountByname()
    {
        AccountEntity accountEntity = accountDao.getAccountByName("test");
        assertNotNull(accountEntity);
    }

    @Test
    public void testCreateAndDeleteAccount()
    {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setName("test1");
        accountEntity.setEmail("test1@gmail.com");
        accountEntity.setFirstName("FirstName1");
        accountEntity.setLastName("LastName1");
        AccountEntity createdAccount = accountDao.save(accountEntity);
        assertNotNull(createdAccount);
        assertNotNull(createdAccount.getId());
        assertThat(createdAccount.getName(), is("test1"));

        List<AccountEntity> accountEntityList = accountDao.getAllAccounts();
        assertNotNull(accountEntityList);
        assertEquals(2, accountEntityList.size());

        accountDao.delete(createdAccount);

        accountEntityList = accountDao.getAllAccounts();
        assertNotNull(accountEntityList);
        assertEquals(1, accountEntityList.size());
    }

}
