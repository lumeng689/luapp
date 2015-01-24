package org.luapp.cms.utils;

import org.springframework.context.support.ResourceBundleMessageSource;

public class MessageHelper {

    private ResourceBundleMessageSource messageSource;

    public ResourceBundleMessageSource getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, null);
    }

    public String getMessage(String code) {
        return messageSource.getMessage(code, null, null);
    }
}
