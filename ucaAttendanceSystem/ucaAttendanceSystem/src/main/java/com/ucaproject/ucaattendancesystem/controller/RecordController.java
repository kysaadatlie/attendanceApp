package com.ucaproject.ucaattendancesystem.controller;

import com.ucaproject.ucaattendancesystem.dto.RecordDto;
import com.ucaproject.ucaattendancesystem.dto.StaffDto;
import com.ucaproject.ucaattendancesystem.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class RecordController {

    private RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("record")
    public ResponseEntity<List<RecordDto>> getRecord() {
        return new ResponseEntity<>(recordService.getAllRecord(), HttpStatus.OK);
    }

    @GetMapping("/staff/{staffId}/record")
    public List<RecordDto> getRecordByStaffId(@PathVariable(value = "staffId") Long staffId) {
        return recordService.getRecordsByStaffId(staffId);
    }

    @GetMapping("/staff/{staffId}/record/{recordId}")
    public ResponseEntity<RecordDto> getRecordById(@PathVariable(value = "staffId") Long staffId, @PathVariable(value = "recordId") Long recordId) {
        RecordDto recordDto = recordService.getRecordById(recordId, staffId);
        return new ResponseEntity<>(recordDto, HttpStatus.OK);
    }

    @PostMapping("/staff/{staffId}/record/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RecordDto> createRecord(@PathVariable(value = "staffId") Long staffId, @RequestBody RecordDto recordDto) {
        return new ResponseEntity<>(recordService.createRecord(staffId, recordDto), HttpStatus.CREATED);
    }

    @PutMapping("/staff/{staffId}/record/{recordId}/update")
    public ResponseEntity<RecordDto> updateRecord(@PathVariable(value = "staffId") Long staffId, @PathVariable(value = "recordId") Long recordId, @RequestBody RecordDto recordDto) {
        return new ResponseEntity<>(recordService.updateRecord(staffId, recordId, recordDto), HttpStatus.OK);
    }

    @DeleteMapping("/staff/{staffId}/record/{recordId}/delete")
    public ResponseEntity<String> deleteRecord(@PathVariable(value = "staffId") Long staffId, @PathVariable(value = "recordId") Long recordId) {
        recordService.deleteRecord(recordId, staffId);
        return new ResponseEntity<>("Record deleted", HttpStatus.OK);
    }

}
