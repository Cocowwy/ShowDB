package cn.cocowwy.showdbcore.aspect;

import cn.cocowwy.showdbcore.annotation.Endpoint;
import cn.cocowwy.showdbcore.entities.Res;
import cn.cocowwy.showdbcore.util.EndpointUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 对端点功能进行鉴权的切面
 * Aspects for Authenticating Endpoint Capabilities
 *
 * @author Cocowwy
 * @create 2022-03-03-21:03
 */
@Aspect
//@Component
@Deprecated
public class EndpointAspect {

    @Pointcut("@annotation(cn.cocowwy.showdbcore.annotation.Endpoint)")
    private void pointcut() {
    }

    /**
     * 校验切面方法是否开启
     * @param point
     * @param endpoint
     * @return
     * @throws Throwable
     */
    @Around("pointcut()&&@annotation(endpoint)")
    public Object around(ProceedingJoinPoint point, Endpoint endpoint) throws Throwable {
        Boolean passOnMethod = EndpointUtil.canPass(endpoint.value());
        if (!passOnMethod) {
            return Res.success();
        }
        return point.proceed();
    }
}
