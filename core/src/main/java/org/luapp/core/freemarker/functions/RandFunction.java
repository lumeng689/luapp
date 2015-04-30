package org.luapp.core.freemarker.functions;

import java.util.List;
import java.util.Random;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * 返回一个随机的正整数 
 */
public class RandFunction implements TemplateMethodModelEx {

    private static Random randomNumberGenerator;

    private static synchronized void initRNG() {
        if (randomNumberGenerator == null)
            randomNumberGenerator = new Random();
    }

    @Override
    @SuppressWarnings("rawtypes")
    public Object exec(List arguments) throws TemplateModelException {

        if (randomNumberGenerator == null) {
            initRNG();
        }

        return randomNumberGenerator.nextInt(Integer.MAX_VALUE);
    }
}
