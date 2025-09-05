package com.zh8888.aop;

import com.zh8888.mapper.OperateLogMapper;
import com.zh8888.pojo.entity.OperateLog;
import com.zh8888.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LogAspect {
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("execution(* com.zh8888.service.*.*add*(..))||" +
            "execution(* com.zh8888.service.*.*update*(..))||" +
            "execution(* com.zh8888.service.*.*delete*(..))")
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // 记录开始时间
        long start = System.currentTimeMillis();

        // 执行原始方法
        Object result = joinPoint.proceed();

        // 记录结束时间
        long end = System.currentTimeMillis();

        // 记录操作日志
        try {
            this.recordLog(joinPoint, start, end, result);
        } catch (Exception e) {
            log.error("记录操作日志失败", e);
        }

        return result;
    }

    /**
     * 记录操作日志
     *
     * @param joinPoint 连接点
     * @param start 开始时间
     * @param end 结束时间
     * @param result 返回值
     */
    private void recordLog(ProceedingJoinPoint joinPoint, long start, long end, Object result) {
        OperateLog operateLog = new OperateLog();

        // 操作人ID
        try {
            operateLog.setOperateEmpId(CurrentHolder.getCurrentId());// 从当前线程中获取用户id
        } finally {
            CurrentHolder.remove();// 释放资源
        }

        // 操作时间
        operateLog.setOperateTime(LocalDateTime.now());

        // 类名
        operateLog.setClassName(joinPoint.getTarget().getClass().getName());

        // 方法名
        operateLog.setMethodName(joinPoint.getSignature().getName());

        // 方法参数
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            operateLog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        }

        // 返回值
        if (result != null) {
            operateLog.setReturnValue(result.toString());
        }

        // 操作耗时
        operateLog.setCostTime(end - start);

        // 保存到数据库
        operateLogMapper.insert(operateLog);
        log.info("记录操作日志：{}", operateLog);
    }
}
