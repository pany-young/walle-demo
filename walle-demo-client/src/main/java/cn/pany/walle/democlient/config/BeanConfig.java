package cn.pany.walle.democlient.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by pany on 18/9/23.
 */
@Configuration
@ImportResource(locations={"classpath:application-bean.xml"})
public class BeanConfig {
}
