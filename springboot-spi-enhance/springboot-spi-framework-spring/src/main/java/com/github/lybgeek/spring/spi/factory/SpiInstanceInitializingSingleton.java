package com.github.lybgeek.spring.spi.factory;


import com.github.lybgeek.spi.anotatation.Activate;
import com.github.lybgeek.spring.interceptor.handler.InterceptorHandler;
import com.github.lybgeek.spring.spi.util.SpiBeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.annotation.AnnotationUtils;

public class SpiInstanceInitializingSingleton implements SmartInitializingSingleton,BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    private InterceptorHandler interceptorHandler;

    public SpiInstanceInitializingSingleton(InterceptorHandler interceptorHandler) {
        this.interceptorHandler = interceptorHandler;
    }

    @Override
    public void afterSingletonsInstantiated() {
        changeBeanInstance2ProxyInstance();

    }

    private void changeBeanInstance2ProxyInstance(){
        beanFactory.getBeansWithAnnotation(Activate.class).forEach((beanName,bean)->{
//            beanFactory.setAllowBeanDefinitionOverriding(true);
            beanFactory.removeBeanDefinition(beanName);
            beanFactory.registerSingleton(beanName,interceptorHandler.getInterceptorChain().pluginAll(bean));
        });


    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory)beanFactory;
    }
}
