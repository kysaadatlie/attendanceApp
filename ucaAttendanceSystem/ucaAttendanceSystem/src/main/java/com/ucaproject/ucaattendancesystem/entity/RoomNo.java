package com.ucaproject.ucaattendancesystem.entity;

import com.ucaproject.ucaattendancesystem.entity.enums.Side;
import jakarta.persistence.*;
import lombok.*;

@Data @NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class RoomNo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_name", nullable = false)
    private String roomName;

    @Column(name = "is_occupied", nullable = false)
    private Boolean isOccupied;

    @Column(name = "side")
    private Side side;

    @OneToOne(mappedBy = "roomNo")
    private PersonInfo personInfo;
}
