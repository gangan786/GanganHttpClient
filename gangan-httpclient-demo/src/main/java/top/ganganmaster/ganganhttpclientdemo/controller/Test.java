package top.ganganmaster.ganganhttpclientdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.ganganmaster.ganganhttpclientdemo.service.BaiduService;
import top.ganganmaster.ganganhttpclientdemo.service.GoogleService;

import javax.annotation.Resource;

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
