package com.ucaproject.ucaattendancesystem.service.impl;

import com.ucaproject.ucaattendancesystem.dto.PersonInfoDto;
import com.ucaproject.ucaattendancesystem.dto.PersonInfoResponse;
import com.ucaproject.ucaattendancesystem.dto.StaffDto;
import com.ucaproject.ucaattendancesystem.dto.StaffResponse;
import com.ucaproject.ucaattendancesystem.entity.PersonInfo;
import com.ucaproject.ucaattendancesystem.entity.Staff;
import com.ucaproject.ucaattendancesystem.exceptions.PersonInfoNotFoundException;
import com.ucaproject.ucaattendancesystem.exceptions.StaffNotFoundException;
import com.ucaproject.ucaattendancesystem.repository.PersonInfoRepository;
import com.ucaproject.ucaattendancesystem.repository.StaffRepository;
import com.ucaproject.ucaattendancesystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaffServiceImpl implements StaffService {

    private StaffRepository staffRepository;
    private PersonInfoRepository personInfoRepository;

    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository, PersonInfoRepository personInfoRepository) {
        this.staffRepository = staffRepository;
        this.personInfoRepository = personInfoRepository;
    }

    @Override
    public StaffDto createStaff(Long personInfoId, StaffDto staffDto) {
        Staff staff = mapToEntity(staffDto);

        PersonInfo personInfo = personInfoRepository.findById(personInfoId).orElseThrow(() -> new PersonInfoNotFoundException("Person Info Not Found"));

        staff.setPersonInfo(personInfo);

        Staff savedStaff = staffRepository.save(staff);
        return mapToDto(savedStaff);
    }

    @Override
    public List<StaffDto> getStaffByPersonInfoId(Long personInfoId) {
        List<Staff> staffList = staffRepository.findByPersonInfoId(personInfoId);
        return staffList.stream().map(s -> mapToDto(s)).collect(Collectors.toList());
    }

    @Override
    public StaffResponse getAllStaff(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Staff> staffs = staffRepository.findAll(pageable);
        List<Staff> listOfStaff = staffs.getContent();
        List<StaffDto> content = listOfStaff.stream().map(s -> mapToDto(s)).collect(Collectors.toList());

        StaffResponse staffResponse = new StaffResponse();
        staffResponse.setContent(content);
        staffResponse.setPageNo(staffs.getNumber());
        staffResponse.setPageSize(staffs.getSize());
        staffResponse.setTotalElements(staffs.getTotalElements());
        staffResponse.setTotalPages(staffs.getTotalPages());
        staffResponse.setLast(staffs.isLast());

        return staffResponse;
    }

    @Override
    public StaffDto getStaffById(Long personInfoId, Long staffId) {
        PersonInfo personInfo = personInfoRepository.findById(personInfoId).orElseThrow(() -> new PersonInfoNotFoundException("Person Info Not Found"));
        Staff staff = staffRepository.findById(staffId).orElseThrow(() -> new StaffNotFoundException("Staff Not Found"));

        if(staff.getPersonInfo().getId() != staff.getId()) {
            throw new StaffNotFoundException("This person info doesn't belong to this staff");
        }

        return mapToDto(staff);
    }

    @Override
    public StaffDto updateStaff(Long personInfoId, Long staffId, StaffDto staffDto) {
        PersonInfo personInfo = personInfoRepository.findById(personInfoId).orElseThrow(() -> new PersonInfoNotFoundException("Person Info Not Found"));
        Staff staff = staffRepository.findById(staffId).orElseThrow(() -> new StaffNotFoundException("Staff Not Found"));

        if(staff.getPersonInfo().getId() != staff.getId()) {
            throw new StaffNotFoundException("This person info doesn't belong to this staff");
        }

        staff.setName(staffDto.getName());
        Staff updatedStaff = staffRepository.save(staff);

        return mapToDto(updatedStaff);
    }

    @Override
    public void deleteStaff(Long personInfoId, Long staffId) {
        PersonInfo personInfo = personInfoRepository.findById(personInfoId).orElseThrow(() -> new PersonInfoNotFoundException("Person Info Not Found"));
        Staff staff = staffRepository.findById(staffId).orElseThrow(() -> new StaffNotFoundException("Staff Not Found"));

        if(staff.getPersonInfo().getId() != staff.getId()) {
            throw new StaffNotFoundException("This person info doesn't belong to this staff");
        }
        staffRepository.delete(staff);
    }

    private StaffDto mapToDto(Staff staff) {
        StaffDto staffDto = new StaffDto();
        staffDto.setId(staff.getId());
        staffDto.setName(staff.getName());
        return staffDto;
    }

    private Staff mapToEntity(StaffDto staffDto) {
        Staff staff = new Staff();
        staff.setName(staffDto.getName());
        return staff;
    }
}
