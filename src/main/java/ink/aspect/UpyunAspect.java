package ink.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @Author 桂乙侨
 * @Date 2020/4/1 16:22
 * @Version 1.0
 */
@Aspect
@Component
@Slf4j
public class UpyunAspect {

    private HttpServletRequest request =null;
    @Around("@annotation(ink.annotation.SystemLog)")
    public Object checkController(ProceedingJoinPoint point) {
        long start = System.currentTimeMillis();
        log.info(start+"  开始执行");

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String method = request.getMethod();
        String url = request.getRequestURL().toString();
        String ip = getIpAddress(request);
        Object object = null;
        log.info("请求地址："+url+"  请求方法 "+method+" 请求 IP："+ip);
        try {
            object = point.proceed();

        } catch (Throwable throwable) {
            log.error("执行出错");
        }
        long end = System.currentTimeMillis();
        log.info(end+" 执行结束，共执行："+(end-start));
        return object;
    }

    private static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
