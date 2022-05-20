package top.ganganmaster.ganganhttpclient.annotation;

import top.ganganmaster.ganganhttpclient.enums.HttpParamsMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HttpServiceEndpoint {

    HttpParamsMethod httpParamsMethod();

    int connectTimeOutMiles();

    int readTimeoutMiles();

    String value();

}
