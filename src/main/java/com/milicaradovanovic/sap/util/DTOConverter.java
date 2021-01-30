package com.milicaradovanovic.sap.util;

import com.milicaradovanovic.sap.dto.ScheduleTaskDTO;
import com.milicaradovanovic.sap.entity.ScheduleTaskEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DTOConverter {

    private ModelMapper modelMapper;

    @Autowired
    public DTOConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ScheduleTaskDTO toScheduleTaskDTO(ScheduleTaskEntity scheduleTaskEntity) {
        return this.modelMapper.map(scheduleTaskEntity, ScheduleTaskDTO.class);
    }

    public List<ScheduleTaskDTO> toScheduleTaskDTOs(List<ScheduleTaskEntity> scheduleTaskEntityList) {
        List<ScheduleTaskDTO> scheduleTaskDTOS = new ArrayList<>();

        scheduleTaskEntityList.forEach(scheduleTaskEntity -> {
            scheduleTaskDTOS.add(this.toScheduleTaskDTO(scheduleTaskEntity));
        });

        return scheduleTaskDTOS;
    }
}
