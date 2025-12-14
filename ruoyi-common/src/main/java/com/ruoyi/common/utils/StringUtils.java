package com.ruoyi.common.utils;

import java.util.Collection;

/**
 * String utility class
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /** Empty string */
    private static final String NULLSTR = "";

    /** Underscore */
    private static final char SEPARATOR = '_';

    /**
     * Get non-null string
     */
    public static String nvl(String value, String defaultValue) {
        return value != null ? value : defaultValue;
    }

    /**
     * Check if a collection is empty
     */
    public static boolean isEmpty(Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }

    /**
     * Check if a collection is not empty
     */
    public static boolean isNotEmpty(Collection<?> coll) {
        return !isEmpty(coll);
    }

    /**
     * Check if an object array is empty
     */
    public static boolean isEmpty(Object[] objects) {
        return objects == null || objects.length == 0;
    }

    /**
     * Check if an object array is not empty
     */
    public static boolean isNotEmpty(Object[] objects) {
        return !isEmpty(objects);
    }

    /**
     * Check if a string is empty
     */
    public static boolean isEmpty(String str) {
        return str == null || NULLSTR.equals(str.trim());
    }

    /**
     * Check if a string is not empty
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * Trim string
     */
    public static String trim(String str) {
        return str == null ? "" : str.trim();
    }

    /**
     * Convert camelCase to underscore_case
     */
    public static String toUnderScoreCase(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            boolean nextUpperCase = true;
            if (i < str.length() - 1) {
                nextUpperCase = Character.isUpperCase(str.charAt(i + 1));
            }
            if (i > 0 && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }

    /**
     * Check if string is http or https url
     */
    public static boolean ishttp(String link) {
        return startsWithAny(link, "http://", "https://");
    }

    /**
     * Check string starts with any of the given strings
     */
    public static boolean startsWithAny(String str, String... prefixes) {
        if (isEmpty(str) || isEmpty(prefixes)) {
            return false;
        }
        for (String prefix : prefixes) {
            if (str.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Format string
     */
    public static String format(String template, Object... params) {
        if (isEmpty(template) || isEmpty(params)) {
            return template;
        }
        for (int i = 0; i < params.length; i++) {
            template = template.replace("{" + i + "}", params[i].toString());
        }
        return template;
    }
}
