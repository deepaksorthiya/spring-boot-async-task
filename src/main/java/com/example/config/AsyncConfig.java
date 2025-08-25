//package com.example.config;
//
//import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
//import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.AsyncConfigurer;
//
//@Configuration(proxyBeanMethods = false)
//public class AsyncConfig {
//
//    @Bean
//    public AsyncConfigurer asyncConfigurer() {
//        return new AsyncConfigurer() {
//            @Override
//            public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
//                return new SimpleAsyncUncaughtExceptionHandler();
//            }
//        };
//    }
//
//}