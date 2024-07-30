package com.coj.cojbackend.aspect;

import com.coj.cojbackend.utils.ResponseUtil;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Log4j2
@Aspect
@Component
public class ValidationAspect {
    @Around("@annotation(com.coj.cojbackend.annotation.HandleValidationErrors) && args(.., bindingResult)")
    public Object handleValidationErrors(ProceedingJoinPoint joinPoint, BindingResult bindingResult) throws Throwable {

        if (bindingResult.hasErrors()) {

            return ResponseUtil.validationError(bindingResult);
        }

        return joinPoint.proceed();
    }
}
