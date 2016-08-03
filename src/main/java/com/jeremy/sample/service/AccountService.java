package com.jeremy.sample.service;

import com.jeremy.sample.domain.messaging.Account;

import java.util.List;

/**
 * Created by Jeremy Yang on 27/07/2016.
 */
public interface AccountService
{

    List<Account> getAll();

    Account getById(Long id);

    Account getByName(String name);

    Account create(Account account);

    Account update(Account account);

    void delete(Long id);

}
