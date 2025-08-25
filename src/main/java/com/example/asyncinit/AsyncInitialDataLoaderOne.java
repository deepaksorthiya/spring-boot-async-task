package com.example.asyncinit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.LockSupport;

@Component
//@Async
@Order(1) // order execution will not work if async annotation applied
public class AsyncInitialDataLoaderOne implements ApplicationRunner {

    private final Logger LOGGER = LoggerFactory.getLogger(AsyncInitialDataLoaderOne.class);


    @Override
    public void run(ApplicationArguments args) {
        LOGGER.info("###########AsyncInitialDataLoaderOne start############# in thread: {}", Thread.currentThread());
        LOGGER.info("AsyncInitialDataLoaderOne Running in thread: {}", Thread.currentThread());
        LockSupport.parkNanos(2 * 1_000_000_000L);
        LOGGER.info("###########AsyncInitialDataLoaderOne end############# in thread: {}", Thread.currentThread());
    }
}
