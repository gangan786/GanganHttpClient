package top.ganganmaster.ganganhttpclient.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.StringUtils;
import top.ganganmaster.ganganhttpclient.annotation.HttpService;
import top.ganganmaster.ganganhttpclient.proxy.HttpClientProxy;

import java.math.BigDecimal;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@Configurable
@Slf4j
public class GanganHttpClientRegisterAutoConfiguration implements BeanFactoryPostProcessor, EnvironmentPostProcessor {

    private static ConfigurableEnvironment environment;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("postProcessBeanFactory 执行");
        String scan = environment.getProperty("gangan.httpclient.scan");
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableListableBeanFactory;
        if (StringUtils.hasText(scan)) {
            String[] urlList = scan.replaceAll(" ", ",").split(",");
            HashSet<URL> urlSet = new HashSet<>();
            for (String url : urlList) {
                urlSet.addAll(ClasspathHelper.forPackage(url));
            }

            Reflections reflections = new Reflections(new ConfigurationBuilder().setUrls(urlSet).setScanners(new TypeAnnotationsScanner()));
            Set<Class<?>> httpServices = reflections.getTypesAnnotatedWith(HttpService.class, true);

            for (Class<?> httpService : httpServices) {
                BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(HttpClientProxy.class);
                definitionBuilder.addPropertyValue("service", httpService.getName());
                defaultListableBeanFactory.registerBeanDefinition(httpService.getName()+"proxy", definitionBuilder.getBeanDefinition());

            }
        }
    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        System.out.println("postProcessEnvironment 执行");
        GanganHttpClientRegisterAutoConfiguration.environment = environment;
    }
}
