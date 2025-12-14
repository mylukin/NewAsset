package com.ruoyi.asset.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Asset Management Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/asset")
public class AssetController {

    @GetMapping("/list")
    public String list() {
        return "Asset list endpoint";
    }
}
