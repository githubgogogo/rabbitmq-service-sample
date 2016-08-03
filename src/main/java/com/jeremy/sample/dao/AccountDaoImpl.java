package com.jeremy.sample.dao;

import com.jeremy.sample.domain.entity.AccountEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Jeremy Yang on 27/07/2016.
 */
@Component("accountDao")
public class AccountDaoImpl extends AbstractHibernateDao<AccountEntity> implements AccountDao
{

    public List<AccountEntity> getAllAccounts()
    {
        Criteria criteria = createCriteria();
        return criteria.list();
    }

    public AccountEntity getAccountByName(String name)
    {
        Criteria criteria = createCriteria();
        criteria.add(Restrictions.eq("name", name));
        return (AccountEntity) criteria.uniqueResult();
    }

    @Override
    protected Criteria createCriteria()
    {
        return getSession().createCriteria(AccountEntity.class);
    }

}
