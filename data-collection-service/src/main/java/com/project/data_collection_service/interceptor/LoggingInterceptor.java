package com.project.data_collection_service.interceptor;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger =
            LogManager.getLogger(LoggingInterceptor.class);

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) {

        // Generate unique correlation ID
        String correlationId =
                UUID.randomUUID().toString();

        // Store inside request
        request.setAttribute(
                "correlationId",
                correlationId
        );

        // Log incoming request
        logger.info(
                "CorrelationId: {} | Incoming Request -> Method: {}, URI: {}",
                correlationId,
                request.getMethod(),
                request.getRequestURI()
        );

        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex
    ) {

        String correlationId =
                (String) request.getAttribute("correlationId");

        logger.info(
                "CorrelationId: {} | Completed Request -> Status: {}",
                correlationId,
                response.getStatus()
        );

        if (ex != null) {
            logger.error(
                    "CorrelationId: {} | Exception occurred",
                    correlationId,
                    ex
            );
        }
    }
}

