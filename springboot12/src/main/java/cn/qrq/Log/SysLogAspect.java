package cn.qrq.Log;

import cn.qrq.mapper.MyLogMapper;
import cn.qrq.springboot.entities.AdminLog;
import cn.qrq.springboot.entities.Employee;
import cn.qrq.springboot.param.LoginParam;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.javassist.bytecode.SignatureAttribute;
import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.logging.Logger;

/** 系统日志：切面处理类 */
@Aspect
@Component
public class SysLogAspect {
    /**使用log4j2把一些信息打印在控制台上面，可以不写 */
//    private static final Logger log = LogManager.getLogger(SysLogAspect.class);

    /**操作数据库 */
    @Autowired
    private MyLogMapper myLogMapper;

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation(cn.qrq.Log.MyLog)")
    public void logPoinCut() {
    }

    //切面 配置通知
    @AfterReturning("logPoinCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        System.out.println("----------接口日志记录-----------");
        //用于保存日志
        AdminLog adminLog = new AdminLog();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();

        //获取操作--方法上的Log的值
        MyLog myLog = method.getAnnotation(MyLog.class);
        if (myLog != null) {
            //保存操作事件
            String value = myLog.value();
            adminLog.setOperation(value);//保存获取的操作
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        // 操作人账号、姓名（需要提前将用户信息存到session）
        LoginParam user = (LoginParam) request.getSession().getAttribute("user");
        if(user != null) {
            String userName = user.getUserName();
            adminLog.setUsername(userName);
            adminLog.setCreateDate(new Date());
        }


        //调用service保存Operation实体类到数据库
        myLogMapper.insert(adminLog);
    }
}