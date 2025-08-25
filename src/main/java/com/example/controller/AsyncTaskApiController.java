package com.example.controller;

import com.example.service.AsyncTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncTaskApiController {

    private final Logger LOGGER = LoggerFactory.getLogger(AsyncTaskApiController.class);

    private final AsyncTaskService asyncTaskService;

    public AsyncTaskApiController(AsyncTaskService asyncTaskService) {
        this.asyncTaskService = asyncTaskService;
    }

    @GetMapping("/async-task-programmatically")
    public ResponseEntity<String> startAsyncTaskProgrammatically() {
        LOGGER.info("AsyncTaskApiController#startAsyncTaskProgrammatically Task execution start :: {}", Thread.currentThread());
        asyncTaskService.startAsyncTaskProgrammatically();
        LOGGER.info("AsyncTaskApiController#startAsyncTaskProgrammatically Task execution end :: {}", Thread.currentThread());
        return ResponseEntity.ok().body("SUCCESS");
    }

    @GetMapping("/async-task-annotation")
    public ResponseEntity<String> startAsyncTaskAnnotation() {
        LOGGER.info("AsyncTaskApiController#startAsyncTaskAnnotation Task execution start :: {}", Thread.currentThread());
        asyncTaskService.startAsyncTaskAnnotation();
        LOGGER.info("AsyncTaskApiController$startAsyncTaskAnnotation Task execution end :: {}", Thread.currentThread());
        return ResponseEntity.ok().body("SUCCESS");
    }

    @GetMapping("/schedule-async-task")
    public ResponseEntity<String> doScheduleAsyncTask() {
        LOGGER.info("Schedule Task executed :: {}", Thread.currentThread());
        asyncTaskService.doScheduleAsyncTask();
        return ResponseEntity.ok().body("SUCCESS");
    }

    @GetMapping("/schedule-async-task-cancel")
    public ResponseEntity<String> cancelScheduleAsyncTask() {
        LOGGER.info("Schedule Cancel Task executed :: {}", Thread.currentThread());
        asyncTaskService.cancelScheduleAsyncTask();
        return ResponseEntity.ok().body("SUCCESS");
    }

    @GetMapping("/schedule-async-task-with-future")
    public ResponseEntity<String> scheduleAsyncTaskWithCompletableFuture() {
        LOGGER.info("AsyncTaskApiController#scheduleAsyncTaskWithCompletableFuture Schedule Task With Future executed :: {}", Thread.currentThread());
        asyncTaskService.scheduleAsyncTaskWithCompletableFuture();
        return ResponseEntity.ok().body("SUCCESS");
    }

    @GetMapping("/cancel-all")
    public ResponseEntity<String> cancelAllScheduleAsyncTask() {
        LOGGER.info("AsyncTaskApiController#cancelAllScheduleAsyncTask Schedule Task With Future executed :: {}", Thread.currentThread());
        asyncTaskService.cancelAllScheduleAsyncTask();
        return ResponseEntity.ok().body("SUCCESS");
    }
}
