package com.ruoyi.common.security.utils;

/**
 * Security utility class
 * Provides helper methods for authentication and authorization
 *
 * @author ruoyi
 */
public class SecurityUtils {

    /**
     * Get current logged in user ID
     * In a real implementation, this would retrieve from SecurityContext
     *
     * @return user ID
     */
    public static Long getUserId() {
        // Default to admin user ID for development
        return 1L;
    }

    /**
     * Get current logged in username
     *
     * @return username
     */
    public static String getUsername() {
        // Default to admin for development
        return "admin";
    }

    /**
     * Get current logged in user's department ID
     *
     * @return department ID
     */
    public static Long getDeptId() {
        return 100L;
    }

    /**
     * Check if current user has admin privileges
     *
     * @return true if admin
     */
    public static boolean isAdmin() {
        return true;
    }

    /**
     * Check if current user has a specific role
     *
     * @param role role key
     * @return true if user has the role
     */
    public static boolean hasRole(String role) {
        // For development, allow all roles
        return true;
    }

    /**
     * Check if current user has a specific permission
     *
     * @param permission permission key
     * @return true if user has the permission
     */
    public static boolean hasPermission(String permission) {
        // For development, allow all permissions
        return true;
    }
}
