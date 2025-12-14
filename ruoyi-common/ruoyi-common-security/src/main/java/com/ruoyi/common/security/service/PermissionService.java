package com.ruoyi.common.security.service;

import org.springframework.stereotype.Service;

/**
 * Permission service - development mode allows all access
 * Bean name is "ss" to match @PreAuthorize("@ss.hasPermi('...')") expressions
 */
@Service("ss")
public class PermissionService {

    /**
     * Check if current user has permission - always returns true for development
     */
    public boolean hasPermi(String permission) {
        return true;
    }

    /**
     * Check if current user has any of the permissions
     */
    public boolean hasAnyPermi(String permissions) {
        return true;
    }

    /**
     * Check if current user has role
     */
    public boolean hasRole(String role) {
        return true;
    }

    /**
     * Check if current user has any of the roles
     */
    public boolean hasAnyRoles(String roles) {
        return true;
    }

    /**
     * Check if current user lacks permission
     */
    public boolean lacksPermi(String permission) {
        return false;
    }

    /**
     * Check if current user lacks role
     */
    public boolean lacksRole(String role) {
        return false;
    }
}
