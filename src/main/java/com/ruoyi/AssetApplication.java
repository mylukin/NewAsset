package com.ruoyi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Asset Management System Application
 *
 * Main entry point for the Spring Boot application.
 * Initializes the complete asset management system including:
 * - Base asset management
 * - House, Parking, Facility, Venue, Office asset modules
 * - Maintenance workflow management
 * - Dashboard and reporting
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@SpringBootApplication
@MapperScan("com.ruoyi.**.mapper")
public class AssetApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssetApplication.class, args);
        System.out.println();
        System.out.println("  ____            _        ____            _       _             ");
        System.out.println(" |  _ \\ ___  _ __ | |_ __ _|  _ \\ _ __ __ _| |_ ___ | |__   ___ _ __");
        System.out.println(" | |_) / _ \\| '_ \\| __/ _` | | | | '__/ _` | __/ _ \\| '_ \\ / _ \\ '__|");
        System.out.println(" |  __/ (_) | | | | || (_| | |_| | | | (_| | || (_) || | | |  __/ |   ");
        System.out.println(" |_|   \\___/|_| |_|\\__\\__,_|____/|_|  \\__,_|\\__\\___(_)_| |_|\\___|_|   ");
        System.out.println();
        System.out.println("Asset Management System started successfully!");
        System.out.println("Frontend: http://localhost:8081");
        System.out.println("Backend API: http://localhost:8080");
        System.out.println("API Documentation: http://localhost:8080/doc.html");
        System.out.println();
    }
}
