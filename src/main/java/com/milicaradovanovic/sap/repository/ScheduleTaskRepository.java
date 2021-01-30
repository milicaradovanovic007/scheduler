package com.milicaradovanovic.sap.repository;

import com.milicaradovanovic.sap.entity.ScheduleTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleTaskRepository extends JpaRepository<ScheduleTaskEntity, Long> {
}
