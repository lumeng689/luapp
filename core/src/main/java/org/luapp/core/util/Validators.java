package org.luapp.core.util;

import java.util.Iterator;
import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.MessageInterpolator;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 校验工具类 
 */
public class Validators {

    private static class ValidatorHolder {
        static Validator validator = new Validators().getValidator();
    }

    private final Logger logger = LoggerFactory.getLogger(Validators.class);

    private final ValidatorFactory factory;

    private Validators() {
        try {
            Configuration<?> factoryCfg = Validation.byDefaultProvider().configure();
            initFactoryConfig(factoryCfg);
            factory = factoryCfg.buildValidatorFactory();
        } catch (final ValidationException ex) {
            logger.error("Bean Validation provider can not be found, no validation will be performed");
            throw ex;
        }
    }

    private void initFactoryConfig(Configuration<?> factoryCfg) {
        //            factoryCfg.parameterNameProvider(cfg.getParameterNameProvider());

        MessageInterpolator messageInterpolator = new ResourceBundleMessageInterpolator(new PlatformResourceBundleLocator(
                "i18n/validation-messages"));
        factoryCfg.messageInterpolator(messageInterpolator);
        //            factoryCfg.traversableResolver(cfg.getTraversableResolver());
        //            factoryCfg.constraintValidatorFactory(cfg.getConstraintValidatorFactory());
        //            for (Map.Entry<String, String> entry : cfg.getProperties().entrySet()) {
        //                factoryCfg.addProperty(entry.getKey(), entry.getValue());
        //            }
    }

    private Validator getValidator() {
        return factory.getValidator();
    }

    public static String validate(Object bean) {

        Set<ConstraintViolation<Object>> constraintViolations = ValidatorHolder.validator.validate(bean);

        if (CollectionUtils.isEmpty(constraintViolations)) {
            return null;
        }

        Iterator<ConstraintViolation<Object>> iterator = constraintViolations.iterator();

        ConstraintViolation<Object> first = iterator.next();
        if (!iterator.hasNext()) {
            return first.getMessage();
        }

        // two or more elements
        StringBuilder buf = new StringBuilder(256); // Java default is 16, probably too small
        if (first != null) {
            buf.append(first);
        }

        while (iterator.hasNext()) {
            buf.append(";");
            ConstraintViolation<Object> elem = iterator.next();
            if (elem != null) {
                buf.append(elem.getMessage());
            }
        }

        return buf.toString();
    }

}
