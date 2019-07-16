package com.haizhi.mock.data.exeception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuguoqin
 * @date 2019/7/16 5:57 PM
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public Map<String, Object> resolveException(Throwable e) {
        log.error(e.getMessage(), e);
        HashMap<String, Object> hashMap = new HashMap<>(2);
        hashMap.put("code", 500);
        hashMap.put("msg", e.getMessage());
        return hashMap;
    }

}
