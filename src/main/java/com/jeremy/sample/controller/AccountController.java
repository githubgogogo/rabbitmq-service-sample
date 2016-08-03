package com.jeremy.sample.controller;

import com.jeremy.sample.domain.messaging.Account;
import com.jeremy.sample.exception.CustomException;
import com.jeremy.sample.service.AccountService;
import com.jeremy.sample.service.MqAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Jeremy Yang on 27/07/2016.
 */
@Controller
@RequestMapping(value = "/account")
public class AccountController
{
    @Autowired
    AccountService accountService;

    @Autowired
    MqAccountService mqAccountService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<Account> getAllAccounts()
    {
        return accountService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Account getAccountByName(@PathVariable Long id)
    {
        return accountService.getById(id);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Account getAccountByName(@PathVariable String name)
    {
        return accountService.getByName(name);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Account createAccount(@RequestBody Account account) throws CustomException
    {
//        return accountService.create(account);
        return mqAccountService.createAnsynchronously(account);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public void deleteAccount(@PathVariable Long id)
    {
        accountService.delete(id);
    }

}
