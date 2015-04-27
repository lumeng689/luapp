package org.luapp.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by lum on 2015/4/27.
 */
public class CookieUtils {

    private final static Logger logger = LoggerFactory.getLogger(CookieUtils.class);
    // cookie默认保存一天
    private static final int DEFAULT_MAX_AGE = 24 * 60 * 60;

    public static void setCookie(HttpServletResponse response, String name, String value, boolean isHttpOnly) {
        addCookie(response, name, value, null, null, DEFAULT_MAX_AGE, false, false);
    }

    /**
     * @param response
     * @param name       名称
     * @param value      值
     * @param domain     域名
     * @param path       相对路径
     * @param maxAge     存活时间
     * @param isHttpOnly 不允许js访问
     */
    public static void addCookie(HttpServletResponse response, String name, String value, String domain, String path, int maxAge, boolean isHttpOnly, boolean secure) {
        try {
            Cookie cookie = new Cookie(name, URLEncoder.encode(value, "UTF-8"));
            cookie.setMaxAge(maxAge);
            if (null != path) {
                cookie.setPath(path);
            }
            cookie.setHttpOnly(isHttpOnly);
            cookie.setSecure(secure);
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            logger.error("unsupported encoding exception", e);
        }
    }

    /**
     * 获取指定cookie
     *
     * @param request
     * @param name
     * @return
     */
    public static String getCookie(HttpServletRequest request, String name) {
        String value = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                try {
                    value = URLDecoder.decode(cookie.getValue(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    logger.error("unsupported encoding exception", e);
                }

                return value;
            }
        }

        return value;
    }

    /**
     * 删除指定的cookie
     *
     * @param request
     * @param response
     * @param name
     */
    public void removeCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                return;
            }
        }
    }

}
