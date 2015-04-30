package org.luapp.core.freemarker.functions;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class UpperZhFunction implements TemplateMethodModelEx {

    private final static String[] u = { "〇", "一", "二", "三", "四", "五", "六", "七", "八", "九" };

    @Override
    @SuppressWarnings("rawtypes")
    public Object exec(List arguments) throws TemplateModelException {

        if (arguments == null || arguments.size() != 1) {
            throw new TemplateModelException("Arguments is null or over 1");
        }

        String strParam = String.valueOf(arguments.get(0));

        if (!StringUtils.isNumeric(strParam)) {
            throw new TemplateModelException("Number format error");
        }

        StringBuilder sb = new StringBuilder();
        char[] digits = strParam.toCharArray();

        for (int i = 0; i < digits.length; i++) {
            sb.append(u[digits[i] - '0']);
        }

        return sb.toString();
    }
}
