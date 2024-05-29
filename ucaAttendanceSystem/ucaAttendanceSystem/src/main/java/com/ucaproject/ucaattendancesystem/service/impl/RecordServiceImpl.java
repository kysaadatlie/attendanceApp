package com.ucaproject.ucaattendancesystem.service.impl;

import com.ucaproject.ucaattendancesystem.dto.RecordDto;
import com.ucaproject.ucaattendancesystem.entity.RecordEntity;
import com.ucaproject.ucaattendancesystem.entity.Staff;
import com.ucaproject.ucaattendancesystem.exceptions.RecordNotFoundException;
import com.ucaproject.ucaattendancesystem.exceptions.StaffNotFoundException;
import com.ucaproject.ucaattendancesystem.repository.RecordRepository;
import com.ucaproject.ucaattendancesystem.repository.StaffRepository;
import com.ucaproject.ucaattendancesystem.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecordServiceImpl implements RecordService {

    private RecordRepository recordRepository;
    private StaffRepository staffRepository;

    @Autowired
    public RecordServiceImpl(RecordRepository recordRepository, StaffRepository staffRepository) {
        this.recordRepository = recordRepository;
        this.staffRepository = staffRepository;
    }

    @Override
    public RecordDto createRecord(Long staffId, RecordDto recordDto) {

        RecordEntity recordEntity = mapToEntity(recordDto);
        Staff staff = staffRepository.findById(staffId).orElseThrow(() -> new StaffNotFoundException("Staff not found"));

        recordEntity.setStaff(staff);
        RecordEntity newRecord = recordRepository.save(recordEntity);

        return mapToDto(newRecord);
    }

    @Override
    public List<RecordDto> getAllRecord() {
        List<RecordEntity> recordEntities = recordRepository.findAll();
        return recordEntities.stream().map(r -> mapToDto(r)).collect(Collectors.toList());
    }

    @Override
    public List<RecordDto> getRecordsByStaffId(Long staffId) {
        List<RecordEntity> recordEntities = recordRepository.findByStaffId(staffId);
        return recordEntities.stream().map(r -> mapToDto(r)).collect(Collectors.toList());
    }

    @Override
    public RecordDto getRecordById(Long recordId, Long staffId) {

        Staff staff = staffRepository.findById(staffId).orElseThrow(() -> new StaffNotFoundException("Staff not found"));
        RecordEntity record = recordRepository.findById(recordId).orElseThrow(() -> new RecordNotFoundException("Record not found"));

        if(record.getStaff().getId() != record.getId()) {
            throw new RecordNotFoundException("This record doesn't belong to this staff");
        }

        return mapToDto(record);
    }

    @Override
    public RecordDto updateRecord(Long staffId, Long recordId, RecordDto recordDto) {

        Staff staff = staffRepository.findById(staffId).orElseThrow(() -> new StaffNotFoundException("Staff not found"));
        RecordEntity record = recordRepository.findById(recordId).orElseThrow(() -> new RecordNotFoundException("Record not found"));

        if(record.getStaff().getId() != record.getId()) {
            throw new RecordNotFoundException("This record doesn't belong to this staff");
        }

        record.setRecordDateTime(recordDto.getRecordDateTime());
        record.setCardNo(recordDto.getCardNo());
        record.setAction(recordDto.getAction());
        record.setStatus(recordDto.getStatus());
        record.setComment(recordDto.getComment());

        RecordEntity updatedRecord = recordRepository.save(record);

        return mapToDto(updatedRecord);
    }

    @Override
    public void deleteRecord(Long recordId, Long staffId) {

        Staff staff = staffRepository.findById(staffId).orElseThrow(() -> new StaffNotFoundException("Staff not found"));
        RecordEntity record = recordRepository.findById(recordId).orElseThrow(() -> new RecordNotFoundException("Record not found"));

        if(record.getStaff().getId() != record.getId()) {
            throw new RecordNotFoundException("This record doesn't belong to this staff");
        }
        recordRepository.delete(record);
    }

    private RecordDto mapToDto(RecordEntity record) {
        RecordDto recordDto = new RecordDto();
        recordDto.setId(record.getId());
        recordDto.setRecordDateTime(record.getRecordDateTime());
        recordDto.setCardNo(record.getCardNo());
        recordDto.setAction(record.getAction());
        recordDto.setStatus(record.getStatus());
        recordDto.setComment(record.getComment());
        return recordDto;
    }

    private RecordEntity mapToEntity(RecordDto recordDto) {
        RecordEntity recordEntity = new RecordEntity();
        recordEntity.setRecordDateTime(recordDto.getRecordDateTime());
        recordEntity.setCardNo(recordDto.getCardNo());
        recordEntity.setAction(recordDto.getAction());
        recordEntity.setStatus(recordDto.getStatus());
        recordEntity.setComment(recordDto.getComment());
        return recordEntity;
    }
}
