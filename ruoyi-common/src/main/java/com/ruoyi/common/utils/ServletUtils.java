package com.ruoyi.common.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet utility class
 */
public class ServletUtils {

    /**
     * Get String parameter
     */
    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    /**
     * Get String parameter with default value
     */
    public static String getParameter(String name, String defaultValue) {
        String value = getRequest().getParameter(name);
        return StringUtils.isEmpty(value) ? defaultValue : value;
    }

    /**
     * Get Integer parameter
     */
    public static Integer getParameterToInt(String name) {
        String value = getParameter(name);
        return StringUtils.isEmpty(value) ? null : Integer.valueOf(value);
    }

    /**
     * Get Integer parameter with default value
     */
    public static Integer getParameterToInt(String name, Integer defaultValue) {
        String value = getParameter(name);
        return StringUtils.isEmpty(value) ? defaultValue : Integer.valueOf(value);
    }

    /**
     * Get Boolean parameter
     */
    public static Boolean getParameterToBool(String name) {
        String value = getParameter(name);
        return StringUtils.isEmpty(value) ? null : Boolean.valueOf(value);
    }

    /**
     * Get Boolean parameter with default value
     */
    public static Boolean getParameterToBool(String name, Boolean defaultValue) {
        String value = getParameter(name);
        return StringUtils.isEmpty(value) ? defaultValue : Boolean.valueOf(value);
    }

    /**
     * Get request
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * Get response
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    /**
     * Get session
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * Render string to client
     */
    public static void renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check if request is ajax request
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String accept = request.getHeader("accept");
        if (accept != null && accept.contains("application/json")) {
            return true;
        }
        String xRequestedWith = request.getHeader("X-Requested-With");
        if (xRequestedWith != null && xRequestedWith.contains("XMLHttpRequest")) {
            return true;
        }
        String uri = request.getRequestURI();
        if (StringUtils.containsAny(uri, ".json", ".xml")) {
            return true;
        }
        String ajax = request.getParameter("__ajax");
        return StringUtils.containsAny(ajax, "json", "xml");
    }
}
