package com.ucaproject.ucaattendancesystem.repository;

import com.ucaproject.ucaattendancesystem.entity.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<RecordEntity, Long> {
    List<RecordEntity> findByStaffId(Long staffId);
}
