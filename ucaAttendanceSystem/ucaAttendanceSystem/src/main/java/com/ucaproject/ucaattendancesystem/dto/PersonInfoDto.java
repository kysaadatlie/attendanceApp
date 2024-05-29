package com.ucaproject.ucaattendancesystem.dto;

import com.ucaproject.ucaattendancesystem.entity.enums.Gender;
import com.ucaproject.ucaattendancesystem.entity.enums.Location;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonInfoDto {
    private Long id;
    private String email;
    private String phone;
    private String cardNo;
    private Gender gender;
    private String address;
    private String role;
    private Location location;
}
