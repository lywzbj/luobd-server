package com.luobd.server;

import org.springframework.beans.factory.annotation.Autowired;

public class BeanTwo {


    @Autowired
    private BeanOne beanOne;




    public BeanTwo() {
        System.out.println("BeanTwo init");
    }

    public void sayHello() {
        System.out.println("Hello BeanTwo");
    }

}
