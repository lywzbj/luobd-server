package com.luobd.server.global;

import com.luobd.server.common.entities.ResponseData;
import com.luobd.server.common.exception.TokenValidException;
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
            return ResponseData.error(sb.toString());
        }else if(e instanceof TokenValidException) {
            ResponseData<?> error = ResponseData.error("Not auth");
            error.setCode(HttpStatus.UNAUTHORIZED.value());
        }
        return ResponseData.error(e.getMessage());
    }

}
