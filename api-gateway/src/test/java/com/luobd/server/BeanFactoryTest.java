package com.luobd.server;

import junit.framework.TestCase;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;

import java.util.Map;

public class BeanFactoryTest extends TestCase {





    public void test(){
        // 创建bean工厂
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 构建一个BeanDefinition
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
                .genericBeanDefinition(ConfigurationBean.class)
                // 设置Bean的作用域为单例
                .setScope("singleton");

        // 注册一个BeanDefinition
        beanFactory.registerBeanDefinition("configurationBean",beanDefinitionBuilder.getBeanDefinition());
        // 为该配置Bean注册一些注解后置处理器，可以让BeanFactory可以扫描Bean的注解信息
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);
        // 获取BeanFactory的后置处理器
        Map<String, BeanFactoryPostProcessor> beans = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        beans.forEach((k,v)-> {
            System.out.println("调用BeanFactory的后置处理器: " + k);
            v.postProcessBeanFactory(beanFactory);
        });
        // 获取bean
        Object beanOne = beanFactory.getBean("beanOne");
        System.out.println(beanOne);
    }


}
