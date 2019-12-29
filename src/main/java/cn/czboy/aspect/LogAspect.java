package cn.czboy.aspect;

import cn.czboy.dao.AopLogDao;
import cn.czboy.entity.AopLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

/**
 * 注解式记录日志
 */

@Aspect
@Component
public class LogAspect {

    @Autowired
    private AopLogDao aopLogDao;

    @Pointcut("@annotation(cn.czboy.annotation.Log1)")
    public void pointcut() { }

    //前置通知,方法执行之前执行
    @Before("pointcut()")
    public void BeforeMethod(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        System.out.println("BeforeMethod  The method   "+ methodName +"   parameter is  "+ Arrays.asList(args));
        String className = jp.getTarget().getClass().getName();
        System.out.println("包名："+className);

        AopLog aopLog = new AopLog();
        aopLog.setId(UUID.randomUUID().toString());
        aopLog.setIp("192.168.100.100");
        aopLog.setCreateTime(new Date());
        aopLog.setMethod(methodName);
        aopLog.setUsername("czboy");
    }

    //后置通知,方法执行之后执行(不管是否发生异常)
    @After("pointcut()")
    public void AfterMethod(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        System.out.println("AfterMethod  The method    "+ methodName +"   parameter is  "+Arrays.asList(args));
        System.out.println();
    }

    //返回通知,方法正常执行完毕之后执行
    @AfterReturning(value="pointcut()",returning="result")
    public void AfterReturningMethod(JoinPoint jp,Object result) {
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        System.out.println("AfterReturningMethod  The method   "+ methodName +"   parameter is  "+ Arrays.asList(args)+" "+result);
        System.out.println();
    }

    //异常通知,在方法抛出异常之后执行
    @AfterThrowing(value="pointcut()",throwing="e")
    public void AfterThrowingMethod(JoinPoint jp,Exception e) {
        String methodName = jp.getSignature().getName();
        System.out.println("AfterThrowingMethod  The method   "+ methodName +"exception :"+e);
    }

    //环绕通知，在方法执行前和执行后
    @Around(value="pointcut()")
    public void AroundMethod(ProceedingJoinPoint pjp) {
        String methodName = pjp.getSignature().getName();   //方法名
        Object[] args = pjp.getArgs();  //参数

        try {
            //前置通知
            System.out.println("前置通知，方法名："+methodName+"，方法参数："+Arrays.asList(args));

            //执行方法
            pjp.proceed();

            //后置通知
            System.out.println("后置通知，方法名："+methodName+"，方法参数："+Arrays.asList(args));

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

}
