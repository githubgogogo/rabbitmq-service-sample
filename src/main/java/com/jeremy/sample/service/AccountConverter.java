package com.jeremy.sample.service;

import com.jeremy.sample.domain.entity.AccountEntity;
import com.jeremy.sample.domain.messaging.Account;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

/**
 * Created by Jeremy Yang on 27/07/2016.
 */
@Service
public class AccountConverter implements Converter<AccountEntity, Account>
{
    public Account convert(AccountEntity accountEntity)
    {
        if (accountEntity == null)
        {
            return null;
        }
        Account account = new Account();
        BeanUtils.copyProperties(accountEntity, account);
        return account;
    }

    public AccountEntity convert(Account account)
    {
        if (account == null)
        {
            return null;
        }
        AccountEntity accountEntity = new AccountEntity();
        BeanUtils.copyProperties(account, accountEntity);
        return accountEntity;
    }
}
