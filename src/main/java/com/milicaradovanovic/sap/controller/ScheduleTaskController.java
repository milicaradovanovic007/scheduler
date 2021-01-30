package com.milicaradovanovic.sap.controller;

import com.milicaradovanovic.sap.dto.NewScheduleTaskDTO;
import com.milicaradovanovic.sap.dto.ResponseDTO;
import com.milicaradovanovic.sap.dto.StatusEnum;
import com.milicaradovanovic.sap.entity.ScheduleTaskEntity;
import com.milicaradovanovic.sap.scheduler.ScheduleTaskThread;
import com.milicaradovanovic.sap.service.ScheduleTaskService;
import com.milicaradovanovic.sap.util.DTOConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/task")
public class ScheduleTaskController {

    private ScheduleTaskService scheduleTaskService;
    private DTOConverter dtoConverter;
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Autowired
    public ScheduleTaskController(ScheduleTaskService scheduleTaskService, DTOConverter dtoConverter,
                                  ThreadPoolTaskScheduler threadPoolTaskScheduler) {
        this.scheduleTaskService = scheduleTaskService;
        this.dtoConverter = dtoConverter;
        this.threadPoolTaskScheduler = threadPoolTaskScheduler;
    }

//    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping(value = "")
    public ResponseEntity<ResponseDTO> getScheduleTaskList() {

        List<ScheduleTaskEntity> scheduleTaskEntityList = this.scheduleTaskService.getAll();

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO<>(StatusEnum.RESOURCE_FOUND,
                this.dtoConverter.toScheduleTaskDTOs(scheduleTaskEntityList)));
    }

    @PostMapping(value = "")
    public ResponseEntity<ResponseDTO> createNewScheduleTask(@Valid @RequestBody NewScheduleTaskDTO scheduleTaskDTO) {

        this.scheduleTaskService.addNew(scheduleTaskDTO);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO<>(StatusEnum.RESOURCE_CREATED,
                null));
    }

    @DeleteMapping(value = "/{taskId}")
    public ResponseEntity<ResponseDTO> deleteScheduleTask(@PathVariable(value = "taskId") long taskId) {

        boolean isDeleted = this.scheduleTaskService.deleteTask(taskId);

        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseDTO<>(true, StatusEnum.RESOURCE_NOT_FOUND, null));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO<>(StatusEnum.RESOURCE_DELETED,
                null));
    }

    @PutMapping(value = "/{taskId}")
    public ResponseEntity<ResponseDTO> updateScheduleTask(@PathVariable(value = "taskId") long taskId,
                                                          @Valid @RequestBody NewScheduleTaskDTO scheduleTaskDTO) {

        boolean isUpdated = this.scheduleTaskService.updateTask(taskId, scheduleTaskDTO);

        if (!isUpdated) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseDTO<>(true, StatusEnum.RESOURCE_NOT_FOUND, null));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO<>(StatusEnum.RESOURCE_UPDATED,
                null));
    }

    @GetMapping(value = "/{taskId}")
    public ResponseEntity<ResponseDTO> updateScheduleTask(@PathVariable(value = "taskId") long taskId) {

        Optional<ScheduleTaskEntity> optionalScheduleTaskEntity = this.scheduleTaskService.getById(taskId);

        if (optionalScheduleTaskEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseDTO<>(true, StatusEnum.RESOURCE_NOT_FOUND, null));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO<>(StatusEnum.RESOURCE_FOUND,
                this.dtoConverter.toScheduleTaskDTO(optionalScheduleTaskEntity.get())));
    }

    @GetMapping(value = "/{taskId}/execute")
    public ResponseEntity<ResponseDTO> executeTask(@PathVariable(value = "taskId") long taskId) {

        Optional<ScheduleTaskEntity> optionalScheduleTaskEntity = this.scheduleTaskService.getById(taskId);

        if (optionalScheduleTaskEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseDTO<>(true, StatusEnum.RESOURCE_NOT_FOUND, null));
        }

        ScheduleTaskEntity scheduleTaskEntity = optionalScheduleTaskEntity.get();

        threadPoolTaskScheduler.schedule(new ScheduleTaskThread(scheduleTaskEntity.getCode()),
                new CronTrigger(scheduleTaskEntity.getRecurrency()));

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO<>(StatusEnum.OK,
               null));
    }
}
