package com.zz.mynetty.config.client.bean;

import com.alibaba.fastjson.JSON;
import com.zz.mynetty.config.client.annotation.MyListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author zhangzuizui
 * @date 2018/8/24 10:44
 */
@Component
public class ConfigClientTest {

    @MyListener(name = "zhangzuizui")
    public void myListener(HashMap map){

        System.out.println("----------"+ JSON.toJSONString(map));
    }
}
