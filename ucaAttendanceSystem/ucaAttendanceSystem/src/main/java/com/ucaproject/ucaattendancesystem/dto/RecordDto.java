package com.ucaproject.ucaattendancesystem.dto;
import com.ucaproject.ucaattendancesystem.entity.enums.Status;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecordDto {
    private Long id;
    private LocalDateTime recordDateTime;
    private String cardNo;
    private String action;
    private Status status;
    private String comment;
}
