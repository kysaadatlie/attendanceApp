package com.ucaproject.ucaattendancesystem.repository;

import com.ucaproject.ucaattendancesystem.entity.PersonInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonInfoRepository extends JpaRepository<PersonInfo, Long> {
    List<PersonInfo> findByDepartmentId(Long departmentId);
    List<PersonInfo> findByRoomNoId(Long roomNoId);
}
