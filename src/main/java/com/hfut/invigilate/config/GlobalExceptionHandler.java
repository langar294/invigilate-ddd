package com.hfut.invigilate.config;


import com.hfut.invigilate.model.commen.CommonResult;
import com.hfut.invigilate.model.exception.BusinessException;

import com.landao.checker.model.collection.IllegalsHolder;
import com.landao.checker.model.exception.CheckIllegalException;
import com.landao.guardian.exception.author.UnAuthorizationException;
import com.landao.guardian.exception.author.UnLoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

/*    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
        });
        binder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDateTime.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
        });
        binder.registerCustomEditor(LocalTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalTime.parse(text, DateTimeFormatter.ofPattern("HH:mm:ss")));
            }
        });
    }*/

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = UnLoginException.class)
    public CommonResult<Void> unLoginHandler(UnLoginException e) {
        CommonResult<Void> result = new CommonResult<>();
        return result.err(e.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = UnAuthorizationException.class)
    public CommonResult<Void> authorizationHandler(UnAuthorizationException e) {
        CommonResult<Void> result = new CommonResult<>();
        return result.err(e.getMessage());
    }

    @ExceptionHandler(value = BusinessException.class)
    public CommonResult<Object> businessHandler(BusinessException e) {
        CommonResult<Object> result = new CommonResult<>();
        result.setCode(e.getCode());
        result.setMsg(e.getMsg());
        result.setData(e.getData());
        return result;
    }

    @ExceptionHandler(CheckIllegalException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult<IllegalsHolder> checkIllegalHandler(CheckIllegalException e) {
        CommonResult<IllegalsHolder> result = new CommonResult<>();
        result.err("参数错误").body(e.getIllegalList());
        return result;
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Throwable.class)
    public CommonResult<Object> exceptionHandler(Throwable e) {
        CommonResult<Object> result = new CommonResult<>();

        for (StackTraceElement element : e.getStackTrace()) {
            log.error("类:{} 方法:{} 行:{}", element.getClassName(), element.getMethodName(), element.getLineNumber());
/*            if(element.getClassName().startsWith("com.hfut")){
                log.error("类:{} 方法:{} 行:{}",element.getClassName(),element.getMethodName(),element.getLineNumber());
            }*/
        }
        String msg;

        if(e.getMessage()!=null){
            msg=e.getMessage();
        }else {
            msg=e.getCause().toString();
        }

        return result.err(msg);
    }

}