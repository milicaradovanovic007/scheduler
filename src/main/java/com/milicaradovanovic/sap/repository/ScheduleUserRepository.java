package com.milicaradovanovic.sap.repository;

import com.milicaradovanovic.sap.entity.ScheduleUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleUserRepository extends JpaRepository<ScheduleUserEntity, Long> {
    Optional<ScheduleUserEntity> findByEmail(String email);
}
