package com.gelinski.superquiz.logger;

import com.gelinski.superquiz.logger.impl.LoggerFacadeImpl;

public class LoggerFactory {
    private LoggerFactory() {
    }

    public static LoggerFacade getLogger(Class<?> clazz) {
        return new LoggerFacadeImpl(clazz);
    }
}
