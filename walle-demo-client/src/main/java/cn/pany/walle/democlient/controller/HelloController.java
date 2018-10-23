package cn.pany.walle.democlient.controller;

import cn.pany.walle.demo.server.service.HelloService;
import cn.pany.walle.remoting.api.WalleApp;
import cn.pany.walle.remoting.registry.WalleRegistry;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by pany on 18/9/23.
 */
@Slf4j
@RestController
public class HelloController {

    @Resource
    WalleRegistry clientRegistry;

    @Resource
    WalleApp demoServer;

    @Resource
    HelloService helloService;

    @RequestMapping("hello")
    public String hello() {
        try {
//            return JSON.toJSONString(clientRegistry.getRegistryAddress());
//            return JSON.toJSONString(demoServer.getAppName());
            return JSON.toJSONString(helloService.hello("test"));
        } catch (Exception e) {
            log.error("", e);
            return "error!";
        }
    }


}
