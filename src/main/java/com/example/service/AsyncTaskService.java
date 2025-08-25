package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.locks.LockSupport;

@Service
public class AsyncTaskService {

    private final static Logger LOGGER = LoggerFactory.getLogger(AsyncTaskService.class);

    private final TaskScheduler taskScheduler;
    private final AsyncTaskExecutor applicationTaskExecutor;
    private final ScheduledAnnotationBeanPostProcessor scheduledAnnotationBeanPostProcessor;
    private ScheduledFuture<?> scheduledFuture;

    public AsyncTaskService(TaskScheduler taskScheduler, @Qualifier("applicationTaskExecutor") AsyncTaskExecutor applicationTaskExecutor, ScheduledAnnotationBeanPostProcessor scheduledAnnotationBeanPostProcessor) {
        this.taskScheduler = taskScheduler;
        this.applicationTaskExecutor = applicationTaskExecutor;
        this.scheduledAnnotationBeanPostProcessor = scheduledAnnotationBeanPostProcessor;
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
        this.scheduledFuture = taskScheduler.scheduleAtFixedRate((
                        () -> LOGGER.info("Schedule Task executed In Thread:: {} at :: {}", Thread.currentThread(), LocalDateTime.now())),
                Duration.ofSeconds(3));
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

    public void cancelAllScheduleAsyncTask() {
        Set<ScheduledTask> scheduledTasks = this.scheduledAnnotationBeanPostProcessor.getScheduledTasks();
        scheduledTasks.forEach(scheduledTask -> scheduledTask.cancel(true));
        LOGGER.info("Cancel All Async task Running in thread: {}", Thread.currentThread());
    }

    public void scheduleAsyncTaskWithCompletableFuture() {
        // Use Spring's TaskScheduler to schedule a Runnable and set the result in a
        // CompletableFuture
        CompletableFuture<Integer> randomValueFuture = new CompletableFuture<>();
        Runnable randomTask = () -> {
            LOGGER.info("Schedule Async With Future task Running in thread: {}", Thread.currentThread());
            int randomValue = new Random().nextInt(100); // random value between 0-99
            System.out.println("Random value: " + randomValue);
            LockSupport.parkNanos(3 * 1_000_000_000L);
            randomValueFuture.complete(randomValue);
            LOGGER.info("Schedule Async With Future task Finished in thread: {}", Thread.currentThread());
        };
        taskScheduler.schedule(randomTask, Instant.now().plusSeconds(10));

        // Get the random value after execution
        try {
            System.out.println("Scheduled random value: " + randomValueFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Scheduled(fixedRate = 5000)
    @Async
    public void scheduledTask() {
        LOGGER.info("SchedulerTask started in thread :: {} at :: {}", Thread.currentThread(), LocalDateTime.now());
        LockSupport.parkNanos(2 * 1_000_000_000L);
        LOGGER.info("SchedulerTask ended in thread :: {} at :: {}", Thread.currentThread(), LocalDateTime.now());
    }
}
