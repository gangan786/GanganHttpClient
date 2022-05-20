package top.ganganmaster.ganganhttpclientdemo.service;

import top.ganganmaster.ganganhttpclient.annotation.HttpService;
import top.ganganmaster.ganganhttpclient.annotation.HttpServiceEndpoint;
import top.ganganmaster.ganganhttpclient.enums.HttpParamsMethod;

@HttpService(host = "baidu.com", port = "80")
public interface BaiduService {

    @HttpServiceEndpoint(httpParamsMethod = HttpParamsMethod.GET,
    connectTimeOutMiles = 100,
    readTimeoutMiles = 1000,
    value = "/test")
    void helloBaiduProxy();

}
