package com.ucaproject.ucaattendancesystem.service;

import com.ucaproject.ucaattendancesystem.dto.FacultyDto;

import java.util.List;

public interface FacultyService {
    FacultyDto createFaculty(Long personInfoId, FacultyDto facultyDto);
    List<FacultyDto> getAllFaculty();
    List<FacultyDto> getFacultyByPersonInfoId(Long personInfoId);
    FacultyDto getFacultyById(Long personInfoId, Long facultyId);
    FacultyDto updateFaculty(Long personInfoId, Long facultyId, FacultyDto facultyDto);
    void deleteFaculty(Long personInfoId, Long facultyId);

}
