package com.jeremy.sample.service;

import com.jeremy.sample.dao.AccountDao;
import com.jeremy.sample.domain.Status;
import com.jeremy.sample.domain.entity.AccountEntity;
import com.jeremy.sample.domain.messaging.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeremy Yang on 27/07/2016.
 */
@Service
public class AccountServiceImpl implements AccountService
{
    private final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private AccountConverter accountConverter;

    public List<Account> getAll()
    {
        LOGGER.debug("getAll start.");
        List<Account> accounts = new ArrayList<Account>();
        List<AccountEntity> accountEntities = accountDao.getAllAccounts();
        for (AccountEntity accountEntity : accountEntities)
        {
            Account account = accountConverter.convert(accountEntity);
            accounts.add(account);
        }
        return accounts;
    }

    public Account getById(Long id)
    {
        AccountEntity accountEntity = accountDao.getById(id);
        return accountConverter.convert(accountEntity);
    }

    public Account getByName(String name)
    {
        AccountEntity accountEntity = accountDao.getAccountByName(name);
        return accountConverter.convert(accountEntity);
    }

    public Account create(Account account)
    {
        AccountEntity accountEntity = accountConverter.convert(account);
        accountEntity = accountDao.save(accountEntity);
        return accountConverter.convert(accountEntity);
    }

    public Account update(Account account)
    {
        AccountEntity accountEntity = accountDao.getById(account.getId());
        BeanUtils.copyProperties(account, accountEntity);
        accountEntity = accountDao.save(accountEntity);
        return accountConverter.convert(accountEntity);
    }

    public void delete(Long id)
    {
        AccountEntity accountEntity = accountDao.getById(id);
        accountDao.delete(accountEntity);
    }
}
