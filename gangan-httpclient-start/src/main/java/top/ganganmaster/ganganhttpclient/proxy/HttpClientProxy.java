package top.ganganmaster.ganganhttpclient.proxy;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Slf4j
public class HttpClientProxy implements MethodInterceptor, InitializingBean, FactoryBean<Object>, ApplicationContextAware, BeanFactoryAware {

    private Class<Object> service;
    private Object proxy;

    @Nullable
    @Override
    public Object invoke(@Nonnull MethodInvocation invocation) throws Throwable {
        log.info("代理方法执行：" + invocation.getMethod().getName());
        return null;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }

    @Override
    public Object getObject() throws Exception {
        return proxy;
    }

    @Override
    public Class<?> getObjectType() {
        return service;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        proxy = ProxyFactory.getProxy(service,this);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    public Class<Object> getService() {
        return service;
    }

    public void setService(Class<Object> service) {
        this.service = service;
    }

    public Object getProxy() {
        return proxy;
    }

    public void setProxy(Object proxy) {
        this.proxy = proxy;
    }
}
