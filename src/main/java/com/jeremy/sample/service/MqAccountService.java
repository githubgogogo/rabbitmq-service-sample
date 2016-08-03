package com.jeremy.sample.service;

import com.jeremy.sample.domain.messaging.Account;
import com.jeremy.sample.exception.CustomException;

/**
 * Created by Jeremy Yang on 2/08/2016.
 */
public interface MqAccountService
{
    Account createAnsynchronously(Account account) throws CustomException;

}
