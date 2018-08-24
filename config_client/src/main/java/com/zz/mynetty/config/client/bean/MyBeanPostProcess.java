package com.zz.mynetty.config.client.bean;

import com.jd.overseas.configcenter.client.event.ConfigEvent;
import com.jd.overseas.configcenter.client.event.EventType;
import com.jd.overseas.configcenter.client.event.OneKeyListener;
import com.zz.mynetty.config.client.annotation.MyListener;
import com.zz.mynetty.config.client.process.MyConfigListener;
import com.zz.mynetty.config.client.process.MyListenerMethodInvoke;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangzuizui
 * @date 2018/8/24 10:48
 */
@Service
public class MyBeanPostProcess implements BeanPostProcessor, ApplicationListener<ContextRefreshedEvent>,InitializingBean {

    ConcurrentHashMap<String, List<MyConfigListener>> myConfigListenerMap = new ConcurrentHashMap<String, List<MyConfigListener>>();

    private static final ExecutorService executorService = Executors.newCachedThreadPool();
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("postProcessBeforeInitialization");
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object object, String s) throws BeansException {
        System.out.println("postProcessAfterInitialization-----------");
        //获取监听注解，添加监听模式
        Method[] methods = ReflectionUtils.getAllDeclaredMethods(object.getClass());
        if (methods != null) {
            //遍历bean所有方法
            for (Method method : methods) {
                //查找OneKeyListener注解
                MyListener myListener = AnnotationUtils.findAnnotation(method, MyListener.class);
                if (myListener != null) {
                    //Listener事件回调处理包装类
                    MyListenerMethodInvoke myListenerMethodInvoke = new MyListenerMethodInvoke();
                    //OneKeyListener类名
                    myListenerMethodInvoke.setListenerClassName(com.jd.overseas.configcenter.client.event.OneKeyListener.class.getName());

                    if (StringUtils.isEmpty(myListener.name())) {
                        //如果Annotation没有指定projectName，默认为ConfigFactoryBean配置的projectName
                        myListenerMethodInvoke.setName("xxxx");
                    } else {
                        myListenerMethodInvoke.setName(myListener.name());
                    }
                    //事件处理bean
                    myListenerMethodInvoke.setTargetObject(object);
                    //事件处理方法
                    myListenerMethodInvoke.setTargetMethod(method.getName());
                    myListenerMethodInvoke.myListenerHandle(myConfigListenerMap);
                }

            }
        }
        return object;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
            System.out.println("onApplicationEvent");

            while (true){
                List<MyConfigListener> list = myConfigListenerMap.get("zhangzuizui");
                final HashMap map = new HashMap();
                map.put("params","111");
                if (list != null) {
                    for (final MyConfigListener listener : list) {
                        executorService.submit(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    listener.onConfigEvent(map);
                                } catch (Throwable t) {
                                    System.out.println("Listener error!");
                                }
                            }
                        });
                    }
                }

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
