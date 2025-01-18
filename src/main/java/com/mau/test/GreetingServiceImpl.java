package com.mau.test;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GreetingService
{
    @Override
    public String getGreeting(String name)
    {
        return "Hello, " + name + ", from Spring a service implementation!";
    }
}
