package com.ucaproject.ucaattendancesystem.repository;

import com.ucaproject.ucaattendancesystem.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
