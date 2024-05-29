package com.ucaproject.ucaattendancesystem.dto;

import com.ucaproject.ucaattendancesystem.entity.enums.Side;
import lombok.*;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class RoomNoDto {
    private Long id;
    private String roomName;
    private boolean isOccupied;
    private Side side;
}
