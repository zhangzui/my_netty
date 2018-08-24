package com.zz.mynetty.config.client.process;

import com.jd.overseas.configcenter.client.service.ClientConfig;
import org.springframework.util.MethodInvoker;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author zhangzuizui
 * @date 2018/8/24 10:51
 */
public class MyListenerMethodInvoke extends MethodInvoker {

    String listenerClassName;
    String name;
    ClientConfig clientConfig;

    public void myListenerHandle(ConcurrentHashMap<String, List<MyConfigListener>> myConfigListenerMap){
        MyConfigListener myConfigListener = new MyConfigListener() {
            @Override
            public void onConfigEvent(Map map) {
                try {
                    setArguments(new Object[] { map });
                    if(!isPrepared()){
                        prepare();
                    }
                    invoke();
                } catch (Exception e) {
                    System.out.println("error111111111111");
                }
            }
        };

        List<MyConfigListener> list = myConfigListenerMap.get(name);
        // oneKeyListenerMap不存在对应key
        if (list == null) {
            // OneKeyListener添加到CopyOnWriteArrayList
            List<MyConfigListener> newList = new CopyOnWriteArrayList<MyConfigListener>();
            newList.add(myConfigListener);
            // 添加到oneKeyListenerMap
            myConfigListenerMap.put(name, newList);
        }
    }

    public String getListenerClassName() {
        return listenerClassName;
    }

    public void setListenerClassName(String listenerClassName) {
        this.listenerClassName = listenerClassName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClientConfig getClientConfig() {
        return clientConfig;
    }

    public void setClientConfig(ClientConfig clientConfig) {
        this.clientConfig = clientConfig;
    }
}
