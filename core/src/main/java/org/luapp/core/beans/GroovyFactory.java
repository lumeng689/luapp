package org.luapp.core.beans;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileFilter;

/**
 * 动态加载指定目录下groovy脚本
 * <p/>
 * Created by lum on 2015/5/8.
 */
public class GroovyFactory implements ApplicationContextAware {

    private final static String REFRESH_CHECK_DELAY = "org.springframework.scripting.support.ScriptFactoryPostProcessor.refreshCheckDelay";
    private final static String LANGUAGE = "org.springframework.scripting.support.ScriptFactoryPostProcessor.language";
    private final static String BEAN_NAME = "org.springframework.scripting.groovy.GroovyScriptFactory";

    private String directory;

    private ApplicationContext applicationContext;

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

        // 动态加载指定位置的script脚本
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();

//        ResourceUtils.getFile(".")

        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        String realDir = cl.getResource(directory).getFile();
        String rootPath = new File(cl.getResource(".").getFile()).getPath();

        File[] files = new File(realDir).listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return "groovy".equalsIgnoreCase(FilenameUtils.getExtension(pathname.getName()));
            }
        });

        for (File file : files) {
            GenericBeanDefinition bd = new GenericBeanDefinition();
            bd.setBeanClassName(BEAN_NAME);
            bd.setAttribute(REFRESH_CHECK_DELAY, 5000);
            bd.setAttribute(LANGUAGE, "groovy");
            bd.getConstructorArgumentValues().addIndexedArgumentValue(0, file.getPath().replace(rootPath, ""));

            String beanName = StringUtils.uncapitalize(file.getName().replace("groovy", ""));
            beanFactory.registerBeanDefinition(beanName, bd);
        }
    }
}
