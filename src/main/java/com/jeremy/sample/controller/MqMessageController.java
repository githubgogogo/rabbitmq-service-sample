package com.jeremy.sample.controller;

import com.jeremy.sample.domain.Status;
import com.jeremy.sample.domain.messaging.Account;
import com.jeremy.sample.domain.messaging.RabbitMqMessage;
import com.jeremy.sample.service.AccountService;
import com.jeremy.sample.service.MqAccountService;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * Created by Jeremy Yang on 2/08/2016.
 */
@Controller("MqMessageController")
@RequestMapping("/mq")
public class MqMessageController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MqMessageController.class);

    @Autowired
    private AccountService accountService;

    public void receiveRabbitMqMessage(@Payload RabbitMqMessage rabbitMqMessage)
    {
        LOGGER.debug("Landing receiveRabbitMqMessage.");
        ObjectMapper mapper = new ObjectMapper();
        String action = rabbitMqMessage.getAction();
        String type = rabbitMqMessage.getType();
        LOGGER.debug("action=" + action + ";type=" + type);
        if (StringUtils.equalsIgnoreCase(type, "account"))
        {
            try
            {
                Account account = mapper.readValue(rabbitMqMessage.getDetails(), Account.class);
                account.setStatus(Status.ACTIVE);
                accountService.update(account);
                LOGGER.debug("receiveRabbitMqMessage process completed.");
            }
            catch (JsonParseException e)
            {
                LOGGER.error("In recieveRabbitMqMessage():  Error while converting  json string to  Account " + "object ", e);
            }
            catch (JsonMappingException e)
            {
                LOGGER.error("In recieveRabbitMqMessage():  Error while converting  json string to  Account " + "object ", e);
            }
            catch (IOException e)
            {
                LOGGER.error("In recieveRabbitMqMessage():  Error while converting  json string to  Account " + "object ", e);
            }
        }
    }
}
