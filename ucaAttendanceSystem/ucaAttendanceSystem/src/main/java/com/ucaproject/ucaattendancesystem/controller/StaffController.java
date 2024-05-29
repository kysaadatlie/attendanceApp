package com.ucaproject.ucaattendancesystem.controller;

import com.ucaproject.ucaattendancesystem.dto.StaffDto;
import com.ucaproject.ucaattendancesystem.dto.StaffResponse;
import com.ucaproject.ucaattendancesystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class StaffController {

    private StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("staff")
    public ResponseEntity<StaffResponse> getAllStaff(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize
    ) {
        return new ResponseEntity<>(staffService.getAllStaff(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("/person_info/{personInfoId}/staff")
    public List<StaffDto> getStaffByPersonInfoId(@PathVariable(value = "personInfoId") Long personInfoId) {
        return staffService.getStaffByPersonInfoId(personInfoId);
    }

    @GetMapping("/person_info/{personInfoId}/staff/{staffId}")
    public ResponseEntity<StaffDto> getStaffById(@PathVariable(value = "personInfoId") Long personInfoId, @PathVariable(value = "staffId") Long staffId) {
        StaffDto staffDto = staffService.getStaffById(personInfoId, staffId);
        return new ResponseEntity<>(staffDto, HttpStatus.OK);
    }

    @PostMapping("/person_info/{personInfoId}/staff/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<StaffDto> createStaff(@PathVariable(value = "personInfoId") Long personInfoId, @RequestBody StaffDto staffDto) {
        return new ResponseEntity<>(staffService.createStaff(personInfoId, staffDto), HttpStatus.CREATED);
    }

    @PutMapping("/person_info/{personInfoId}/staff/{staffId}/update")
    public ResponseEntity<StaffDto> updateStaff(@PathVariable(value = "personInfoId") Long personInfoId, @PathVariable(value = "staffId") Long staffId, @RequestBody StaffDto staffDto) {
        StaffDto response = staffService.updateStaff(personInfoId, staffId, staffDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/person_info/{personInfoId}/staff/{staffId}/delete")
    public ResponseEntity<String> deleteStaff(@PathVariable(value = "personInfoId") Long personInfoId, @PathVariable(value = "staffId") Long staffId) {
        staffService.deleteStaff(personInfoId, staffId);
        return new ResponseEntity<>("Staff deleted", HttpStatus.OK);
    }
}
