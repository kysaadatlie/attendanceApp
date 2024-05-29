package com.ucaproject.ucaattendancesystem.service;

import com.ucaproject.ucaattendancesystem.dto.StaffDto;
import com.ucaproject.ucaattendancesystem.dto.StaffResponse;

import java.util.List;

public interface StaffService {
    StaffDto createStaff(Long personInfoId, StaffDto staffDto);
    List<StaffDto> getStaffByPersonInfoId(Long personInfoId);
    StaffResponse getAllStaff(int pageNo, int pageSize);
    StaffDto getStaffById(Long personInfoId, Long staffId);
    StaffDto updateStaff(Long personInfoId, Long staffId, StaffDto staffDto);
    void deleteStaff(Long personInfoId, Long staffId);
}
