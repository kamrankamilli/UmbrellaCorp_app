package com.example.umbrellacorp;

import java.io.Serializable;

public class SupportObj implements Serializable
{
    public String name,email,phoneNumber,message;

    public SupportObj()
    {

    }
    public SupportObj(String name, String email,String phoneNumber, String message)
    {
        this.name= name;
        this.email = email;
        this.phoneNumber= phoneNumber;
        this.message= message;
    }

}
