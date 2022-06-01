//package com.hznu.xdd.Aspect;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import lombok.NonNull;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.core.io.Resource;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.util.Assert;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.constraints.NotNull;
//
//@Component
//@Aspect
//@Slf4j
//public class ControllerLogAop {
//    
//    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
//    public void restController(){}
//    
//    @Pointcut("@within(org.springframework.stereotype.Controller)")
//    public void controller(){}
//
//
//
//
//    private void printRequestLog(HttpServletRequest request, String clazzName, String methodName,
//                                 Object[] args) throws JsonProcessingException {
//        log.debug("Request URL: [{}], URI: [{}], Request Method: [{}], IP: [{}]",
//                request.getRequestURL(),
//                request.getRequestURI(),
//                request.getMethod(),
//                ServletUtils.getClientIP(request));
//
//        if (args == null || !log.isDebugEnabled()) {
//            return;
//        }
//
//        boolean shouldNotLog = false;
//        for (Object arg : args) {
//            if (arg == null
//                    || arg instanceof HttpServletRequest
//                    || arg instanceof HttpServletResponse
//                    || arg instanceof MultipartFile
//                    || arg.getClass().isAssignableFrom(MultipartFile[].class)) {
//                shouldNotLog = true;
//                break;
//            }
//        }
//
//        if (!shouldNotLog) {
//            String requestBody = JsonUtils.objectToJson(args);
//            log.debug("{}.{} Parameters: [{}]", clazzName, methodName, requestBody);
//        }
//    }
//    
//    
//    
//    
//    private void printResponseLog(HttpServletRequest request,String className,String methodName,Object returnObj){
//        if (log.isDebugEnabled()) {
//            String returnData = "";
//            if (returnObj != null) {
//                if (returnObj instanceof ResponseEntity) {
//                    ResponseEntity<?> responseEntity = (ResponseEntity<?>) returnObj;
//                    if (responseEntity.getBody() instanceof Resource) {
//                        returnData = "[ BINARY DATA ]";
//                    } else if (responseEntity.getBody() != null) {
//                        returnData = toString(responseEntity.getBody());
//                    }
//                } else {
//                    returnData = toString(returnObj);
//                }
//
//            }
//            log.debug("{}.{} Response: [{}]", className, methodName, returnData);
//        }
//    }
//    
//    
//    @NonNull
//    private String toString(@NotNull Object obj){
//        Assert.notNull(obj, "Return object must not be null");
//        String toString;
//        if (obj.getClass().isAssignableFrom(byte[].class) && obj instanceof Resource) {
//            toString = "[ BINARY DATA ]";
//        } else {
//            toString = JSONObject.toJSONString(obj);
//        }
//        return toString;
//    }
//    
//    
//}