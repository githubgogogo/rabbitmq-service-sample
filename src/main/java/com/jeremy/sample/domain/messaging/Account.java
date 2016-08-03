package com.jeremy.sample.domain.messaging;

import com.jeremy.sample.domain.Status;

import java.io.Serializable;

/**
 * Created by Jeremy Yang on 27/07/2016.
 */
public class Account implements Serializable
{
    private static final long serialVersionUID = -749414824680563514L;

    private Long id;

    private String name;

    private String email;

    private String firstName;

    private String lastName;

    private Status status;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }
}
