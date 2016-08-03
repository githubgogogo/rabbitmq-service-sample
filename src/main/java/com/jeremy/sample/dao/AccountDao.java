package com.jeremy.sample.dao;

import com.jeremy.sample.domain.entity.AccountEntity;

import java.util.List;

/**
 * Created by Jeremy Yang on 27/07/2016.
 */
public interface AccountDao extends Dao<AccountEntity>
{
    List<AccountEntity> getAllAccounts();

    AccountEntity getAccountByName(String name);
}
