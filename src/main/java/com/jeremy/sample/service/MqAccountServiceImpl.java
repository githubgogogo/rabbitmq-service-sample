package com.jeremy.sample.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeremy.sample.dao.AccountDao;
import com.jeremy.sample.domain.Status;
import com.jeremy.sample.domain.entity.AccountEntity;
import com.jeremy.sample.domain.messaging.Account;
import com.jeremy.sample.domain.messaging.RabbitMqMessage;
import com.jeremy.sample.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Jeremy Yang on 2/08/2016.
 */
@Service("mqAccountService")
public class MqAccountServiceImpl implements MqAccountService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MqAccountServiceImpl.class);

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private AccountConverter accountConverter;

    @Autowired
    private MessageChannel publishTaskMessage;

    public Account createAnsynchronously(Account account) throws CustomException
    {
        AccountEntity accountEntity = accountConverter.convert(account);
        accountEntity.setStatus(Status.IN_PROGRESS);
        accountEntity = accountDao.save(accountEntity);
        Account savedAccount = accountConverter.convert(accountEntity);

        RabbitMqMessage rabbitMqMessage = createRabbitMQMessage(savedAccount);
        rabbitMqMessage.setRoutingKey("RabbitmqService.Account.Create");
        MessagingTemplate template = new MessagingTemplate();
        Message<RabbitMqMessage> requestMessage = MessageBuilder.withPayload(rabbitMqMessage).build();
        LOGGER.debug("Publishing RabbitMqMessage in submitAutomatedTask###");
        template.send(publishTaskMessage, requestMessage);
        LOGGER.debug("Published RabbitMqMessage in submitAutomatedTask###");

        return savedAccount;
    }


    private RabbitMqMessage createRabbitMQMessage(Account account) throws CustomException
    {
        RabbitMqMessage rabbitMqMessage = new RabbitMqMessage();
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            // TODO : Need to get clarified on what values we should fill in rabbitMqMessage
            rabbitMqMessage.setAction("Create account");
            rabbitMqMessage.setType("Account");
            rabbitMqMessage.setTimeStamp(new Date());
            rabbitMqMessage.setTrackingIdentifier("account-" + account.getId());
            rabbitMqMessage.setDetails(mapper.writeValueAsString(account));

        }
        catch (IOException ioe)
        {
            String errorMessage = "create rabbit mq message for account failed. err=" + ioe.getMessage();
            LOGGER.error(errorMessage);
            throw new CustomException(errorMessage, ioe);
        }
        return rabbitMqMessage;
    }
}
