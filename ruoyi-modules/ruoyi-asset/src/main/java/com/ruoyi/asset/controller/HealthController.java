package com.ruoyi.asset.controller;

import com.ruoyi.common.core.web.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Health check controller - simple endpoint for testing
 */
@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping
    public AjaxResult health() {
        Map<String, Object> data = new HashMap<>();
        data.put("status", "UP");
        data.put("timestamp", System.currentTimeMillis());
        data.put("service", "ruoyi-asset");
        return AjaxResult.success("Service is running", data);
    }

    @GetMapping("/info")
    public AjaxResult info() {
        Map<String, Object> data = new HashMap<>();
        data.put("name", "Asset Management API");
        data.put("version", "1.0.0");
        data.put("description", "Asset Management System Backend API");
        return AjaxResult.success(data);
    }
}
