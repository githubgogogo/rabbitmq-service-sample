package com.jeremy.sample.domain.messaging;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jeremy Yang on 2/08/2016.
 */
public class RabbitMqMessage implements Serializable
{
    private static final long serialVersionUID = -7744460928783146796L;

    private String action;
    private String type;
    private String details;
    private String trackingIdentifier;
    private Date timeStamp;
    private String routingKey;

    public String getAction()
    {
        return action;
    }

    public void setAction(String action)
    {
        this.action = action;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getDetails()
    {
        return details;
    }

    public void setDetails(String details)
    {
        this.details = details;
    }

    public String getTrackingIdentifier()
    {
        return trackingIdentifier;
    }

    public void setTrackingIdentifier(String trackingIdentifier)
    {
        this.trackingIdentifier = trackingIdentifier;
    }

    public Date getTimeStamp()
    {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp)
    {
        this.timeStamp = timeStamp;
    }

    public String getRoutingKey()
    {
        return routingKey;
    }

    public void setRoutingKey(String routingKey)
    {
        this.routingKey = routingKey;
    }
}
