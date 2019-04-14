package cn.pany.walle.democlient.controller;

import cn.pany.walle.demo.server.service.Demo1Service;
import cn.pany.walle.demo.server.service.HelloService;
import cn.pany.walle.remoting.api.WalleApp;
import cn.pany.walle.remoting.registry.WalleRegistry;
import cn.pany.walle.remoting.server.WalleServer;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by pany on 18/9/23.
 */

@RestController
public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(WalleServer.class);

    @Resource
    HelloService helloService;

    @RequestMapping("hello")
    public String hello() {
        try {
//            return JSON.toJSONString(clientRegistry.getRegistryAddress());
//            return JSON.toJSONString(demoServer.getAppName());
            String result = JSON.toJSONString(helloService.hello("test"));
            int threadNum = 1;
            long allCostBegin = System.currentTimeMillis();
            CountDownLatch countDownLatch1 = new CountDownLatch(threadNum);

            for (int i = 0; i < threadNum; i++) {
                int finalI = i;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long begin = System.currentTimeMillis();
                        for (int j = 0; j < 1; j++) {
                            String resultThread = helloService.hello("thread" + finalI);
                            if (resultThread.equals("null")) {
                                log.error(j + " result is:" + resultThread);
                            }
//                            else {
//                                log.info(j + " result is:" + resultThread);
//                            }
                        }
                        long cost = System.currentTimeMillis() - begin;
                        log.info("thread" + finalI + " cost time is:" + cost);
//                        allCost = allCost+cost;
                        countDownLatch1.countDown();
                    }
                }).start();

            }
            countDownLatch1.await(100, TimeUnit.SECONDS);
            log.info("result is :" + result);
            log.info("threadNum[" + threadNum + "] allCost is :" + (System.currentTimeMillis() - allCostBegin));

//
            return result;
        } catch (Exception e) {
            log.error("", e);
            return "error!";
        }
    }

    @Resource
    Demo1Service demo1Service;

    @RequestMapping("demo")
    public String demo() {
        String result = JSON.toJSONString(demo1Service.demoOne1("demo"));
        log.info("demo result is :" + result);

        return result;
    }
}
