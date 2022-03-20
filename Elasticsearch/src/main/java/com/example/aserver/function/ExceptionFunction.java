package com.example.aserver.function;

@FunctionalInterface
public interface ExceptionFunction {

    /**
     * 抛出异常
     * @param message
     */
    void throwExpection(String message);
}
