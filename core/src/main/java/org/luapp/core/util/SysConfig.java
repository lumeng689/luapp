package org.luapp.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 *  系统配置读取类 
 */
public class SysConfig {

    private static final Logger logger = LoggerFactory.getLogger(SysConfigHolder.class);

    public static class SysConfigHolder {
        public static SysConfig holder = new SysConfig();
    }

    private long lastModified;

    private volatile boolean firstInit = true;

    private Resource resource = null;

    private static ResourceLoader resourceLoader = new DefaultResourceLoader();

    private Properties properties = null;

    private SysConfig() {
        try {
            loadProperty();
        } catch (IOException e) {
            logger.error("Initializing SysConfigHolder failed! ", e);
        }
    }

    private synchronized void loadProperty() throws IOException {
        if (!firstInit && (resource.lastModified() == lastModified)) {
            return;
        }
        firstInit = false;
        Properties props = new Properties();
        String location = "sysconf.properties";
        logger.debug("Loading properties file from path:{}", location);

        InputStream is = null;
        try {
            resource = resourceLoader.getResource(location);
            is = resource.getInputStream();
            props.load(is);
        } catch (IOException ex) {
            logger.info("Could not load properties from path:{}, {} ", location, ex.getMessage());
        } finally {
            IOUtils.closeQuietly(is);
        }

        this.properties = props;
        this.lastModified = resource.lastModified();
    }

    private String getProperty(String key) {
        try {
            if (lastModified != resource.lastModified()) {
                loadProperty();
            }
        } catch (IOException e) {
            logger.error("Getting access to property file failed!", e);
        }
        return properties.getProperty(key);
    }

    private void updateProperty(String key, String value) {
        properties.put(key, value);
    }

    public static void setProperty(String key, String value) {
        SysConfigHolder.holder.updateProperty(key, value);
    }

    public static String getString(String key) {

        String value = SysConfigHolder.holder.getProperty(key);

        if (value == null) {
            throw new NoSuchElementException();
        }

        return value;
    }

    public static String getString(String key, String defaultValue) {
        String value = SysConfigHolder.holder.getProperty(key);

        if (value == null) {
            return defaultValue;
        }

        return value;
    }

    public static Long getLong(String key) {

        String value = SysConfigHolder.holder.getProperty(key);

        if (value == null) {
            throw new NoSuchElementException();
        }

        return NumberUtils.toLong(value);
    }

    public static Long getLong(String key, Long defaultValue) {

        String value = SysConfigHolder.holder.getProperty(key);

        if (value == null) {
            throw new NoSuchElementException();
        }

        return NumberUtils.toLong(value);
    }

    public static Integer getInt(String key) {

        String value = SysConfigHolder.holder.getProperty(key);

        if (value == null) {
            throw new NoSuchElementException();
        }

        return NumberUtils.toInt(value);
    }

    public static Integer getInt(String key, Integer defaultValue) {
        return NumberUtils.toInt(SysConfigHolder.holder.getProperty(key), defaultValue);
    }

    public static Double getDouble(String key) {
        String value = SysConfigHolder.holder.getProperty(key);

        if (value == null) {
            throw new NoSuchElementException();
        }

        return NumberUtils.toDouble(value);
    }

    public static Double getDouble(String key, Double defaultValue) {
        return NumberUtils.toDouble(SysConfigHolder.holder.getProperty(key), defaultValue);
    }

    public static boolean getBoolean(String key) {
        String value = SysConfigHolder.holder.getProperty(key);

        if (value == null) {
            throw new NoSuchElementException();
        }

        return BooleanUtils.toBoolean(value);
    }

    public static boolean getBoolean(String key, Boolean defaultValue) {
        String value = SysConfigHolder.holder.getProperty(key);

        if (value == null) {
            return defaultValue;
        }

        return BooleanUtils.toBoolean(value);
    }

    public static Date getDate(String key) {
        String value = SysConfigHolder.holder.getProperty(key);

        if (value == null) {
            throw new NoSuchElementException();
        }

        return DateTime.parse(value).toDate();
    }

    public static Date getDate(String key, Date defaultValue) {
        String value = SysConfigHolder.holder.getProperty(key);

        if (value == null) {
            return defaultValue;
        }
        return DateTime.parse(value).toDate();
    }
}
