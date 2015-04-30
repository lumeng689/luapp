package org.luapp.core.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.luapp.core.web.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;

public class StaticHtmlUtils {

    private static final Logger logger = LoggerFactory.getLogger(StaticHtmlUtils.class);

    public static void generate(String templateFile, Map<String, Object> dataMap, String targetHtmlFile) {
        ServletContext sc = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getServletContext();
//
//        if (sc == null) {
//            throw new RuntimeException("only used under web context");
//        }
//
        Configuration config = new Configuration();

        if (config == null) {
            config = new Configuration();
            config.setDefaultEncoding("UTF-8");
            config.setServletContextForTemplateLoading(sc, "/WEB-INF/ftl/pc");
        }

        Writer out = null;
        try {
            Template template = config.getTemplate(templateFile);
            template.setEncoding("UTF-8");

            File htmlFile = new File(RequestContext.root() + targetHtmlFile);
            // 确保父文件夹一定存在
            FileUtils.forceMkdir(htmlFile.getParentFile());
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));

            template.process(dataMap, out);

            out.flush();
            out.close();
        } catch (IOException e) {
            logger.error("error happened,maybe the folder of  target html file is incorrect", e);
        } catch (TemplateException e) {
            logger.error("render template failed", e);
        } finally {
            IOUtils.closeQuietly(out);
        }
    }
}
