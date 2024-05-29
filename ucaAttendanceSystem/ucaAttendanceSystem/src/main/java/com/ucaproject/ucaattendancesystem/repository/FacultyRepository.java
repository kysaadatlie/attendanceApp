package com.ucaproject.ucaattendancesystem.repository;

import com.ucaproject.ucaattendancesystem.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findByPersonInfoId(Long personInfoId);
}
