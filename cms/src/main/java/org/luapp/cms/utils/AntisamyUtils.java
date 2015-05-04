package org.luapp.cms.utils;

import org.apache.commons.lang3.StringUtils;
import org.owasp.validator.html.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

/**
 * Created by lum on 2015/5/4.
 */
public class AntisamyUtils {
    private final static Logger logger = LoggerFactory.getLogger(AntisamyUtils.class);

    public static String cleanHtml(String contents) {
        try {
            Policy policy = Policy.getInstance(ResourceUtils.getFile("classpath:/conf/antisamy-myspace-1.4.4.xml"));
            AntiSamy as = new AntiSamy();

            CleanResults cr = as.scan(contents, policy);
            String cleanHTML = cr.getCleanHTML();
            if (StringUtils.isNotBlank(cleanHTML)) {
                return cleanHTML;
            }
        } catch (PolicyException e) {
            logger.error("Read antisamy poilicy file failed!", e);
            return "";
        } catch (ScanException e) {
            logger.error("Scan html content failed!", e);
            return "";
        } catch (FileNotFoundException e) {
            logger.error("Cannot find antisamy config xml");
            return "";
        }

        return "";
    }

    public static void main(String[] args) {
        System.out.println(cleanHtml("<a onerr=\"alert('123')\">ccc</a>"));
    }
}
