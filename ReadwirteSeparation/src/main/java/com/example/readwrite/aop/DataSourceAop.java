package com.example.readwrite.aop;
 
/**
 * @author chaird
 * @create 2020-12-30 21:30
 */
import com.example.readwrite.config.db.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
 
/** 切面表达式和方法 */
@Aspect
@Component
public class DataSourceAop {
 
  /** 读切面（条件1&&条件2）
   * 条件1：没有Master注解 条件2：com.example.service包下 任意类 的select* 或者get*方法
   * */
  @Pointcut(
      "!@annotation(com.example.readwrite.annotation.Master) "
          + "&& (execution(* com.example.readwrite.service..*.select*(..)) "
          + "|| execution(* com.example.readwrite.service..*.get*(..)))")
  public void readPointcut() {}
 
 
    /** 写切面
     * 类似上面，不做解释
     * */
  @Pointcut(
      "@annotation(com.example.readwrite.annotation.Master) "
          + "|| execution(* com.example.readwrite.service..*.insert*(..)) "
          + "|| execution(* com.example.readwrite.service..*.add*(..)) "
          + "|| execution(* com.example.readwrite.service..*.update*(..)) "
          + "|| execution(* com.example.readwrite.service..*.edit*(..)) "
          + "|| execution(* com.example.readwrite.service..*.delete*(..)) "
          + "|| execution(* com.example.readwrite.service..*.remove*(..))")
  public void writePointcut() {}
 
    /**
     * Before方法，设置ThreadLocal里的一个变量为slave
     */
  @Before("readPointcut()")
  public void read() {
    DBContextHolder.slave();
  }
 
    /**
     * Before方法，设置ThreadLocal里的一个变量为master
     */
  @Before("writePointcut()")
  public void write() {
    DBContextHolder.master();
  }
 
  /** 另一种写法：if...else... 判断哪些需要读从数据库，其余的走主数据库 */
  //    @Before("execution(* com.cjs.example.service.impl.*.*(..))")
  //    public void before(JoinPoint jp) {
  //        String methodName = jp.getSignature().getName();
  //
  //        if (StringUtils.startsWithAny(methodName, "get", "select", "find")) {
  //            DBContextHolder.slave();
  //        }else {
  //            DBContextHolder.master();
  //        }
  //    }
}