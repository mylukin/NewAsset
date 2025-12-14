package com.ruoyi.asset;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Asset Management Application Entry Point
 *
 * @author ruoyi
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {
    "com.ruoyi.asset",
    "com.ruoyi.common.core",
    "com.ruoyi.common.log",
    "com.ruoyi.common.datascope"
})
public class RuoyiAssetApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuoyiAssetApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  Asset Management System Started Successfully   ლ(´ڡ`ლ)ﾞ");
    }
}
