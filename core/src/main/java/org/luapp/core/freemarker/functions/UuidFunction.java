package org.luapp.core.freemarker.functions;

import java.util.List;
import java.util.UUID;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * uuid函数 
 */
public class UuidFunction implements TemplateMethodModelEx {

    @Override
    @SuppressWarnings("rawtypes")
    public Object exec(List arguments) throws TemplateModelException {
        return UUID.randomUUID();
    }

}
