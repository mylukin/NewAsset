package com.ruoyi.asset;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Asset Management Application Entry Point
 *
 * @author ruoyi
 */
@SpringBootApplication
@ComponentScan(basePackages = {
    "com.ruoyi.asset",
    "com.ruoyi.common.core",
    "com.ruoyi.common.security",
    "com.ruoyi.common.log",
    "com.ruoyi.common.datascope",
    "com.ruoyi.common.swagger"
})
public class RuoyiAssetApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuoyiAssetApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  Asset Management System Started Successfully   ლ(´ڡ`ლ)ﾞ");
    }
}
