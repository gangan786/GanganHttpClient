~~~
  _____                               _    _ _   _          _____ _ _            _   
 / ____|                             | |  | | | | |        / ____| (_)          | |  
| |  __  __ _ _ __   __ _  __ _ _ __ | |__| | |_| |_ _ __ | |    | |_  ___ _ __ | |_ 
| | |_ |/ _` | '_ \ / _` |/ _` | '_ \|  __  | __| __| '_ \| |    | | |/ _ \ '_ \| __|
| |__| | (_| | | | | (_| | (_| | | | | |  | | |_| |_| |_) | |____| | |  __/ | | | |_ 
 \_____|\__,_|_| |_|\__, |\__,_|_| |_|_|  |_|\__|\__| .__/ \_____|_|_|\___|_| |_|\__|
                     __/ |                          | |                              
                    |___/                           |_|                              
~~~

# 介绍
这是一个httpClient封装工具，类似工具：[Forest](https://github.com/dromara/forest)，通过这个项目可以学习如果通过Spring框架的能力快速集成中间件，如果你苦于阅读Spring源码后没有实战练手项目，那么这个项目会给你一定的启发

# 快速开始

1. 在应用内引入start模块
```xml
<dependency>
    <groupId>top.ganganmaster</groupId>
    <artifactId>gangan-httpclient-start</artifactId>
    <version>${ganganClient.version}</version>
</dependency>
```
2. 在application.yml添加扫描路径（该路径下定义着http调用接口）
```yml
gangan:
  httpclient:
    scan: top.ganganmaster.ganganhttpclientdemo.service
```
3. 定义http调用接口
```java
@HttpService(host = "baidu.com", port = "80")
public interface BaiduService {

    @HttpServiceEndpoint(httpParamsMethod = HttpParamsMethod.GET,
    connectTimeOutMiles = 100,
    readTimeoutMiles = 1000,
    value = "/test")
    void helloBaiduProxy();

}
```
4. 开启调用
通过我们平常使用的Spring属性注入注解即可开启调用
```java
@RestController
public class Test {

    @Resource
    private GoogleService googleService;

    @Resource
    private BaiduService baiduService;

    @RequestMapping(value = {"/testBaidu"}, method = RequestMethod.GET)
    @ResponseBody
    public String testBaiduService() {
        baiduService.helloBaiduProxy();
        return "hello world";
    }
}
```
