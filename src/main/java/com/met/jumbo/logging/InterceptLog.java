package com.met.jumbo.logging;

import com.met.jumbo.service.ILoggingService;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
public class InterceptLog implements HandlerInterceptor {

    public InterceptLog() {
        MDC.put("trace-id", UUID.randomUUID().toString());
    }

    private String TRACE_HEADER = "X-Trace-Id";

    @Autowired
    ILoggingService loggingService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceID = UUID.randomUUID().toString();
        if (request.getHeader(TRACE_HEADER) != null && !request.getHeader(TRACE_HEADER).isEmpty()) {
            traceID = request.getHeader(TRACE_HEADER);
        }
        MDC.put("trace-id", traceID);
        if(request.getMethod().equals(HttpMethod.GET.name())
                || request.getMethod().equals(HttpMethod.DELETE.name()))    {
            loggingService.displayReq(request,null);
        }
        return true;
    }
}
