package com.example.asyncinit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.LockSupport;

@Component
//@Async
@Order(2) // order execution will not work if async annotation applied
public class AsyncInitialDataLoaderTwo implements ApplicationRunner {

    private final Logger LOGGER = LoggerFactory.getLogger(AsyncInitialDataLoaderTwo.class);


    @Override
    public void run(ApplicationArguments args) {
        LOGGER.info("###########AsyncInitialDataLoaderTwo start############# in thread: {}", Thread.currentThread());
        LOGGER.info("AsyncInitialDataLoaderTwo Running in thread: {}", Thread.currentThread());
        LockSupport.parkNanos(2 * 1_000_000_000L);
        LOGGER.info("###########AsyncInitialDataLoaderTwo end############# in thread: {}", Thread.currentThread());
    }
}
