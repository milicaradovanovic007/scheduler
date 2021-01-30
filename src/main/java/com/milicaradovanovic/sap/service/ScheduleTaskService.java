package com.milicaradovanovic.sap.service;

import com.milicaradovanovic.sap.dto.NewScheduleTaskDTO;
import com.milicaradovanovic.sap.entity.ScheduleTaskEntity;
import com.milicaradovanovic.sap.repository.ScheduleTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleTaskService {

    private ScheduleTaskRepository scheduleTaskRepository;

    @Autowired
    public ScheduleTaskService(ScheduleTaskRepository scheduleTaskRepository) {
        this.scheduleTaskRepository = scheduleTaskRepository;
    }

    public Optional<ScheduleTaskEntity> getById(long taskId) {
        return this.scheduleTaskRepository.findById(taskId);
    }

    public List<ScheduleTaskEntity> getAll() {
        return scheduleTaskRepository.findAll();
    }

    public void addNew(NewScheduleTaskDTO scheduleTaskDTO) {
        ScheduleTaskEntity scheduleTaskEntity = new ScheduleTaskEntity();
        scheduleTaskEntity.setName(scheduleTaskDTO.getName());
        scheduleTaskEntity.setRecurrency(scheduleTaskDTO.getRecurrency());
        scheduleTaskEntity.setCode(scheduleTaskDTO.getCode());

        scheduleTaskRepository.save(scheduleTaskEntity);
    }

    public boolean deleteTask(long taskId) {
        Optional<ScheduleTaskEntity> optionalScheduleTaskEntity = this.scheduleTaskRepository.findById(taskId);

        if (optionalScheduleTaskEntity.isEmpty()) {
            return false;
        }

        ScheduleTaskEntity scheduleTaskEntity = optionalScheduleTaskEntity.get();

        this.scheduleTaskRepository.delete(scheduleTaskEntity);
        return true;
    }

    public boolean updateTask(long taskId, NewScheduleTaskDTO scheduleTaskDTO) {
        Optional<ScheduleTaskEntity> optionalScheduleTaskEntity = this.scheduleTaskRepository.findById(taskId);

        if (optionalScheduleTaskEntity.isEmpty()) {
            return false;
        }

        ScheduleTaskEntity scheduleTaskEntity = optionalScheduleTaskEntity.get();
        scheduleTaskEntity.setName(scheduleTaskDTO.getName());
        scheduleTaskEntity.setRecurrency(scheduleTaskDTO.getRecurrency());
        scheduleTaskEntity.setCode(scheduleTaskDTO.getCode());

        this.scheduleTaskRepository.save(scheduleTaskEntity);
        return true;
    }

    public boolean executeTask() {
        return true;
    }
}
