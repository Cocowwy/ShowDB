package cn.cocowwy.showdbcore.aspect;

import cn.cocowwy.showdbcore.annotation.Endpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Cocowwy
 * @create 2022-03-03-21:03
 */
@Aspect
@Component
public class EndpointAspect {

    @Pointcut("@annotation(cn.cocowwy.showdbcore.annotation.Endpoint)")
    private void pointcut() {
    }

    @Around("pointcut()&&@annotation(endpoint)")
    public Object around(ProceedingJoinPoint point, Endpoint endpoint) throws Throwable {
        point.getSignature();
        point.proceed();
        return null;
    }
}
