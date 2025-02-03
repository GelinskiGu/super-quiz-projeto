package com.gelinski.superquiz.logger.impl;

import com.gelinski.superquiz.logger.LoggerFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerFacadeImpl implements LoggerFacade {
    private final Logger logger;

    public LoggerFacadeImpl(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void error(String message) {
        logger.error(message);
    }

    @Override
    public void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }

    @Override
    public void debug(String message) {
        logger.debug(message);
    }

    @Override
    public void warn(String message) {
        logger.warn(message);
    }
}
