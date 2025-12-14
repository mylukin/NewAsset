package com.ruoyi.asset.config;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Database dialect configuration for handling SQL dialect differences
 * between SQLite (development) and MySQL (production)
 */
@Configuration
public class DatabaseDialectConfig {

    /**
     * Configure database ID provider for MyBatis
     * This allows using different SQL in mapper XML based on database type
     *
     * Usage in mapper XML:
     * <select id="selectById" databaseId="sqlite">
     *     SELECT * FROM table WHERE id = #{id}
     * </select>
     * <select id="selectById" databaseId="mysql">
     *     SELECT * FROM table WHERE id = #{id}
     * </select>
     */
    @Bean
    public DatabaseIdProvider databaseIdProvider() {
        VendorDatabaseIdProvider provider = new VendorDatabaseIdProvider();
        Properties properties = new Properties();
        properties.setProperty("SQLite", "sqlite");
        properties.setProperty("MySQL", "mysql");
        properties.setProperty("MariaDB", "mysql");
        provider.setProperties(properties);
        return provider;
    }
}
