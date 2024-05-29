package com.ucaproject.ucaattendancesystem.entity;

import com.ucaproject.ucaattendancesystem.entity.enums.Gender;
import com.ucaproject.ucaattendancesystem.entity.enums.Location;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PersonInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    @Column(unique = true, nullable = false)
    private String cardNo;

    @Column(nullable = false)
    private Gender gender;

    @Column(name = "address")
    private String address;

    @Column(name = "role")
    private String role;

    @Column(name = "location")
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_no_id")
    private RoomNo roomNo;

    @OneToOne(mappedBy = "personInfo")
    private Staff staff;

    @OneToOne(mappedBy = "personInfo")
    private Faculty faculty;
}
