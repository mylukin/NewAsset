package com.ruoyi.asset.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Asset module configuration
 * Configures MyBatis mapper scanning for the asset module
 */
@Configuration
@MapperScan("com.ruoyi.asset.mapper")
public class AssetConfig {
    // Asset module configuration will be added here
}
