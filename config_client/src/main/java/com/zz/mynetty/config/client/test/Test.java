package com.zz.mynetty.config.client.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhangzuizui
 * @date 2018/8/24 10:41
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {

        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-config.xml");

        synchronized(Test.class){
            while (true){
                Test.class.wait();
            }

        }
    }
}
