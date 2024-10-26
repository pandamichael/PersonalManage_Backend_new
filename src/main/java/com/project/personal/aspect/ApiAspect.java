package com.project.personal.aspect;


import com.project.personal.controller.BaseController;
import com.project.personal.controller.CommonController;
import com.project.personal.enums.CommonCode;
import com.project.personal.exception.BaseException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@EnableAsync
@Component
public class ApiAspect {

    @Pointcut("execution(* com.project.personal.controller.*.*(..))")
    public void controllerPoint() {
    }

    @Around("controllerPoint()")
    public Object doAroundAccessCheck(ProceedingJoinPoint pjp) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String path = request.getRequestURI();
        HttpSession session = request.getSession();

        log.info("[API] Path: {}, Method = {}", path, pjp.getSignature().getName());

        BaseController controller = (BaseController) pjp.getTarget();
        if(!(controller instanceof CommonController)) {
            Long userId = (Long) session.getAttribute("userId");
            if (userId == null) {
                throw new BaseException(CommonCode.N3003);
            }

            controller.setUserId(String.valueOf(userId));
        }

        return pjp.proceed();
    }


}
