package com.jeremy.sample.exception;

/**
 * Created by Jeremy Yang on 2/08/2016.
 */
public class CustomException extends Exception
{

    public CustomException(String message)
    {
        super(message);
    }

    public CustomException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
