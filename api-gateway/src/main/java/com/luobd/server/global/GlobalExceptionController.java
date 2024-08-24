package com.luobd.server.global;

import com.luobd.server.common.entities.ResponseData;
import com.luobd.server.common.exception.NoPermissionException;
import com.luobd.server.common.exception.TokenValidException;
import com.luobd.server.common.exception.UnAuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionController {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseData<?> errorHandler(Exception e){
        ResponseData<?> error = ResponseData.error("error");
        if(e instanceof MethodArgumentNotValidException) {
            List<ObjectError> errors = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
            StringBuilder sb = new StringBuilder();
            Iterator<ObjectError> iterator = errors.iterator();
            while (iterator.hasNext()) {
                ObjectError next = iterator.next();
                sb.append(next.getDefaultMessage());
                if(iterator.hasNext()) {
                    sb.append(",");
                }
            }
            error.setMsg(sb.toString());
        }else if(e instanceof TokenValidException) {
            error.setCode(HttpStatus.UNAUTHORIZED.value());
            error.setMsg("Not Auth");
        }else if (e instanceof UnAuthenticationException) {
            error.setCode(HttpStatus.UNAUTHORIZED.value());
            error.setMsg("Not Auth");
        }else if(e instanceof NoPermissionException) {
            error.setCode(HttpStatus.FORBIDDEN.value());
            error.setMsg("没有访问权限");
        } else {
            e.printStackTrace();
            error.setMsg(e.getMessage());
        }
        return error;
    }

}
