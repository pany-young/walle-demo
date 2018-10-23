package cn.pany.walle.demo.server.service;

import cn.pany.walle.common.annotation.WalleRpcService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by pany on 16/8/25.
 */

@WalleRpcService(appName = "demo-server",value = HelloService.class) // 指定远程接口
public class HelloServiceImpl implements HelloService {
    private final static Logger LOG = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public String hello(String name) {
        LOG.info("client call hello!");
        return "Hello! " + name;
    }
}