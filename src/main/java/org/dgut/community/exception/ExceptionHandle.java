package org.dgut.community.exception;

import org.dgut.community.NotFoundException;
import org.dgut.community.resultenum.Result;
import org.dgut.community.util.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if(e instanceof NotFoundException){
            NotFoundException notFoundException = (NotFoundException)e;
            return ResultUtil.error(notFoundException.getCode(), notFoundException.getMessage());
        }else {
            return ResultUtil.error(-1,"未知错误");
        }
    }

}
