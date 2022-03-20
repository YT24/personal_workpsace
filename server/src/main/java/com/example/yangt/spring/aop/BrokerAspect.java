package com.example.yangt.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BrokerAspect {


    /**
     * 定义切入点，切入点为com.example.demo.aop.AopDemo中的所有函数
     *通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("execution(public * com.example.yangt.spring.aop.AopController.*(..)))")
    public void BrokerAspect(){

    }


    /**
     * 定义切入点，切入点为com.example.demo.aop.AopDemo中的某个方法
     *通过@Pointcut注解声明频繁使用的切点表达式
     * args(point) 参数
     * Durant(int) 参数类型
     */
    @Pointcut("execution(public * com.example.yangt.spring.aop.AopController.Durant(int)) && args(point))")
    public void GameDataAspect(int point){

    }

    /**
     * @description  使用环绕通知
     */
    @Around("GameDataAspect(point)")
    public void doAroundGameData(ProceedingJoinPoint pjp, int point) throws Throwable {
        try{
            System.out.println("Around-球星上场前热身！");
            pjp.proceed();
            System.out.println("Around-球星本场得到" + point + "分" );
        }
        catch(Throwable e){
            System.out.println("异常通知：球迷要求退票！");
        }
    }

    /**
     * @description  在连接点执行之前执行的通知
     */
    @Before("BrokerAspect()")
    public void doBeforeGame(){
        System.out.println("Before-经纪人正在处理球星赛前事务！");
    }

    /**
     * @description  在连接点执行之后执行的通知（返回通知和异常通知的异常）
     */
    @After("BrokerAspect()")
    public void doAfterGame(){
        System.out.println("After-经纪人为球星表现疯狂鼓掌！");
    }

    /**
     * @description  在连接点执行之后执行的通知（返回通知）
     */
    @AfterReturning("BrokerAspect()")
    public void doAfterReturningGame(){
        System.out.println("AfterReturning-经纪人为球星表现疯狂鼓掌！");
    }

    /**
     * @description  在连接点执行之后执行的通知（异常通知）
     */
    @AfterThrowing("BrokerAspect()")
    public void doAfterThrowingGame(){
        System.out.println("AfterThrowing：球迷要求退票！");
    }
}
