package top.ganganmaster.ganganhttpclientdemo.service;

import top.ganganmaster.ganganhttpclient.annotation.HttpService;

@HttpService(host = "google.com", port = "80")
public interface GoogleService {
}
