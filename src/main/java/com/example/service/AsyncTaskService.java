package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.locks.LockSupport;

@Service
public class AsyncTaskService {

    private final static Logger LOGGER = LoggerFactory.getLogger(AsyncTaskService.class);

    private final TaskScheduler taskScheduler;
    private final AsyncTaskExecutor applicationTaskExecutor;
    private ScheduledFuture<?> scheduledFuture;

    public AsyncTaskService(TaskScheduler taskScheduler, @Qualifier("applicationTaskExecutor") AsyncTaskExecutor applicationTaskExecutor) {
        this.taskScheduler = taskScheduler;
        this.applicationTaskExecutor = applicationTaskExecutor;
    }

    public void startAsyncTaskProgrammatically() {
        applicationTaskExecutor.execute(() -> {
            LOGGER.info("AsyncTaskProgrammatically execution Start In :: {}", Thread.currentThread());
            LockSupport.parkNanos(5 * 1_000_000_000L);
            LOGGER.info("AsyncTaskProgrammatically execution End In :: {}", Thread.currentThread());
        });
    }

    @Async
    public void startAsyncTaskAnnotation() {
        // Simulate a long-running task
        LOGGER.info("AsyncTaskAnnotation start in thread: {}", Thread.currentThread());
        LockSupport.parkNanos(5 * 1_000_000_000L);
        LOGGER.info("AsyncTaskAnnotation completed end in thread: {}", Thread.currentThread());
    }

    @Async
    public void doScheduleAsyncTask() {
        // Simulate a long-running task
        LOGGER.info("Schedule Async task Running in thread: {}", Thread.currentThread());
        this.scheduledFuture = taskScheduler.scheduleAtFixedRate((() -> LOGGER.info("Schedule Task executed :: {}", Thread.currentThread())), Duration.ofSeconds(3));
        LockSupport.parkNanos(5 * 1_000_000_000L);
        LOGGER.info("Schedule Async task completed in thread: {}", Thread.currentThread());
    }

    public void cancelScheduleAsyncTask() {
        if (scheduledFuture != null) {
            LOGGER.info("Async task Cancel Running in thread: {}", Thread.currentThread());
            scheduledFuture.cancel(true);
            LOGGER.info("Schedule Async task Cancel completed in thread: {}", Thread.currentThread());
        }
    }

    public void asyncWithCompletableFuture() throws ExecutionException, InterruptedException {
        Future<Long> future = applicationTaskExecutor.submit(() -> {
            LockSupport.parkNanos(5 * 1_000_000_000L);
            System.out.println("Current thread: " + Thread.currentThread());
            return Thread.currentThread().threadId();
        });

        System.out.println(future.get());

        // Use Spring's TaskScheduler to schedule a Runnable and set the result in a
        // CompletableFuture
        CompletableFuture<Integer> randomValueFuture = new CompletableFuture<>();
        Runnable randomTask = () -> {
            int randomValue = new Random().nextInt(100); // random value between 0-99
            System.out.println("Random value: " + randomValue);
            randomValueFuture.complete(randomValue);
        };
        taskScheduler.schedule(randomTask, Instant.now().plusSeconds(10));

        // Get the random value after execution
        System.out.println("Scheduled random value: " + randomValueFuture.get());
    }

    @Scheduled(fixedRate = 3000)
    @Async
    public void scheduledTask() {
        LOGGER.info("SchedulerTask started... {}", Thread.currentThread());
        LockSupport.parkNanos(2 * 1_000_000_000L);
        LOGGER.info("SchedulerTask ended... {}", Thread.currentThread());
    }
}
