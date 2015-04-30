package org.luapp.core.beans;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * 一个简单的发送邮件助手类
 */
public class MailHelper {

    private static final String DEFAULT_ENCODING = "UTF-8";

    private static Logger logger = LoggerFactory.getLogger(MailHelper.class);

    private JavaMailSender mailSender;

    private Configuration freemarkerConfiguration;

    /**
     * Spring的MailSender.
     */
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * 注入Freemarker引擎配置,构造Freemarker 邮件内容模板.
     */
    public void setFreemarkerConfiguration(Configuration freemarkerConfiguration) throws IOException {
        // 根据freemarkerConfiguration的templateLoaderPath载入文件.
//        template = freemarkerConfiguration.getTemplate("subscribeMail.ftl", DEFAULT_ENCODING);
        this.freemarkerConfiguration = freemarkerConfiguration;
    }

    /**
     * 发送freemarker模版邮件
     *
     * @param mailTo
     * @param subject
     * @param dataMap
     */
    public void sendNotificationMail(String mailTo, String subject, String templateName, Map<String, Object> dataMap) {
        try {
            MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true, DEFAULT_ENCODING);

            String[] tos = StringUtils.split(mailTo, ";");
            helper.setTo(tos);
            helper.setSubject(subject);
            String content = generateContent(templateName, dataMap);
            helper.setText(content, true);

            File attachment = generateAttachment();
            helper.addAttachment("mailAttachment.txt", attachment);

            mailSender.send(msg);
            logger.info("HTML版邮件已发送至{}", mailTo);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用Freemarker生成html格式内容.
     */
    private String generateContent(String templateName, Map<String, Object> context) throws MessagingException {

        try {
            Template template = freemarkerConfiguration.getTemplate("subscribeMail.ftl", DEFAULT_ENCODING);
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, context);
        } catch (IOException e) {
            logger.error("生成邮件内容失败, FreeMarker模板不存在", e);
            throw new MessagingException("FreeMarker模板不存在", e);
        } catch (TemplateException e) {
            logger.error("生成邮件内容失败, FreeMarker处理失败", e);
            throw new MessagingException("FreeMarker处理失败", e);
        }
    }

    /**
     * 获取classpath中的附件.
     */
    private File generateAttachment() throws MessagingException {
        try {
            Resource resource = new ClassPathResource("/conf/mailAttachment.txt");
            return resource.getFile();
        } catch (IOException e) {
            logger.error("构造邮件失败,附件文件不存在", e);
            throw new MessagingException("附件文件不存在", e);
        }
    }

    /**
     * 发送简单邮件
     *
     * @param mailTo  多个地址按";"分隔
     * @param subject
     * @param content
     */
    public void sendSimpleMail(String mailTo, String subject, String content) {
        SimpleMailMessage msg = new SimpleMailMessage();
        String[] tos = StringUtils.split(mailTo, ";");
        msg.setTo(tos);
        msg.setSubject(subject);
        msg.setText(content);
        try {
            mailSender.send(msg);
            if (logger.isInfoEnabled()) {
                logger.info("邮件已发送至{}", mailTo);
            }
        } catch (Exception e) {
            logger.error("发送邮件失败", e);
        }
    }
}
