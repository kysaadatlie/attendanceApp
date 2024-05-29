package com.ucaproject.ucaattendancesystem.repository;

import com.ucaproject.ucaattendancesystem.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    List<Staff> findByPersonInfoId(Long personInfoId);
}
