package com.jeremy.sample.service;

import com.jeremy.sample.dao.AccountDao;
import com.jeremy.sample.domain.entity.AccountEntity;
import com.jeremy.sample.domain.messaging.Account;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by Jeremy Yang on 29/07/2016.
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-applicationContext.xml")
public class AccountServiceImplTest
{

    @Autowired
    AccountService accountService;

    @Mock
    AccountDao accountDao;

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown()
    {
        accountService = null;
    }

    @Test
    public void testGetAll()
    {
        AccountEntity accountEntity = getAccountEntity();
        when(accountDao.getAllAccounts()).thenReturn(Arrays.asList(accountEntity));
        List<Account> accounts = accountService.getAll();
        assertNotNull(accounts);
        assertThat(accounts.size(), is(1));
        Account account = accounts.get(0);
        assertThat(account.getId(), is(1L));
        assertThat(account.getName(), is("test"));
    }

    private AccountEntity getAccountEntity()
    {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(1L);
        accountEntity.setEmail("test@gmail.com");
        accountEntity.setName("test");
        accountEntity.setFirstName("Jeremy");
        accountEntity.setLastName("Yang");
        accountEntity.setCreatedAt(new Date());
        accountEntity.setCreatedBy("system");
        return accountEntity;
    }
}
