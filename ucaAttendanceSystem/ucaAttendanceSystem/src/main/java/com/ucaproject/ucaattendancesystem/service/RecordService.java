package com.ucaproject.ucaattendancesystem.service;

import com.ucaproject.ucaattendancesystem.dto.RecordDto;

import java.util.List;

public interface RecordService {
    RecordDto createRecord(Long staffId, RecordDto recordDto);
    List<RecordDto> getAllRecord();
    List<RecordDto> getRecordsByStaffId(Long staffId);
    RecordDto getRecordById(Long recordId, Long staffId);
    RecordDto updateRecord(Long staffId, Long recordId, RecordDto recordDto);
    void deleteRecord(Long recordId, Long staffId);
}
