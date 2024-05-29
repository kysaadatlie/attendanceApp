package com.ucaproject.ucaattendancesystem.service;

import com.ucaproject.ucaattendancesystem.dto.PersonInfoDto;
import com.ucaproject.ucaattendancesystem.dto.PersonInfoResponse;

import java.util.List;

public interface PersonInfoService {
    PersonInfoDto createPersonInfo(Long departmentId, Long roomNoId, PersonInfoDto personInfoDto);
    List<PersonInfoDto> getPersonInfoByDepartmentId(Long departmentId);
    List<PersonInfoDto> getPersonInfoByRoomNoId(Long roomNoId);
    PersonInfoResponse getAllPersonInfo(int pageNo, int pageSize);
    PersonInfoDto getPersonInfoById(Long personInfoId, Long departmentId, Long roomNoId);
    PersonInfoDto updatePersonInfo(Long departmentId, Long roomNoId, Long personInfoId, PersonInfoDto personInfoDto);
    void deletePersonInfo(Long departmentId, Long roomNoId, Long personInfoId);
}
