package com.ucaproject.ucaattendancesystem.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FacultyDto {
    private Long id;
    private String name;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}
