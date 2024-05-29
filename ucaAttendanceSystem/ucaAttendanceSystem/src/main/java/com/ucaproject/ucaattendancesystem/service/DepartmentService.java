package com.ucaproject.ucaattendancesystem.service;

import com.ucaproject.ucaattendancesystem.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);
    List<DepartmentDto> getAllDepartment();
    DepartmentDto getDepartmentById(Long id);
    DepartmentDto updateDepartment(DepartmentDto departmentDto, Long id);
    void deleteDepartment(Long id);
}
