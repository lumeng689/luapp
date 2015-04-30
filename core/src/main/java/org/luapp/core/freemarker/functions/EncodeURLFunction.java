package org.luapp.core.freemarker.functions;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class EncodeURLFunction implements TemplateMethodModelEx {

    private final static Logger logger = LoggerFactory.getLogger(EncodeURLFunction.class);

    @Override
    @SuppressWarnings("rawtypes")
    public Object exec(List arguments) throws TemplateModelException {

        if (arguments == null || arguments.size() != 1) {
            throw new TemplateModelException("Arguments is null or over 1");
        }

        try {
            return URLEncoder.encode(String.valueOf(arguments.get(0)), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }
        return arguments.get(1);
    }
}
