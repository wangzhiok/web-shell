package com.github.zmzhoustar.webshell;

import com.github.zmzhoustar.webshell.utils.ThreadPoolUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @description: war启动类
 * @author: wangzhi
 * @create: 2021-12-08 21:23
 **/
@SpringBootApplication
@EnableCaching
public class WarStarterApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // log4j2全局异步日志配置 http://logging.apache.org/log4j/2.x/manual/async.html#AllAsync
        System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        //  指向Application这个springboot启动类
        // 停止应用时，关闭线程池钩子，或者使用 @PreDestroy 注解执行一系列操作
        Runtime.getRuntime().addShutdownHook(new Thread(ThreadPoolUtils::shutdown, "ShutdownThreadPoolHook"));
        return builder.sources(WarStarterApplication.class);
    }

}
