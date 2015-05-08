package org.luapp.cms.aop;

import org.luapp.core.util.BeanValidators;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

/**
 * Created by lum on 2015/5/8.
 */
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { ConstraintViolationException.class })
    public final ResponseEntity<?> handleException(ConstraintViolationException ex, WebRequest request)    {
        return new ResponseEntity(BeanValidators.extractPropertyAndMessage(ex.getConstraintViolations()),
                HttpStatus.BAD_REQUEST);
    }
}
