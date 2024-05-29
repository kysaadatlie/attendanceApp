package com.ucaproject.ucaattendancesystem.service.impl;

import com.ucaproject.ucaattendancesystem.dto.DepartmentDto;
import com.ucaproject.ucaattendancesystem.entity.Department;
import com.ucaproject.ucaattendancesystem.exceptions.DepartmentNotFoundException;
import com.ucaproject.ucaattendancesystem.repository.DepartmentRepository;
import com.ucaproject.ucaattendancesystem.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setDepartment(departmentDto.getDepartment());

        Department newDepartment = departmentRepository.save(department);

        DepartmentDto departmentResponse = new DepartmentDto();
        departmentResponse.setId(newDepartment.getId());
        departmentResponse.setDepartment(newDepartment.getDepartment());

        return departmentResponse;
    }

    @Override
    public List<DepartmentDto> getAllDepartment() {
        List<Department> department = departmentRepository.findAll();
        return department.stream().map(d -> mapToDto(d)).collect(Collectors.toList());
    }

    @Override
    public DepartmentDto getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException("Department Not Found"));
        return mapToDto(department);
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto, Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException("Department Not Found"));
        department.setDepartment(departmentDto.getDepartment());
        Department updatedDepartment = departmentRepository.save(department);
        return mapToDto(updatedDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException("Department Not Found"));
        departmentRepository.delete(department);
    }

    private DepartmentDto mapToDto(Department department) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(department.getId());
        departmentDto.setDepartment(department.getDepartment());
        return departmentDto;
    }

    private Department mapToEntity(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setDepartment(departmentDto.getDepartment());
        return department;
    }
}
