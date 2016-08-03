package com.jeremy.sample.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Jeremy Yang on 25/07/2016.
 */
@Controller
@RequestMapping(value = "/")
public class HelloWorld
{

    @Value("${artifact.version}")
    protected String version;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String helloWorld() {
        return "{app-name: rabbitmq-service-sample version " + version + ".}";
    }
}
