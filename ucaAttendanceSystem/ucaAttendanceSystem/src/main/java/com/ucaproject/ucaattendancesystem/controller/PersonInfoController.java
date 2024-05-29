package com.ucaproject.ucaattendancesystem.controller;

import com.ucaproject.ucaattendancesystem.dto.PersonInfoDto;
import com.ucaproject.ucaattendancesystem.dto.PersonInfoResponse;
import com.ucaproject.ucaattendancesystem.service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PersonInfoController {

    private PersonInfoService personInfoService;
    
    @Autowired
    public PersonInfoController(PersonInfoService personInfoService) {
        this.personInfoService = personInfoService;
    }

    @GetMapping("person_info")
    public ResponseEntity<PersonInfoResponse> getAllPersonInfo(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize) {
        return new ResponseEntity<>(personInfoService.getAllPersonInfo(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("/department/{departmentId}/person_info")
    public List<PersonInfoDto> getAllPersonInfoByDepartmentId(@PathVariable(value = "departmentId") Long departmentId) {
        return personInfoService.getPersonInfoByDepartmentId(departmentId);
    }

    @GetMapping("room/{roomNoId}/person_info")
    public List<PersonInfoDto> getPersonInfoByRoomId(@PathVariable(value = "roomNoId") Long roomNoId) {
        return personInfoService.getPersonInfoByRoomNoId(roomNoId);
    }

    @GetMapping("/department/{departmentId}/room/{roomNoId}/person_info/{personInfoId}")
    public ResponseEntity<PersonInfoDto> getPersonInfoById(@PathVariable(value = "departmentId") Long departmentId, @PathVariable(value = "roomNoId") Long roomNoId, @PathVariable(value = "personInfoId") Long personInfoId) {
        PersonInfoDto personInfoDto = personInfoService.getPersonInfoById(departmentId, roomNoId, personInfoId);
        return new ResponseEntity<>(personInfoDto, HttpStatus.OK);
    }

    @PostMapping("department/{departmentId}/room/{roomNoId}/person_info/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PersonInfoDto> createPersonInfo(@PathVariable(value = "departmentId") Long departmentId, @PathVariable(value = "roomNoId") Long roomNoId, @RequestBody PersonInfoDto personInfoDto) {
        return new ResponseEntity<>(personInfoService.createPersonInfo(departmentId, roomNoId, personInfoDto), HttpStatus.CREATED);
    }

    @PutMapping("department/{departmentId}/room/{roomNoId}/person_info/{personInfoId}/update")
    public ResponseEntity<PersonInfoDto> updatePersonInfo(@PathVariable(value = "departmentId") Long departmentId, @PathVariable(value = "roomNoId") Long roomNoId, @PathVariable(value = "personInfoId") Long personInfoId, @RequestBody PersonInfoDto personInfoDto) {
        PersonInfoDto response = personInfoService.updatePersonInfo(departmentId, roomNoId, personInfoId, personInfoDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("department/{departmentId}/room/{roomNoId}/person_info/{personInfoId}/delete")
    public ResponseEntity<String> deletePersonInfo(@PathVariable(value = "departmentId") Long departmentId, @PathVariable(value = "roomNoId") Long roomNoId, @PathVariable(value = "personInfoId") Long personInfoId) {
        personInfoService.deletePersonInfo(departmentId, roomNoId, personInfoId);
        return new ResponseEntity<>("Person Info deleted", HttpStatus.OK);
    }

}
