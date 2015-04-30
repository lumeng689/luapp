package org.luapp.core.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.io.IOUtils;

/**
 *  资源文件工具类
 */
public class ResourceUtils {

    private final static MyResourceBundleCountrol ctl = new MyResourceBundleCountrol();

    /**
     * 返回 {res}.properties 中 key 对应的值
     * @param baseName
     * @param key
     * @return
     */
    public static String getString(String baseName, String key) {
        return getStringForLocale(Locale.getDefault(), baseName, key);
    }

    /**
     * 返回 {res}.properties 中 key 对应的值，并对值进行参数格式化
     * @param baseName
     * @param key
     * @param args
     * @return
     */
    public static String getString(String baseName, String key, Object... args) {
        String text = getString(baseName, key);
        return (text != null) ? MessageFormat.format(text, args) : null;
    }

    public static String loadFromResource(String resource) {
        InputStream in = null;
        BufferedReader reader = null;
        try {
            in = new FileInputStream(resource);
            reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
            return IOUtils.toString(reader);
        } catch (Exception excp) {
            throw new RuntimeException(excp);
        } finally {
            IOUtils.closeQuietly(reader);
            IOUtils.closeQuietly(in);
            reader = null;
        }
    }

    private static String getStringForLocale(Locale locale, String baseName, String key) {
        ResourceBundle rb = ResourceBundle.getBundle(baseName, locale, ResourceUtils.class.getClassLoader(), ctl);
        return rb != null ? rb.getString(key) : null;
    }

    private static class MyResourceBundleCountrol extends ResourceBundle.Control {

        /**
         * 1小时重载一次
         */
        @Override
        public long getTimeToLive(String baseName, Locale locale) {
            return 3600000;
        }

        @Override
        public boolean needsReload(String baseName, Locale locale, String format, ClassLoader loader, ResourceBundle bundle,
                long loadTime) {
            return true;
        }

    }
}
