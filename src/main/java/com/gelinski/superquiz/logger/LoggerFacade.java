package com.gelinski.superquiz.logger;

public interface LoggerFacade {
    void info(String message);
    void error(String message);
    void error(String message, Throwable throwable);
    void debug(String message);
    void warn(String message);
}
